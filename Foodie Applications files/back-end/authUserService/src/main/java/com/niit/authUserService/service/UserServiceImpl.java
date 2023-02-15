package com.niit.authUserService.service;

import com.niit.authUserService.exceptions.InvalidDataException;
import com.niit.authUserService.exceptions.UserAlreadyExistsException;
import com.niit.authUserService.model.User;
import com.niit.authUserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService
{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmailId()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User loginCheck(String emailId, String password) throws InvalidDataException {
        User loggedInUser = userRepository.findByEmailIdAndPassword(emailId, password);
        if (loggedInUser == null)
        {
            throw new InvalidDataException();
        }
        return loggedInUser;
    }

    @Override
    public User getUserDetails(String emailId) {
        return userRepository.findById(emailId).get();
    }
}
