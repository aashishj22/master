package com.example.userRestaurantService.Service;

import com.example.userRestaurantService.Exceptions.RestaurantAlreadyExistsException;
import com.example.userRestaurantService.Model.Cuisine;
import com.example.userRestaurantService.Exceptions.UserAlreadyExistException;
import com.example.userRestaurantService.Model.CommonUser;
import com.example.userRestaurantService.Model.Restaurant;
import com.example.userRestaurantService.Model.User;
import com.example.userRestaurantService.Model.UserDTO;
import com.example.userRestaurantService.Proxy.UserProxy;
import com.example.userRestaurantService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserRestaurantServiceImpl implements UserRestaurantService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserProxy userProxy;


    @Override
    public User registerUser(CommonUser commonUser) throws UserAlreadyExistException {
        User user = new User(commonUser.getEmailId(), commonUser.getProfilePicture(), commonUser.getFirstName(), commonUser.getLastName(), commonUser.getGender(), commonUser.getPassword(), new ArrayList<>(), new ArrayList<>());

        if (userRepository.findById(user.getEmailId()).isPresent())
        {
            throw new UserAlreadyExistException();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId(commonUser.getEmailId());
        userDTO.setPassword(commonUser.getPassword());
        ResponseEntity<?> responseEntity = userProxy.sendUserObjectToAuth(userDTO);
        return userRepository.insert(user);
    }

    @Override
    public User addFavouriteRestaurant(Restaurant restaurant, String emailId) throws RestaurantAlreadyExistsException {
        List<Restaurant> favRestauant = new ArrayList<>();
        User user = userRepository.findById(emailId).get();
        favRestauant = user.getFavouriteRestaurant();
        if(favRestauant==null)
        {
            user.setFavouriteRestaurant(Arrays.asList(restaurant));
        }
        else if(favRestauant.contains(restaurant)) {
            throw new RestaurantAlreadyExistsException();
        }
        else
        {
            favRestauant.add(restaurant);
            user.setFavouriteRestaurant(favRestauant);
        }
        return userRepository.save(user);
    }

    @Override
    public User addFavouriteCuisine(Cuisine cuisine, String emailId) {
        List<Cuisine> favCuisine= new ArrayList<>();
        User user= userRepository.findById(emailId).get();
        favCuisine=user.getFavouriteCuisine();
        if (favCuisine ==null)
        {
            user.setFavouriteCuisine(Arrays.asList(cuisine));
        }
        else {
            favCuisine.add(cuisine);
            user.setFavouriteCuisine(favCuisine);
        }
        return userRepository.save(user);
    }

    @Override
    public List<Restaurant> getFavouriteRestaurant(String emailId) {
        return userRepository.findById(emailId).get().getFavouriteRestaurant();
    }

    @Override
    public List<Cuisine> getFavouriteCuisine(String emailId) {
        return userRepository.findById(emailId).get().getFavouriteCuisine();
    }

    @Override
    public User deleteFromFavouriteRestaurant(String restaurantName, String emailId) {
        User cust = userRepository.findById(emailId).get();
        cust.getFavouriteRestaurant().removeIf(cus->cus.getRestaurantName().equals(restaurantName));
        return  userRepository.save(cust);
    }

    @Override
    public User deleteFromFavouriteCuisine(String cuisineId, String emailId) {
        User cust = userRepository.findById(emailId).get();
        cust.getFavouriteCuisine().removeIf(cus->cus.getCuisineId().equals(cuisineId));
        return  userRepository.save(cust);
    }

    @Override
    public User getUserDetails(String emailId) {
        return userRepository.findById(emailId).get();
    }
}
