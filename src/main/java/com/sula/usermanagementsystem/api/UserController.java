package com.sula.usermanagementsystem.api;

import com.sula.usermanagementsystem.exceptions.EmailAlreadyExistException;
import com.sula.usermanagementsystem.exceptions.ResourceNotFoundException;
import com.sula.usermanagementsystem.model.User;
import com.sula.usermanagementsystem.repository.UserRepository;
import com.sula.usermanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * REST controller for managing Users.
 */
@CrossOrigin
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * {@code GET /users} : get all users.
     *
     * @return the status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * {@code GET /home} : get home page.
     *
     * @return the status {@code 200 (OK)} and with body of home page.
     */
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
     * @throws com.sula.usermanagementsystem.exceptions.ResourceAlreadyExistException if the User already exist in the Data Base.
     */
    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        return userRepository.save(user);
    }

    /**
     * {@code GET /users/:id} : get the user base on the id.
     *
     * @param id the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws ResourceNotFoundException {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Such User"));
        //User user= userRepository.findById(id).get();
        //User user= userRepository.getOne(id);
        return ResponseEntity.ok(user).getBody();
    }

    /**
     * {@code PUT /users} : Updates an existing User.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     * @throws com.sula.usermanagementsystem.exceptions.EmailAlreadyExistException {@code 400 (Bad Request)} if the email is already in use.
     * @throws com.sula.usermanagementsystem.exceptions.ResourceAlreadyExistException {@code 400 (Bad Request)} if the id is Not Found.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userData) throws ResourceNotFoundException, EmailAlreadyExistException {
        User user= userRepository.findById(userData.getId()).get();
        System.out.println("xxxxxxxxxxxx"+user.getId());
        if(user.getId()<=0){
            throw new ResourceNotFoundException("No such User in the DB");
        }
        Boolean emailExist=userService.checkEmailAlreadyExist(userData.getEmail());
        if(emailExist){
            throw new EmailAlreadyExistException("The Email Already in Use");
        }
        user.setEmail(userData.getEmail());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setRole(userData.getRole());
        User updatedUser=userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * {@code DELETE /users/:id} : delete the User.
     *
     * @param id of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users/{id}")
    public Map<String,Boolean> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Such User"));
        userRepository.delete(user);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response).getBody();
    }




}
