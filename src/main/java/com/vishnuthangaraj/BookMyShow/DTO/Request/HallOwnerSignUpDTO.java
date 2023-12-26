package com.vishnuthangaraj.BookMyShow.DTO.Request;

import com.vishnuthangaraj.BookMyShow.Enums.UserType;
import com.vishnuthangaraj.BookMyShow.Models.Hall;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class HallOwnerSignUpDTO {

    private String name;
    private String email;
    private long phoneNumber;
    private String password;
    private UserType type;
    private List<Hall> halls;
    private int companyAge;
}
