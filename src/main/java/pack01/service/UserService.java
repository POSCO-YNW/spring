package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.User;
import pack01.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long save(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}