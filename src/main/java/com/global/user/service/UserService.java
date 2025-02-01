package com.global.user.service;

import com.global.user.dto.UserDto;
import com.global.user.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto findUserById(Long id);

    List<UserDto> findAll();

    UserDto update(Long id, UserDto updatedUserDto);

    void delete(Long id);


}
