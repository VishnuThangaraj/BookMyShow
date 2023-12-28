package com.vishnuthangaraj.BookMyShow.Controller;

import com.vishnuthangaraj.BookMyShow.DTO.Request.RegularUserSignupDTO;
import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import com.vishnuthangaraj.BookMyShow.Service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegularUserController {

    @Autowired
    private RegularUserService regularUserService;

    // Add regular user to the database
    @PostMapping("/signup")
    public ResponseEntity<ApplicationUser> signUp(@RequestBody RegularUserSignupDTO regularUserSignupDTO){
        ApplicationUser regularUser = regularUserService.signUp(regularUserSignupDTO);
        return new ResponseEntity<>(regularUser, HttpStatus.CREATED); // HttpStatus-code : 201
    }
}
