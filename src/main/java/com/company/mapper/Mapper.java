package com.company.mapper;

import com.company.dtos.CartDto;
import com.company.dtos.UserDto;
import com.company.entities.Cart;
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
                user.getDateOfBirth(),
                user.getCart()
        );
    }

    public User toUser(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.dayOfBirth()
        );
    }

    public List<UserDto> toUserDtos(List<User> users) {
        return users.stream()
                .map(this::toUserDto)
                .toList();
    }

    public CartDto toCartDto(Cart cart) {
        return new CartDto(
                cart.getCartItems(),
                cart.getTotalPrice(),
                cart.getCartOwner()
        );
    }

    public Cart toCart(CartDto cartDto) {
        return new Cart(
                cartDto.cartItems(),
                cartDto.user()
        );
    }

    public List<CartDto> toCartDtos(List<Cart> carts) {
        return carts.stream()
                .map(this::toCartDto)
                .toList();
    }
}
