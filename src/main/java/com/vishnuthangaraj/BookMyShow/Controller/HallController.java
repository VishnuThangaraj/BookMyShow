package com.vishnuthangaraj.BookMyShow.Controller;

import com.vishnuthangaraj.BookMyShow.DTO.Request.AddScreenDTO;
import com.vishnuthangaraj.BookMyShow.DTO.Request.AddShowDTO;
import com.vishnuthangaraj.BookMyShow.DTO.Request.HallOwnerSignUpDTO;
import com.vishnuthangaraj.BookMyShow.DTO.Response.GeneralMessageDTO;
import com.vishnuthangaraj.BookMyShow.Exceptions.ResourceNotExistException;
import com.vishnuthangaraj.BookMyShow.Exceptions.UnAuthorized;
import com.vishnuthangaraj.BookMyShow.Exceptions.UserDoesNotExistException;
import com.vishnuthangaraj.BookMyShow.Models.ApplicationUser;
import com.vishnuthangaraj.BookMyShow.Models.Show;
import com.vishnuthangaraj.BookMyShow.Service.HallService;
import com.vishnuthangaraj.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hall")
public class HallController {

    @Autowired
    private HallService hallService;

    // Add new Hall-Owner to the Database
    @PostMapping("/signup")
    public ResponseEntity<ApplicationUser> signUp(@RequestBody HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser hallOwner = hallService.signUp(hallOwnerSignUpDTO);
        return new ResponseEntity<>(hallOwner, HttpStatus.CREATED);
    }

    // Add Screens to the Hall
    @PostMapping("/add-screen")
    public ResponseEntity<GeneralMessageDTO> addScreenToHall(@RequestBody AddScreenDTO addScreenDTO, @RequestParam String email){
        try{
            hallService.addScreens(addScreenDTO, email);
        }
        catch(UserDoesNotExistException | ResourceNotExistException exception){
            return new ResponseEntity<>(new GeneralMessageDTO(exception.getMessage()), HttpStatus.NOT_FOUND); // HttpStatus-code : 404
        }
        catch (UnAuthorized exception){
            return new ResponseEntity<>(new GeneralMessageDTO(exception.getMessage()), HttpStatus.UNAUTHORIZED); // HttpStatus-code : 401
        }

        return new ResponseEntity<>(new GeneralMessageDTO("Screens added Successfully !"), HttpStatus.CREATED); // HttpStatus-code : 201
    }

    // Add Shows to the Screen
    @PostMapping("/add-shows")
    public ResponseEntity addShows(@RequestBody AddShowDTO addShowDTO, @RequestParam String email){
        try{
            Show show = hallService.createShows(addShowDTO, email);
            return new ResponseEntity<>(show, HttpStatus.CREATED); // HttpStatus-code : 201
        }
        catch(UserDoesNotExistException | ResourceNotExistException exception){
            return new ResponseEntity<>(new GeneralMessageDTO(exception.getMessage()), HttpStatus.NOT_FOUND); // HttpStatus-code : 404
        }
        catch (UnAuthorized exception){
            return new ResponseEntity<>(new GeneralMessageDTO(exception.getMessage()), HttpStatus.UNAUTHORIZED); // HttpStatus-code : 401
        }
    }
}
