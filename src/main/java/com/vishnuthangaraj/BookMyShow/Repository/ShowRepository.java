package com.vishnuthangaraj.BookMyShow.Repository;

import com.vishnuthangaraj.BookMyShow.Models.Show;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {

    // Get the List of Shows with the HallID
    @Query(value = "select * from show where hall_id =: hallID", nativeQuery = true)
    public List<Show> getShowByHallID(UUID hallID);

    // Get the List of Shows with the MovieID
    @Query(value = "select * from show where movie_id =: movieID", nativeQuery = true)
    public List<Show> getShowByMovieID(UUID movieID);

    // Get the List of Shows with the MovieID and HallID
    @Query(value = "select * from show where movie_id =: movieID and hall_id =: hallID", nativeQuery = true)
    public List<Show> getShowByHallIDAndMovieID(UUID movieID, UUID hallID);

    // Update Ticket Count for the Show
    @Transactional
    @Modifying
    @Query(value = "update show set available_tickets =: ticketCount where id =: showID", nativeQuery = true)
    public void updateTicketCount(UUID showID, int ticketCount);
}
