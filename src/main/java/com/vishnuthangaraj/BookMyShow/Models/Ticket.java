package com.vishnuthangaraj.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Component
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private ApplicationUser user;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Show show;
}
