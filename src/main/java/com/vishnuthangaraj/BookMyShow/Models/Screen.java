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
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String screenName;
    private int screenCapacity;
    private boolean status;
    private String type;

    @ManyToOne
    private Hall hall;
}
