package davidmateus.com.alugacasa.controllers;


import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    // dados so pode ser passado no path
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable(value = "id") Long id){
        return userService.getUserById(id);
    }
    //dados pode ser passado no body
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO updateUser){
        return userService.updateUser(id, updateUser);
    }
    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
