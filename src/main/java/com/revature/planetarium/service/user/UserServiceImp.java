package com.revature.planetarium.service.user;


import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

public class UserServiceImp implements UserService {
    
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String createUser(User newUser) {
        if(newUser.getUsername().length() <= 5 || newUser.getUsername().length() >=30){
            throw new UserFail("Invalid username");
        }

        if(newUser.getPassword().length() <= 5 || newUser.getPassword().length() >=30){
            throw new UserFail("Invalid password");
        }

        if(newUser.getUsername().substring(0,1).matches("[0-9]")){
            throw new UserFail("Invalid username");
        }

        if(newUser.getPassword().substring(0,1).matches("[0-9]")){
            throw new UserFail("Invalid password");
        }

        if(newUser.getUsername().matches(".*[/~`\\\\?.,=+*^&%$#@!;'\" ].*")){
            throw new UserFail("Invalid username");
        }

        if(newUser.getPassword().matches(".*[/~`\\\\?.,=+*^&%$#@!;'\" ].*")){
            throw new UserFail("Invalid password");
        }

        if(!newUser.getPassword().matches(".*[A-Z]+.*")){
            throw new UserFail("Invalid password");
        }

        if(!newUser.getPassword().matches(".*[a-z]+.*")){
            throw new UserFail("Invalid password");
        }

        if(!newUser.getPassword().matches(".*[0-9]+.*")) {
            throw new UserFail("Invalid password");
        }

        if(userDao.findUserByUsername(newUser.getUsername()).isPresent()){
            throw new UserFail("Invalid username");
        }
        Optional<User> createdUser = userDao.createUser(newUser);

        if (createdUser.isPresent()) {
            return "Created user with username " + createdUser.get().getUsername() + " and password " + createdUser.get().getPassword();
        } else {
            throw new UserFail("Failed to create user, please try again");
        }

    }

    @Override
    public User authenticate(User credentials) {
        Optional<User> foundUser = userDao.findUserByUsername(credentials.getUsername());
        if (foundUser.isPresent()) {
            if (foundUser.get().getPassword().equals(credentials.getPassword())) {
                foundUser.get().setPassword("");
                return foundUser.get();
            }
        }
        throw new UserFail("Invalid credentials");
    }

}
