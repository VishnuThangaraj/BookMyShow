package com.vishnuthangaraj.BookMyShow.Service;

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
}
