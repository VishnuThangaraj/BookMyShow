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
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String password;
    private String type; // MovieOwner, HallOwner, RegularUser
    private int age;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private long phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
