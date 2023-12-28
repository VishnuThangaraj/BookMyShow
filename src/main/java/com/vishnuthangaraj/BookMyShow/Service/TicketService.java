package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.Exceptions.ResourceNotExistException;
import com.vishnuthangaraj.BookMyShow.Exceptions.UnAuthorized;
import com.vishnuthangaraj.BookMyShow.Exceptions.UserDoesNotExistException;
import com.vishnuthangaraj.BookMyShow.Models.*;
import com.vishnuthangaraj.BookMyShow.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RegularUserService regularUserService;
    @Autowired
    private ShowService showService;
    @Autowired
    private BarCodeGeneratorService barCodeGeneratorService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MovieService movieService;

    // Buy Ticket for the Show
    public Ticket buyTicket(String email, UUID showID){
        // Get the user with the email
        ApplicationUser user = regularUserService.getUserByEmail(email);
        if(user == null) // Throw Exception if user does not exist with the email
            throw new UserDoesNotExistException(String.format("User with EmailId %s does not exist in System.", email));
        if(!user.getType().equals("RegularUser")) // Throw Exception if the User is not Regular Type
            throw new UnAuthorized(String.format("User with Email %s does not have required Access", email));

        // Check if the Show Exist in the Database if not Throw Exception
        Show show = showService.getShowByShowID(showID);
        if(show == null)
            throw new ResourceNotExistException(String.format("Show with id %s does not exist in our system.", showID));

        showService.reduceTicketCountByOne(show);

        Ticket ticket = new Ticket();
        ticket.setHall(show.getHall());
        ticket.setMovie(show.getMovie());
        ticket.setUser(user);
        ticket.setShow(show);
        ticketRepository.save(ticket); // Save ticket to the Database

        Movie movie = show.getMovie();
        Hall hall = show.getHall();

        sendTicketConfirmationMailToUser(ticket, user, movie, hall, show); // Send Mail to the User for Ticket Registration
        sendMailToMovieOwner(movie);

        return ticket;
    }

    // Send Mail to User for Ticket Booking
    public void sendTicketConfirmationMailToUser(Ticket ticket, ApplicationUser user, Movie movie, Hall hall, Show show){
        String MessageBody = String.format("""
                        Hey %s,
                        Congratulations!! your ticket got booked on our BookMyShow Application. Below are your ticket details:
                        1. Movie Name - %s
                        2. Hall Name - %s
                        3. Hall Address - %s
                        4. Date And timings - %s
                        5. Ticket Price- %d

                        Hope you will enjoy your show, All The Best
                        BookMyShow Application""",
                user.getName(),movie.getName(), hall.getName(), hall.getAddress(), show.getStartTime().toString(), show.getTicketPrice()
        );

        String mailSubject = String.format("Congratulations!! %s your ticket got generated !!", user.getName());

        // Add QR-Code to the Email
        try{
            barCodeGeneratorService.generateQR(MessageBody);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        // SendMail
        String filePath = "./src/main/resources/static/QRCode.png"; // Relative file Path
        mailService.generateMailWithAttachment(user.getEmail(), mailSubject,MessageBody, filePath);
    }

    // Send Mail to Movie_Owner with Revenue and Tickets Sold
    public void sendMailToMovieOwner(Movie movie){
        ApplicationUser movieOwner = movie.getOwner();

        int totalTicketsSold = movieService.TotalTicketCount(movie);
        int totalIncome = movieService.boxOfficeCollection(movie);

        String movieMessage = String.format("""
                Hii %s
                Congratulations!! your ticket got sold
                TotalTicketsSold : %d
                TotalIncome : %d""",
                movie.getOwner().getName(), totalTicketsSold, totalIncome );

        String movieSubject = String.format("Congratulations!! %s One more ticket sold", movie.getOwner().getName());
        mailService.generateMailWithoutAttachment(movie.getOwner().getEmail(), movieSubject, movieMessage);
    }
}
