package com.example.restaurantService.Service;

import com.example.restaurantService.Exceptions.CuisineNotFoundException;
import com.example.restaurantService.Exceptions.RestaurantNotFoundException;
import com.example.restaurantService.Model.*;
import com.example.restaurantService.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant)
    {
        return restaurantRepository.insert(restaurant);
    }

    @Override
    public Restaurant addCuisineToRestaurant(Cuisine cuisine, String restaurantId) throws RestaurantNotFoundException {
        if(restaurantRepository.findById(restaurantId).isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        if (restaurant.getCuisineList() == null)
        {
            restaurant.setCuisineList(Arrays.asList(cuisine));
        }
        else
        {
            List<Cuisine> cuisineList = restaurant.getCuisineList();
            cuisineList.add(cuisine);
            restaurant.setCuisineList(cuisineList);
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteCuisineFromRestaurant(String restaurantId, String cuisineId) throws RestaurantNotFoundException, CuisineNotFoundException {
        boolean cuisineIdPresent = false;
        if (restaurantRepository.findById(restaurantId).isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        List<Cuisine> cuisineList= restaurant.getCuisineList();
        cuisineIdPresent = cuisineList.removeIf(d->d.getCuisineId().equals(cuisineId));
        if (!cuisineIdPresent)
        {
            throw new CuisineNotFoundException();
        }
        restaurant.setCuisineList(cuisineList);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Cuisine> getAllCuisine(String restaurantId) throws CuisineNotFoundException {
        if (restaurantRepository.findById(restaurantId).isEmpty())
        {
            throw new CuisineNotFoundException();
        }
        return restaurantRepository.findById(restaurantId).get().getCuisineList();
    }

    @Override
    public List<Restaurant> getAllRestaurant() throws RestaurantNotFoundException {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantDetails(String restaurantId) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantId).get();
    }

    @Override
    public boolean deleteProduct(String restaurantId) throws RestaurantNotFoundException {
        if(restaurantRepository.findById(restaurantId).isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        restaurantRepository.deleteById(restaurantId);
        return true;
    }
}
