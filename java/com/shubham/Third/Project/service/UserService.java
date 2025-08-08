package com.shubham.Third.Project.service;

import com.shubham.Third.Project.entity.UserEntity;
import com.shubham.Third.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity register(UserEntity user) {
        return userRepository.save(user);
    }

    public Optional<UserEntity> login(String email, String password) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getByEmail(String email) {
    return userRepository.findByEmail(email);
    }
}
