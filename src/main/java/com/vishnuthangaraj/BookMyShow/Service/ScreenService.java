package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.Models.Screen;
import com.vishnuthangaraj.BookMyShow.Repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    // Add Screen to the Database
    public void registerScreen(Screen screen){
        screenRepository.save(screen);
    }

    // Book Screen for the show
    public void bookScreen(UUID screenId){
        screenRepository.bookScreen(screenId);
    }
}
