package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.DTO.Request.AddShowDTO;
import com.vishnuthangaraj.BookMyShow.Models.Show;
import com.vishnuthangaraj.BookMyShow.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    // Create Show and add to Database
    public void createShow(Show show){
        showRepository.save(show);
    }
}
