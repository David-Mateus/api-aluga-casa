package davidmateus.com.alugacasa.controllers;


import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // dados so pode ser passado no path
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    //dados pode ser passado no body
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser){
        return userService.updateUser(id, updateUser);
    }
    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
