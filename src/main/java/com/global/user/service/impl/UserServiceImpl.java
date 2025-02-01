package com.global.user.service.impl;

import com.global.user.dto.UserDto;
import com.global.user.entity.User;
import com.global.user.exception.EmailAlreadyExistsException;
import com.global.user.exception.ResourceNotFoundException;
import com.global.user.repository.UserRepository;
import com.global.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // Use ModelMapper to map DTO to Entity
        Optional<User> user1 = userRepository.findByEmail(userDto.getEmail());
        if(user1.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for another user");
        }

        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        // Use ModelMapper to map Entity to DTO
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
       return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        // Use ModelMapper for each entity in the list
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(updatedUserDto.getId()).orElseThrow(()-> new ResourceNotFoundException(
                "User" , "id" , id
        ));
        existingUser.setFirstName(updatedUserDto.getFirstName());
        existingUser.setLastName(updatedUserDto.getLastName());
        existingUser.setEmail(updatedUserDto.getEmail());
        User savedUser = userRepository.save(existingUser);

        return modelMapper.map(savedUser , UserDto.class) ;


    }

    @Override
    public void delete(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
               "User","id",id));
        userRepository.delete(user);


    }
}
