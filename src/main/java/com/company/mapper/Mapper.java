package com.company.mapper;

import com.company.dtos.UserDto;
import com.company.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth()
        );
    }

    public User toUser(UserDto userDto) {
        return new User(
                userDto.userId(),
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.dayOfBirth()
        );
    }

    public List<UserDto> toUserDtos(List<User> users) {
        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
