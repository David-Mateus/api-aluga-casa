package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.model.User;
import davidmateus.com.alugacasa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(UUID userId){
        return userRepository.findById(userId);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(UUID userId, User updateUser){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void deleteUser(UUID userId){
        userRepository.deleteById(userId);
    }
}
