package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.DTO.Request.MovieOwnerSignUpDTO;
import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import com.vishnuthangaraj.BookMyShow.Models.Movie;
import com.vishnuthangaraj.BookMyShow.Models.Ticket;
import com.vishnuthangaraj.BookMyShow.Repository.ApplicationUserRepository;
import com.vishnuthangaraj.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    // Get the Movie Using the MovieID
    public Movie getMovieById(UUID movieId){
        return movieRepository.findById(movieId).orElse(null);
    }

    // Add new Movie Owner to the Database
    public ApplicationUser signUp(MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser movieOwner = new ApplicationUser();

        // Set the properties for the Movie Owner
        movieOwner.setName(movieOwnerSignUpDTO.getName());
        movieOwner.setPassword(movieOwnerSignUpDTO.getPassword());
        movieOwner.setEmail(movieOwnerSignUpDTO.getEmail());
        movieOwner.setPhoneNumber(movieOwnerSignUpDTO.getPhoneNumber());
        movieOwner.setType(movieOwnerSignUpDTO.getType().toString());
        movieOwner.setAge(movieOwnerSignUpDTO.getCompanyAge());
        applicationUserRepository.save(movieOwner); // Save Movie owner to Database

        movieOwnerSignUpDTO.getMovies().forEach(movie -> {
            movie.setOwner(movieOwner);
            movieRepository.save(movie);
        });

        return movieOwner;
    }

    // Get Count of Total Tickets Sold
    public int TotalTicketCount(Movie movie){
        return movie.getTickets().size();
    }

    // Get Box Office Collection of the Movie
    public int boxOfficeCollection(Movie movie){
        int totalIncome = movie.getTickets().stream().
                mapToInt(ticket -> ticket.getShow().getTicketPrice()).sum();
        return totalIncome;
    }
}
