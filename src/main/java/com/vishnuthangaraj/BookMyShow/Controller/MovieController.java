package com.vishnuthangaraj.BookMyShow.Controller;

import com.vishnuthangaraj.BookMyShow.DTO.Request.MovieOwnerSignUpDTO;
import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import com.vishnuthangaraj.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Add new Movie Owner to the Database
    public ResponseEntity<ApplicationUser> signUp(@RequestBody MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser movieOwner = movieService.signUp(movieOwnerSignUpDTO);
        return new ResponseEntity<>(movieOwner, HttpStatus.CREATED); // HttpStatus-code : 201
    }
}
