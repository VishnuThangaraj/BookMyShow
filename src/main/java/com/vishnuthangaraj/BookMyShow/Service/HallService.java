package com.vishnuthangaraj.BookMyShow.Service;

import com.vishnuthangaraj.BookMyShow.DTO.Request.AddScreenDTO;
import com.vishnuthangaraj.BookMyShow.DTO.Request.AddShowDTO;
import com.vishnuthangaraj.BookMyShow.DTO.Request.HallOwnerSignUpDTO;
import com.vishnuthangaraj.BookMyShow.Exceptions.ResourceNotExistException;
import com.vishnuthangaraj.BookMyShow.Exceptions.UnAuthorized;
import com.vishnuthangaraj.BookMyShow.Exceptions.UserDoesNotExistException;
import com.vishnuthangaraj.BookMyShow.Models.*;
import com.vishnuthangaraj.BookMyShow.Repository.ApplicationUserRepository;
import com.vishnuthangaraj.BookMyShow.Repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private RegularUserService regularUserService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowService showService;

    // Get the Hall Using the HallId
    public Hall getHallById(UUID hallId){
        return hallRepository.findById(hallId).orElse(null);
    }

    // Add new Hall-Owner to the Database
    public ApplicationUser signUp (HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser hallOwner = new ApplicationUser();

        // Set the Attributes for the User(Owner)
        hallOwner.setName(hallOwnerSignUpDTO.getName());
        hallOwner.setEmail(hallOwnerSignUpDTO.getEmail());
        hallOwner.setPassword(hallOwnerSignUpDTO.getPassword());
        hallOwner.setPhoneNumber(hallOwnerSignUpDTO.getPhoneNumber());
        hallOwner.setAge(hallOwnerSignUpDTO.getCompanyAge());
        hallOwner.setType(hallOwnerSignUpDTO.getType().toString());
        applicationUserRepository.save(hallOwner);

        // Save all the Halls to the repository
        hallOwnerSignUpDTO.getHalls().forEach(hall -> {
            hall.setOwner(hallOwner);
            hallRepository.save(hall);
        });

        return hallOwner;
    }

    // Add screens to the Hall
    public void addScreens(AddScreenDTO addScreenDTO, String email){
        // Check if the User Exists and has the Permission to add screen
        ApplicationUser user = regularUserService.getUserByEmail(email);
        if(user == null)
            throw new UserDoesNotExistException("User with this email does not exist !");
        else if(!user.getType().equals("HallOwner"))
            throw new UnAuthorized("User does not have access to perform this action.");

        // Check if the Hall exists and check if the User have access to the Hall
        UUID hallId = addScreenDTO.getHallId();
        Hall hall = getHallById(hallId);
        if(hall == null)
            throw new ResourceNotExistException(String.format("Hall id : %s , does not exist in System", hallId.toString()));
        else if(hall.getOwner().getId() != user.getId())
            throw new UnAuthorized(String.format("User does not own this Hall with id : %s",hallId.toString()));

        // Add screens to the Hall
        addScreenDTO.getScreens().stream().forEach(screen -> {
            screen.setHall(hall);
            screenService.registerScreen(screen);
        });
    }

    // Add Shows to the Available Screen
    public Show createShows(AddShowDTO addShowDTO, String email){
        // Check if the User Exist and has the Permission to add screen
        ApplicationUser user = regularUserService.getUserByEmail(email);
        if(user == null)
            throw new UserDoesNotExistException("User with this email does not exist !");
        else if(!user.getType().equals("HallOwner"))
            throw new UnAuthorized("User does not have access to perform this action.");

        // Check if the Hall Exist and check if the User have access to the Hall
        UUID hallId = addShowDTO.getHallId();
        Hall hall = getHallById(hallId);
        if(hall == null)
            throw new ResourceNotExistException(String.format("Hall id : %s , does not exist in System", hallId.toString()));
        else if(hall.getOwner().getId() != user.getId())
            throw new UnAuthorized(String.format("User does not own this Hall with id : %s",hallId.toString()));

        // Check if the Movie Exist
        UUID movieId = addShowDTO.getMovieId();
        Movie movie = movieService.getMovieById(movieId);
        if(movie == null)
            throw new ResourceNotExistException(String.format
                    ("Movie with Movie-ID : %s , does not exist in System.", movieId.toString()));

        // Get the Screen if available and If No screens are available throw exception
        Screen screen = hall.getScreens().stream().filter(screen1 -> !screen1.isStatus()).
                findFirst().orElse(null);

        if(screen == null)
            throw new ResourceNotExistException(String.format
                    ("Hall with Hall-Id : %S , Does not have any Unoccupied Screens.", hallId.toString()));

        // Set Properties for the show
        Show show = new Show();
        show.setHall(hall);
        show.setMovie(movie);
        show.setScreen(screen);
        show.setAvailableTickets(screen.getScreenCapacity());
        show.setTicketPrice(addShowDTO.getTicketPrice());

        Date startDateTime = new Date();
        startDateTime.setHours(addShowDTO.getHour());
        startDateTime.setMinutes(addShowDTO.getMinutes());
        startDateTime.setSeconds(0);

        Date endDateTime = new Date();
        int endHour = (int) (addShowDTO.getHour() + movie.getDuration())%24;
        endDateTime.setHours(endHour);
        endDateTime.setMinutes(addShowDTO.getMinutes());
        endDateTime.setSeconds(0);

        // Add start and end time
        show.setStartTime(startDateTime);
        show.setEndTime(endDateTime);

        screenService.bookScreen(screen.getId());
        showService.createShow(show);

        return show;
    }


}
