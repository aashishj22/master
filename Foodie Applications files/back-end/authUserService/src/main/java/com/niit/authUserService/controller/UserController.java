package com.niit.authUserService.controller;

import com.niit.authUserService.exceptions.InvalidDataException;
import com.niit.authUserService.exceptions.UserAlreadyExistsException;
import com.niit.authUserService.model.User;
import com.niit.authUserService.service.IUserService;
import com.niit.authUserService.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/authservice")
public class UserController
{
    @Autowired
    private IUserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    //http://localhost:8085/authservice/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        user.setUserRole("USER");
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) throws InvalidDataException {
        User result=userService.loginCheck(user.getEmailId(),user.getPassword());
        if(result!=null)
        {
            Map<String,String> key=securityTokenGenerator.generateToken(user);
            return new ResponseEntity<>(key,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Authentication failed",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{emailId}")
    public ResponseEntity<?> getUserDetails(@PathVariable String emailId)
    {
        return new ResponseEntity<>(userService.getUserDetails(emailId), HttpStatus.OK);
    }
}
