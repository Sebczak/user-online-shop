package com.company.mapper;

import com.company.domain.ProductBasketItemDto;
import com.company.domain.ProductDto;
import com.company.entities.Product;
import com.company.entities.ProductBasket;
import com.company.entities.ProductBasketItem;
import com.company.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    private final UserRepository userRepository;

    public Mapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProductBasketItemDto mapToProductBasketItemDto(Product product, Integer quantity, Long userId) {
        ProductBasket productBasket = userRepository.findById(userId).get().getProductBucket();
        return new ProductBasketItemDto(
                product,
                quantity,
                productBasket.getBasketId()
        );
    }

    public ProductBasketItem mapProductBasketItemDtoToProductBasketItem(ProductBasketItemDto productBasketItemDto, Long userId) {
        ProductBasket productBasket = userRepository.findById(userId).get().getProductBucket();
        return new ProductBasketItem(
          productBasketItemDto.product(),
                productBasketItemDto.quantity(),
                productBasket
        );
    }


}
