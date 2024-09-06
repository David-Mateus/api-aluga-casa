package davidmateus.com.alugacasa.controllers;


import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping
    public User updateUser(@PathVariable UUID id, @RequestBody User updateUser){
        return userService.updateUser(id, updateUser);
    }
    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

}
