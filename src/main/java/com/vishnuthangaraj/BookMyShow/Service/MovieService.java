package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.Models.Movie;
import com.vishnuthangaraj.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Get the Movie Using the MovieID
    public Movie getMovieById(UUID movieId){
        return movieRepository.findById(movieId).orElse(null);
    }
}
