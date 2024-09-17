package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.repository.TenantRepository;
import davidmateus.com.alugacasa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// model -> repository -> service(camada de negocios) -> controller
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }
    public User createUser(User user){
        user.setUserId(null);
        return userRepository.save(user);
    }
    public User updateUser(Long userId, User updateUser){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
