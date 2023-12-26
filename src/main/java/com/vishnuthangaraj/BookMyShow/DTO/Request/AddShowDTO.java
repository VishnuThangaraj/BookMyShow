package com.vishnuthangaraj.BookMyShow.DTO.Request;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class AddShowDTO {

    private int hour; // 13
    private int minutes; // 00
    private int ticketPrice;
    private UUID movieId;
    private UUID hallId;
}
