package com.company.user;

import com.company.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userFoundByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userFoundByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("student with id " + userId + " does not exists");
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(Long userId, String firstname, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("student with id " + userId + " does not exists"));

        if (firstname != null && !firstname.isEmpty() && !Objects.equals(user.getFirstName(), firstname)) {
            user.setFirstName(firstname);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userFoundByEmail = userRepository.findUserByEmail(user.getEmail());

            if (userFoundByEmail.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }
}
