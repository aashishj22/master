package com.example.userRestaurantService.Service;

import com.example.userRestaurantService.Exceptions.RestaurantAlreadyExistsException;
import com.example.userRestaurantService.Model.Cuisine;
import com.example.userRestaurantService.Exceptions.UserAlreadyExistException;
import com.example.userRestaurantService.Model.CommonUser;
import com.example.userRestaurantService.Model.Restaurant;
import com.example.userRestaurantService.Model.User;

import java.util.List;


public interface UserRestaurantService
{
    User registerUser(CommonUser commonUser) throws UserAlreadyExistException;
    User addFavouriteRestaurant(Restaurant restaurant, String emailId) throws RestaurantAlreadyExistsException;
    public User addFavouriteCuisine(Cuisine cuisine, String emailId);
    List<Restaurant> getFavouriteRestaurant(String emailId);
    List<Cuisine> getFavouriteCuisine(String emailId);
    User deleteFromFavouriteRestaurant(String restaurantName, String emailId);
    User deleteFromFavouriteCuisine(String cuisineName, String emailId);
    User getUserDetails(String emailId);
}
