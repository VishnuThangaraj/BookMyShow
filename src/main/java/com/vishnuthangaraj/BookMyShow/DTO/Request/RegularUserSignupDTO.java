package com.vishnuthangaraj.BookMyShow.DTO.Request;

import com.vishnuthangaraj.BookMyShow.Enums.UserType;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class RegularUserSignupDTO {

    private String name;
    private String email;
    private long phoneNumber;
    private String password;
    private UserType type; // movieOwners, HallOwners and RegularUserServce
    private int age;
}
