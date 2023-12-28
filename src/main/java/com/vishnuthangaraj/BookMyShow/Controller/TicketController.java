package com.vishnuthangaraj.BookMyShow.Controller;

import com.vishnuthangaraj.BookMyShow.Models.Ticket;
import com.vishnuthangaraj.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Buy Ticket for the Show
    @PostMapping("/buy-ticket")
    public ResponseEntity<Ticket> buyTicket(@RequestParam String email, @RequestParam UUID showID){
        Ticket ticket = ticketService.buyTicket(email, showID);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED); // HttpStatus-code : 201
    }
}
