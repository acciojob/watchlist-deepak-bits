package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, String> movieDirectorPair = new HashMap<>(); // key: movieName, value: directorName

    public void addMovie(Movie movie) {
        movieDB.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorDB.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        movieDirectorPair.put(movie, director);
    }

    public Movie getMovieByName(String name) {
        return movieDB.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorDB.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movies = new ArrayList<>();
        for(String movieName : movieDirectorPair.keySet()) {
            if(movieDirectorPair.get(movieName).equals(director)) {
                movies.add(movieName);
            }
        }
        return movies;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieDB.keySet());
    }

    public void deleteDirectorByName(String name) {
        directorDB.remove(name);
        for(String movieName : movieDirectorPair.keySet()) {
            if(movieDirectorPair.get(movieName).equals(name)) {
                movieDB.remove(movieName);
                movieDirectorPair.remove(movieName);
            }
        }
    }

    public void deleteAllDirectors() {
        directorDB.clear();
//        List<String> movies = new ArrayList<>();
        for(String movieName : movieDirectorPair.keySet()) {
            movieDB.remove(movieName);
            movieDirectorPair.remove(movieName);
        }
    }
}
