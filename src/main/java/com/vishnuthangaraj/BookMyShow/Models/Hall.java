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
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String address;

    @ManyToOne
    private ApplicationUser owner;

    @OneToMany(mappedBy = "hall")
    private List<Screen> screens;

    @OneToMany(mappedBy = "hall")
    private List<Show> shows;

    @OneToMany(mappedBy = "hall")
    private List<Ticket> tickets;
}
