package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.controllers.UserController;
import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.repository.UserRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

// model -> repository -> service(camada de negocios) -> controller
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<UserDTO> getAllUsers(){

       List<UserDTO> users = DozerMapper.parseListObject(userRepository.findAll(), UserDTO.class);
       users.forEach(user -> {
           user.add(linkTo(methodOn(UserController.class).getUserById(user.getUserId())).withSelfRel());
       });

        return  users;
    }
    @Transactional
    public UserDTO getUserById(Long userId){
        var entity = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));

        UserDTO user = DozerMapper.parseObject(entity, UserDTO.class);
        user.add(linkTo(methodOn(UserController.class).getUserById(userId)).withSelfRel());
        return user;
    }
    public UserDTO createUser(UserDTO user){

        var entity = DozerMapper.parseObject(user, User.class);
        var dto =  DozerMapper.parseObject(userRepository.save(entity), UserDTO.class);
        dto.add(linkTo(methodOn(UserController.class).getUserById(dto.getUserId())).withSelfRel());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return dto;
    }
    public UserDTO updateUser(Long userId, UserDTO updateUser){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    var dto =DozerMapper.parseObject(userRepository.save(user), UserDTO.class);
                    dto.add(linkTo(methodOn(UserController.class).getUserById(dto.getUserId())).withSelfRel());
                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public void deleteUser(Long userId){
        var entity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(entity);
    }
}
