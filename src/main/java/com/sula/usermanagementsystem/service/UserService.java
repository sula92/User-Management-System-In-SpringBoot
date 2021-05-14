package com.sula.usermanagementsystem.service;

import org.springframework.stereotype.Service;

/**
 * Service for managing Users.
 */
@Service
public class UserService {

    public Boolean checkEmailAlreadyExist(String email){
        return false;
    }
}
