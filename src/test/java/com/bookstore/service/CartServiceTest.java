package com.bookstore.service;

import com.bookstore.model.CartItem;
import com.bookstore.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    public CartServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCartItems() {
        List<CartItem> cartItems = Arrays.asList(new CartItem(1L, 2), new CartItem(2L, 3));
        when(cartRepository.findAll()).thenReturn(cartItems);

        List<CartItem> result = cartService.getCartItems();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddToCart() {
        CartItem cartItem = new CartItem(1L, 2);
        when(cartRepository.save(cartItem)).thenReturn(cartItem);

        boolean result = cartService.addToCart(cartItem);
        assertEquals(true, result);
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem(1L, 2);
        when(cartRepository.existsById(1L)).thenReturn(true);
        when(cartRepository.save(cartItem)).thenReturn(cartItem);

        boolean result = cartService.updateCartItem(1L, cartItem);
        assertEquals(true, result);
    }

    @Test
    public void testRemoveCartItem() {
        when(cartRepository.existsById(1L)).thenReturn(true);

        boolean result = cartService.removeCartItem(1L);
        assertEquals(true, result);
    }
}
