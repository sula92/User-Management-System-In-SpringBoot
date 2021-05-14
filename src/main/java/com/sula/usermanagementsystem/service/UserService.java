package com.sula.usermanagementsystem.service;

import com.sula.usermanagementsystem.model.User;
import com.sula.usermanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for managing Users.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepositor;

    public Boolean checkEmailAlreadyExist(String email){
        User user=userRepositor.findOneByEmailIgnoreCase(email).get();
        if(user==null||user.getId()<=0){
            return true;
        }
        return false;
    }
}
