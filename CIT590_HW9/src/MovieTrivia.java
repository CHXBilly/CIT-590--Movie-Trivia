import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	/**
	 * Utility Methods
	 */
	
	/**
	 * Inserts given actor and his/her movies into database
	 */
	public void insertActor(String actor, String[] movies, ArrayList<Actor> actorsInfo) {
		 String actorCorrectForm = actor.trim().toLowerCase(); //make sure the name is in correct form
		 
		// situation where target Actor exist
			for (Actor targetActor: actorsInfo) {
				if (targetActor.getName().equals(actorCorrectForm)) {
					// update the movies information of this actor
					for (int i = 0; i < movies.length; i++) {
						//get the movie that already casted from the target Actor
						ArrayList<String> moviesCasted = targetActor.getMoviesCast();
						if (moviesCasted.contains(movies[i].trim().toLowerCase())) {//if movie already existed then pass
							continue;
						} else {
							moviesCasted.add(movies[i].trim().toLowerCase());
						}
					}
					return;
				}
			}
			//Everytime we want to add a non exist target actor
			Actor newActor = new Actor (actorCorrectForm);
			actorsInfo.add(newActor); //add the new actor in
			for (int i = 0; i < movies.length; i++) {
				newActor.getMoviesCast().add(movies[i].trim().toLowerCase());//add the new actor's movie 
			}
			return;
	}
	
	/**
	 * Inserts given ratings for given movie into database
	 */
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
		 String movieCorrectForm = movie.trim().toLowerCase(); //make sure the movie is in correct form
		 if (ratings==null || ratings.length!=2){
			 return;
		 }else if(ratings[0]<0 || ratings[0]>100){
			 return;
		 }else if(ratings[1]<0 || ratings[1]>100) {
			 return;
		 }else {
			 //for all the movies info inside the arraylist
			 for (Movie moviesInfoCasted: moviesInfo) {
				 //update the ratings if the movie is already existed
				 if (moviesInfoCasted.getName().equals(movieCorrectForm)) {
					 moviesInfoCasted.setCriticRating(ratings[0]);
					 moviesInfoCasted.setAudienceRating(ratings[1]);
					 return;
				 //if it's not existed, create a new one 
				 }else {
					 Movie newMovie = new Movie(movieCorrectForm, ratings[0], ratings[1]);
					 moviesInfo.add(newMovie);
					 return;
				 }
			 }
		 }
	}
	
	/**
	 * Given an actor, returns the list of all movies
	 */
	public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
		String actorCorrectForm = actor.trim().toLowerCase(); 
		ArrayList<String> targetActorsInfo = new ArrayList<String>();
		for (Actor targetActor: actorsInfo) {
			//if the given actor name is in the actors info, return the move actor casted
			if(targetActor.getName().equals(actorCorrectForm)) {
				targetActorsInfo = targetActor.getMoviesCast();
				//deleted return targetActorsInfor then all worked?
			}
		}
		//if the given actor name is not in the actors info, return a empty list
		return targetActorsInfo;
	}
	
	/**
	 * returns the list of all actors in that movie
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo){
		String movieCorrectForm = movie.trim().toLowerCase();
		ArrayList<String> actorInSameMovies = new ArrayList<String>(); 
		for(Actor targetActor: actorsInfo) {
			if(targetActor.getMoviesCast().contains(movieCorrectForm)) {
				actorInSameMovies.add(targetActor.getName());
			}
		}
		return actorInSameMovies;
	}
	
	/**
	 * returns a list of movies that satisfy an inequality or equality, based on the 
comparison argument and the targeted rating argument
	 */
	public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo){
		ArrayList<String> moviesSelected = new ArrayList<String>(); 
		if(targetRating<0 || targetRating>100) {
			return moviesSelected;
		}else{
			//if the input is critic
			if(isCritic) {
				if(comparison=='<') {
					for (Movie moviesInfoCasted: moviesInfo){
	                    if (moviesInfoCasted.getCriticRating() < targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
	                }  
				}else if(comparison=='>') {
					for (Movie moviesInfoCasted: moviesInfo){
						if (moviesInfoCasted.getCriticRating() > targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
					}
				}else {
					for (Movie moviesInfoCasted: moviesInfo){
						if (moviesInfoCasted.getCriticRating() == targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
					}
				}
				
			//if the input is audience rating
			}else {
				if(comparison=='<') {
					for (Movie moviesInfoCasted: moviesInfo){
	                    if (moviesInfoCasted.getCriticRating() < targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
	                }  
				}else if(comparison=='>') {
					for (Movie moviesInfoCasted: moviesInfo){
						if (moviesInfoCasted.getCriticRating() > targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
					}
				}else {
					for (Movie moviesInfoCasted: moviesInfo){
						if (moviesInfoCasted.getCriticRating() == targetRating) {
	                    	moviesSelected.add(moviesInfoCasted.getName());
	                    }
					}
				}
			}			
		}
		return moviesSelected;
	}
	
	/**
	 * Fun Methods
	 */
	/**
	 * Returns a list of all actors that the given actor has ever worked with in any movie 
except the actor herself/himself. 
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo){
		String actorCorrectForm = actor.trim().toLowerCase();
		//select all movies the actor involved
		ArrayList<String> moviesInvolved = selectWhereActorIs(actorCorrectForm, actorsInfo);
		//an empty list that has all actor's collaboration list
		ArrayList<String> actorCollaborationList = new ArrayList<String>();
		
		for(String moviesInfoCasted:moviesInvolved) {//why it needs to be string here
			ArrayList<String> actorsInOneMovie=selectWhereMovieIs (moviesInfoCasted, actorsInfo);
			
			for (int i = 0; i < actorsInOneMovie.size(); i++) {
			if (actorCollaborationList.contains(actorsInOneMovie.get(i).trim().toLowerCase()) || actorsInOneMovie.get(i).trim().toLowerCase().equals(actorCorrectForm) ) {//if movie already existed then pass
				continue; //skip adding actor if its he/herself or already appeard in the list
			} else {
				actorCollaborationList.add(actorsInOneMovie.get(i).trim().toLowerCase());
			}			
		}
	}
		return actorCollaborationList;
	}
	
	/**
	 * Returns a list of movie names where both actors were cast
	 */
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo){
		ArrayList<String> commonMovie = new ArrayList<String>();
		//since selectwhereactor is already trim and and lowercase
		ArrayList <String> actor1Movies = selectWhereActorIs (actor1, actorsInfo);
		ArrayList <String> actor2Movies = selectWhereActorIs (actor2, actorsInfo);
		
		//if there is one non-existent actors, return empty list
		if(actor1Movies==null || actor2Movies==null) {
			return commonMovie;
		}
		//if is the same then it contains everything
		for (int i = 0; i < actor1Movies.size(); i++) {
			if (actor2Movies.contains(actor1Movies.get(i).trim().toLowerCase())) {
				commonMovie.add(actor1Movies.get(i).trim().toLowerCase());
			} 		
		}
		return commonMovie;
	}
	
	/**
	 *  Returns a list of movie names that both critics and the audience have rated above 85 (>= 85). 
	 */
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		//ArrayList<String> goodCriticMovies = selectWhereRatingIs('>', 84, true, moviesInfo);
		//ArrayList<String> goodAudienceMovies = selectWhereRatingIs('>', 84, false, moviesInfo);
		ArrayList<String> bothGoodMovie = new ArrayList<String>();
		
		for (Movie targetMovie : moviesInfo){
			//if both ratings are above 85
            if (targetMovie.getCriticRating() > 84 && targetMovie.getAudienceRating() > 84){
            	bothGoodMovie.add(targetMovie.getName());
            }
        }
		return bothGoodMovie;
	}
	
	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both movies.
	 */
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo){
		ArrayList<String> commonActor = new ArrayList<String>();
		//since selectwhereactor is already trim and and lowercase
		ArrayList <String> movie1Actors = selectWhereMovieIs (movie1, actorsInfo);
		ArrayList <String> movie2Actors = selectWhereMovieIs (movie2, actorsInfo);
		
		//if there is one non-existent actors, return empty list
		if(movie1Actors==null || movie2Actors==null) {
			return commonActor;
		}
		//if is the same then it contains everything
		for (int i = 0; i < movie1Actors.size(); i++) {
			if (movie2Actors.contains(movie1Actors.get(i).trim().toLowerCase())) {
				commonActor.add(movie1Actors.get(i).trim().toLowerCase());
			} 		
		}
		return commonActor;
	}
	
	/**
	 *  returns the mean value of the critics ratings and the audience ratings.
	 */
	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		double sumCriticRatings = 0;
		double sumAudienceRatings = 0;
		double length=0;
		double mean[]=new double[2];
		for(Movie allMovies:moviesInfo) {
			sumCriticRatings += allMovies.getCriticRating();
			sumAudienceRatings += allMovies.getAudienceRating();
			length++;
		}
		mean[0]=sumCriticRatings/length;
		mean[1]=sumAudienceRatings/length;
		return mean;
		
	}
	
	
}
	

