package com.vishnuthangaraj.BookMyShow.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Component
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int availableTickets;
    private Date startTime;
    private Date endTime;
    private int ticketPrice;

    @JsonIgnore
    @ManyToOne
    private Hall hall;

    @JsonIgnore
    @ManyToOne
    private Movie movie;

    @JsonIgnore
    @ManyToOne
    private Screen screen;

    @OneToMany(mappedBy = "show")
    private List<Ticket> tickets;
}
