package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, List<String>> movieDirectorPairDB = new HashMap<>(); // key: directorName, value: List of movies

    public String addMovie(Movie movie) {
        movieDB.put(movie.getName(), movie);
        return "New movie added successfully";
    }

    public String addDirector(Director director) {
        directorDB.put(director.getName(), director);
        return "New director added successfully";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(movieDirectorPairDB.containsKey(director)) {
            List<String> movies = movieDirectorPairDB.get(director);
            movies.add(movie);
            movieDirectorPairDB.put(director, movies);
        } else {
            List<String> movies = new ArrayList<>();
            movies.add(movie);
            movieDirectorPairDB.put(director, movies);
        }

        return "New movie-director pair added successfully";
    }

    public Movie getMovieByName(String name) {
        return movieDB.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorDB.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieDirectorPairDB.get(director);
//        List<String> movies = new ArrayList<>();
//        for(String movieName : movieDirectorPair.keySet()) {
//            if(movieDirectorPair.get(movieName).equals(director)) {
//                movies.add(movieName);
//            }
//        }
//        return movies;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieDB.keySet());
    }

    public void deleteDirectorByName(String name) {
        directorDB.remove(name);
        for(String directorName : movieDirectorPairDB.keySet()) {
            if(directorName.equals(name)) {
                List<String> movies = movieDirectorPairDB.get(directorName);
                for(String movie : movies) {
                    movieDB.remove(movie);
                }
                movieDirectorPairDB.remove(directorName);
            }
        }
//        for(String movieName : movieDirectorPair.keySet()) {
//            if(movieDirectorPair.get(movieName).equals(name)) {
//                movieDB.remove(movieName);
//                movieDirectorPair.remove(movieName);
//            }
//        }
    }

    public void deleteAllDirectors() {
        for(String directorName : directorDB.keySet()) {
            directorDB.remove(directorName);
            List<String> movies = movieDirectorPairDB.get(directorName);
            for(String movie : movies) {
                movieDB.remove(movie);
            }
            movieDirectorPairDB.remove(directorName);
//            for(String  : movieDirectorPair.keySet()) {
//                if(movieDirectorPair.get(movieName).equals(directorName)) {
//                    movieDB.remove(movieName);
//                    movieDirectorPair.remove(movieName);
//                }
//            }
        }
    }
}
