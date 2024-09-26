package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// model -> repository -> service(camada de negocios) -> controller
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<UserDTO> getAllUsers(){

        return DozerMapper.parseListObject(userRepository.findAll(), UserDTO.class);
    }
    @Transactional
    public UserDTO getUserById(Long userId){
        var entity = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
        return DozerMapper.parseObject(entity, UserDTO.class);
    }
    public UserDTO createUser(UserDTO user){

        var entity = DozerMapper.parseObject(user, User.class);
        return DozerMapper.parseObject(userRepository.save(entity), UserDTO.class);
    }
    public UserDTO updateUser(Long userId, UserDTO updateUser){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    return DozerMapper.parseObject(userRepository.save(user), UserDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public void deleteUser(Long userId){
        var entity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(entity);
    }
}
