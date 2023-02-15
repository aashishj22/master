package com.example.restaurantService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuisine
{
    @Id
    private String cuisineId;
    private byte[] image;
    private String cuisineName;
    private String cuisineDescription;
    private int price;
}
