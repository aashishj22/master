package com.example.restaurantService.Repository;

import com.example.restaurantService.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String>
{
}
