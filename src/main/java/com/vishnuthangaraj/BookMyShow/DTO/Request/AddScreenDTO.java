package com.vishnuthangaraj.BookMyShow.DTO.Request;

import com.vishnuthangaraj.BookMyShow.Models.Screen;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class AddScreenDTO {

    private List<Screen> screens;
    private UUID hallId;
}
