package com.example.userRestaurantService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User{
    @Id
    private String emailId;
    private byte[] profilePicture;
//    private String fileName;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
    private List<Restaurant> favouriteRestaurant;
    private List<Cuisine> favouriteCuisine;
}
