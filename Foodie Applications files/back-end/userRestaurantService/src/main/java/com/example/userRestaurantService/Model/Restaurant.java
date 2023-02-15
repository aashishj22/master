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
public class Restaurant
{
    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private List<Cuisine> cuisineList;
    private byte[] picByte;
    private String fileName;
}