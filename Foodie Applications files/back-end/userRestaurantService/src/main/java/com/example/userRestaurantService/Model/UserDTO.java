package com.example.userRestaurantService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
    @Id
    private String emailId;
    private String password;
    private String userRole;
}
