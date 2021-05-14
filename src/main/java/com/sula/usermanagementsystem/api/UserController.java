package com.sula.usermanagementsystem.api;

import com.sula.usermanagementsystem.exceptions.ResourceNotFoundException;
import com.sula.usermanagementsystem.model.User;
import com.sula.usermanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


/**
 * REST controller for managing Users.
 */
@CrossOrigin
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/home")
    public String gethome()
    {
        return "<h1>Hello Friends. I'm Sulakkhana</h1>";
    }

    /**
     * {@code POST  /users} : Create a new user.
     *
     * @param user the company to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the user has already an ID.
     * @throws com.sula.usermanagementsystem.exceptions.ResourceAlreadyExistException if the Location URI syntax is incorrect.
     */
    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getAllEmployeeById(@PathVariable Long id) throws ResourceNotFoundException {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Such User"));
        //User user= userRepository.findById(id).get();
        //User user= userRepository.getOne(id);
        return ResponseEntity.ok(user).getBody();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userData) throws ResourceNotFoundException {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Such User"));
        user.setEmail(userData.getEmail());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setRole(userData.getRole());
        User updatedUser=userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }




}
