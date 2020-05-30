package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean save(User user) {
        User userDto = userRepository.save(user);
        return userDto == null ? false : true;
    }

    @Override
    public boolean update(User user) {
        User userDto = userRepository.save(user);
        return userDto == null ? false : true;
    }

    @Override
    public void delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User getById(Integer id) {
        if (userRepository.existsById(id)) {
            return userRepository.getOne(id);
        }
        return new User();
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
