/*

 IMDbQuery.java
 COSC 102, Colgate University

 Implements a sparse matrix using hash tables.
 Your code goes here.

*/

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class IMDbQuery
{
  HashMap<String, HashSet<String>> data = new HashMap<String, HashSet<String>>();
  HashMap<String, HashSet<String>> movies = new HashMap<String, HashSet<String>>();


  //Constructor
  //Gets passed all of the provided read in data
  //in the form of an ArrayList of String arrays.
  //Each string array represents one line of the source data
  //split on the forward slashes '/'.
 public IMDbQuery(ArrayList<String[]> data)
 {

   HashSet<String> names = new HashSet<String>();
   for (int i = 0; i < data.size(); i ++){

     HashSet<String> test = new HashSet<String>();
     String key = "";
     String[] line = data.get(i);

     key = line[0];
     if (line.length > 1){
     for (int j = 1; j < line.length; j ++){
       test.add(line[j]);
       if (names.contains(line[j])){
         HashSet<String> name = new HashSet<String>();
         name = movies.get(line[j]);
         name.add(key);
         movies.put(line[j], name);
       }
       else{
         HashSet<String> name = new HashSet<String>();
         name.add(key);
         movies.put(line[j], name);
         names.add(line[j]);
       }
     }
     }
     this.data.put(key, test);

   }


 }


 // Returns a collection of the movies that the argument actor has appeared in.
 // runs in O(m) time where m is the number of movies the given actor has appeared in.
 public Iterable<String> getMovies(String actor)
 {
  HashSet<String> actors_movies;
  actors_movies = movies.get(actor);
  return actors_movies;
 }


 // Returns a collection of actors that have appeared in the argument movie.
 // runs in O(a) time where a is the number of actors that appeared in the given movie.
 public Iterable<String> getActors(String movie)
 {
  HashSet<String> actors = this.data.get(movie);

  return actors;
 }


 // Returns a collection of actors that have appeared in any film with the argument actor.

 public Iterable<String> getCoStars(String actor)
 {
   HashSet<String> coStars = new HashSet<String>();
   HashSet<String> actors = new HashSet<String>();

   for (String m : movies.get(actor)){
     HashSet<String> check = data.get(m);
     for (String s : check) {
       if (!actors.contains(s)){
         actors.add(s);
         coStars.add(s);
       }
     }

   }
  return coStars;
 }


 // Returns a boolean indicating whether the argument actor appeared in the argument movie.
 // runs in O(1) time.
 public boolean isMatch(String actor, String movie)
 {
   if (data.containsKey(movie)){
     if (data.get(movie).contains(actor)){
       return true;
     }
   }
  return false;
 }


 // Returns a collection of all the movies in which the two argument actors have appeared together.
 // runs in O(a1 + a2) time, where a1 and a2 are the total number of movies actor1 and actor2 have appeared in, respectively.
 public Iterable<String> findMovieLinks(String actor1, String actor2)
 {
   HashSet<String> shared = new HashSet<String>();

   for (String s: movies.get(actor1)){
     if (movies.get(actor2).contains(s) && !shared.contains(shared)){
       shared.add(s);
     }
   }
  return shared;
 }


}
