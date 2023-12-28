package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.DTO.Request.RegularUserSignupDTO;
import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import com.vishnuthangaraj.BookMyShow.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    // Get the User by Email
    public ApplicationUser getUserByEmail(String email){
        ApplicationUser user = applicationUserRepository.getUserByEmail(email);
        return user;
    }

    // Create new Regular User and add to the Database
    public ApplicationUser signUp(RegularUserSignupDTO regularUserSignupDTO){
        ApplicationUser regularUser = new ApplicationUser();

        //Set the properties for the Regular User
        regularUser.setName(regularUserSignupDTO.getName());
        regularUser.setEmail(regularUserSignupDTO.getEmail());
        regularUser.setPhoneNumber(regularUserSignupDTO.getPhoneNumber());
        regularUser.setPassword(regularUserSignupDTO.getPassword());
        regularUser.setAge(regularUserSignupDTO.getAge());
        regularUser.setType(regularUserSignupDTO.getType().toString());

        applicationUserRepository.save(regularUser); // Save the User to the Database

        return regularUser;
    }
}
