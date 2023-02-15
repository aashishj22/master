package com.example.userRestaurantService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonUser
{
    @Id
    private String emailId;
    private byte[] profilePicture;
//    private String fileName;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
}
