package com.vishnuthangaraj.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Component
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String directorName;
    private String actorName;
    private String actressName;
    private int imdbRating;
    private double duration; // Runtime in Hours

    @OneToMany(mappedBy = "movie")
    private List<Ticket> tickets;

    @ManyToOne
    private ApplicationUser owner;
}
