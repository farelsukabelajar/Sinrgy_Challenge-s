package com.org.binarfud.service;

import com.org.binarfud.dto.UserDTO;
import com.org.binarfud.model.User;
import com.org.binarfud.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(UUID id) {
        return userRepo.findById(id).map(this::convertToDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepo.save(user);
        return convertToDTO(savedUser);
    }

    public Optional<UserDTO> updateUser(UUID id, UserDTO userDetails) {
        return userRepo.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmailAddress(userDetails.getEmailAddress());
            user.setPassword(userDetails.getPassword());
            return convertToDTO(userRepo.save(user));
        });
    }

    public void deleteUser(UUID id) {
        userRepo.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsersId(user.getUsersId());
        dto.setUsername(user.getUsername());
        dto.setEmailAddress(user.getEmailAddress());
        dto.setPassword(user.getPassword());
        // Optionally map orders
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsersId(dto.getUsersId());
        user.setUsername(dto.getUsername());
        user.setEmailAddress(dto.getEmailAddress());
        user.setPassword(dto.getPassword());
        // Optionally map orders
        return user;
    }
}
