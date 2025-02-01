package com.global.user.controller;

import com.global.user.dto.UserDto;
import com.global.user.entity.User;
import com.global.user.exception.ErrorDetails;
import com.global.user.exception.ResourceNotFoundException;
import com.global.user.mapper.UserMapper;
import com.global.user.repository.UserRepository;
import com.global.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long userID) {
        UserDto userDto = userService.findUserById(userID);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/find_all")
    public List<UserDto> findAll(){
        return userService.findAll();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDto> update(@PathVariable Long id,@Valid @RequestBody UserDto updatedUser) {
        // Call the service method and get the updated UserDto
        UserDto updatedUserDto = userService.update(id, updatedUser);

        // Return the updated UserDto wrapped in a ResponseEntity
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
