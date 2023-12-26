package com.vishnuthangaraj.BookMyShow.DTO.Response;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class GeneralMessageDTO {
    private String message;
}
