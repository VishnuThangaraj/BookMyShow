package com.vishnuthangaraj.BookMyShow.Controller;

import com.vishnuthangaraj.BookMyShow.Models.Show;
import com.vishnuthangaraj.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    // Search List of movies using movie ID or Hall ID
    @GetMapping("/search")
    public ResponseEntity<List<Show>> searchMovie
    (@RequestParam(required = false) UUID movieID, @RequestParam(required = false) UUID hallID) throws Exception{

        if(movieID == null && hallID == null){
            throw new Exception("Please pass at-least one parameter !");
        }

        List<Show> shows;

        if(movieID == null) // Search with HallID
            shows = showService.getShowByHallID(hallID);

        else if(hallID == null) // Search with MovieID
            shows = showService.getShowByMovieID(movieID);

        else // Search with Movie and HallID
            shows = showService.getShowByHallIDAndMovieID(movieID, hallID);

        return new ResponseEntity<>(shows, HttpStatus.OK); // HttpStatus-code : 200
    }
}
