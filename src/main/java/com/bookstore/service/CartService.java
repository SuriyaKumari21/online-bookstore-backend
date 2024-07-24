package com.bookstore.service;

import com.bookstore.model.CartItem;
import com.bookstore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public boolean addToCart(CartItem cartItem) {
        if (cartItem != null && cartItem.getQuantity() > 0) {
            cartRepository.save(cartItem);
            return true;
        }
        return false;
    }

    public boolean updateCartItem(Long id, CartItem updatedCartItem) {
        if (cartRepository.existsById(id) && updatedCartItem.getQuantity() > 0) {
            updatedCartItem.setId(id);
            cartRepository.save(updatedCartItem);
            return true;
        }
        return false;
    }

    public boolean removeCartItem(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
