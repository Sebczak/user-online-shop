package com.company.user;

import com.company.Messages;
import com.company.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new IllegalStateException(String.format(Messages.EMAIL_TAKEN, user.getEmail()));
        }

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId));
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(Long userId, String firstname, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(Messages.USER_NOT_FOUND, userId)));

        if (firstname != null && !firstname.isEmpty() && !Objects.equals(user.getFirstName(), firstname)) {
            user.setFirstName(firstname);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userFoundByEmail = userRepository.findUserByEmail(email);

            if (userFoundByEmail.isPresent()) {
                throw new IllegalStateException(String.format(Messages.EMAIL_TAKEN, email));
            }
            user.setEmail(email);
        }
    }
}
