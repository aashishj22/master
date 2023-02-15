package com.example.restaurantService.Controller;

import com.example.restaurantService.Exceptions.CuisineNotFoundException;
import com.example.restaurantService.Exceptions.RestaurantNotFoundException;
import com.example.restaurantService.Model.Cuisine;
import com.example.restaurantService.Model.Restaurant;
import com.example.restaurantService.Service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class RestaurantController
{
    @Autowired
    RestaurantService restaurantService;
    private ResponseEntity<?> responseEntity;

    //http://localhost:65100/api/v1/{restaurantId}/cuisine
//    @PostMapping("{restaurantId}/cuisine")
//    public ResponseEntity<?> saveUserCuisineToList(@RequestBody Cuisine cuisine, @PathVariable String restaurantId) throws RestaurantNotFoundException
//    {
//        try
//        {
//            responseEntity = new ResponseEntity<>(restaurantService.addCuisineToRestaurant(cuisine,restaurantId), HttpStatus.OK);
//        }
//        catch (RestaurantNotFoundException e)
//        {
//            throw new RestaurantNotFoundException();
//        }
//        return responseEntity;
//    }

    //http://localhost:65100/api/v1/{restaurantId}/cuisine
    @GetMapping("/{restaurantId}/cuisine")
    public ResponseEntity<?> getAllCuisineFromRestaurant(@PathVariable String restaurantId) throws CuisineNotFoundException
    {
        try
        {
            responseEntity = new ResponseEntity<>(restaurantService.getAllCuisine(restaurantId), HttpStatus.OK);
        }
        catch (CuisineNotFoundException e)
        {
            throw new CuisineNotFoundException();
        }
        return responseEntity;
    }

    //http://localhost:65100/api/v1/restaurants
    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurants() throws RestaurantNotFoundException {
        return new ResponseEntity<>(restaurantService.getAllRestaurant(), HttpStatus.OK);
    }

    //http://localhost:65100/api/v1/{restaurantId}
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable String restaurantId) throws RestaurantNotFoundException {
        return new ResponseEntity<>(restaurantService.getRestaurantDetails(restaurantId), HttpStatus.OK);
    }

    //http://localhost:65100/api/v1/{restaurantId}/{cuisineId}
    @DeleteMapping("/{restaurantId}/{cuisineId}")
    public ResponseEntity<?> deleteCuisine(@PathVariable String restaurantId, @PathVariable String cuisineId) throws RestaurantNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(restaurantService.deleteCuisineFromRestaurant(restaurantId,cuisineId), HttpStatus.OK);
        }
        catch (RestaurantNotFoundException | CuisineNotFoundException e)
        {
            throw new RestaurantNotFoundException();
        }
        return responseEntity;
    }

    //http://localhost:65100/api/v1/restaurant
    @PostMapping("restaurant")
    public ResponseEntity<?> saveRestaurant(@RequestParam("file") MultipartFile file, @RequestParam ("restaurant") String restaurant) throws IOException {
        Restaurant restaurant1 = new ObjectMapper().readValue(restaurant, Restaurant.class);
        restaurant1.setPicByte(file.getBytes());
        restaurant1.setFileName(file.getOriginalFilename());
        Restaurant dbRestaurant = restaurantService.addRestaurant(restaurant1);
        return new ResponseEntity<>(dbRestaurant,HttpStatus.OK);
    }

    //http://localhost:65100/api/v1/{restaurantId}/cuisine
    @PostMapping("{restaurantId}/cuisine")
    public ResponseEntity<?> saveUserCuisineToList(@RequestParam("file") MultipartFile file, @RequestParam ("cuisine") String cuisine, @PathVariable String restaurantId) throws RestaurantNotFoundException, IOException {
        //            responseEntity = new ResponseEntity<>(restaurantService.addCuisineToRestaurant(cuisine,restaurantId), HttpStatus.OK);
        Cuisine cuisine1 = new ObjectMapper().readValue(cuisine, Cuisine.class);
        cuisine1.setImage(file.getBytes());
        Restaurant dbCuisine = restaurantService.addCuisineToRestaurant(cuisine1,restaurantId);
        return new ResponseEntity<>(dbCuisine,HttpStatus.OK);
    }

    //http://localhost:65100/api/v1/{restaurantId}
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String restaurantId) throws RestaurantNotFoundException {
        return new ResponseEntity<>(restaurantService.deleteProduct(restaurantId),HttpStatus.OK);
    }
}
