package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.DTO.Request.AddShowDTO;
import com.vishnuthangaraj.BookMyShow.Models.Movie;
import com.vishnuthangaraj.BookMyShow.Models.Show;
import com.vishnuthangaraj.BookMyShow.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    // Create Show and add to Database
    public void createShow(Show show){
        showRepository.save(show);
    }

    // Get the List of Shows with the HallID
    public List<Show> getShowByHallID(UUID hallID){
        List<Show> shows = showRepository.getShowByHallID(hallID);
        return shows;
    }

    // Get the List of Shows with the MovieID
    public List<Show> getShowByMovieID(UUID movieID){
        List<Show> shows = showRepository.getShowByMovieID(movieID);
        return shows;
    }

    // Get the List of Shows with the MovieID and HallID
    public List<Show> getShowByHallIDAndMovieID(UUID movieID, UUID hallID){
        List<Show> shows = showRepository.getShowByHallIDAndMovieID(movieID, hallID);
        return shows;
    }

    // Get the Show Using the Show ID
    public Show getShowByShowID(UUID showID){
        Show show = showRepository.findById(showID).orElse(null);
        return show;
    }

    // Update Ticket Count by reducing by One
    public void reduceTicketCountByOne(Show show){
        int availableTickets = show.getAvailableTickets() -1;
        showRepository.updateTicketCount(show.getId(), availableTickets);
    }
}
