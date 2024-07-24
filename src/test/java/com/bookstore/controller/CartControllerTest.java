package com.bookstore.controller;

import com.bookstore.model.CartItem;
import com.bookstore.service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    public CartControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCartItems() {
        List<CartItem> cartItems = Arrays.asList(new CartItem(1L, 2), new CartItem(2L, 3));
        when(cartService.getCartItems()).thenReturn(cartItems);

        ResponseEntity<List<CartItem>> response = cartController.getCartItems();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testAddToCart() {
        CartItem cartItem = new CartItem(1L, 2);
        when(cartService.addToCart(cartItem)).thenReturn(true);

        ResponseEntity<Void> response = cartController.addToCart(cartItem);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddToCart_BadRequest() {
        CartItem cartItem = new CartItem(1L, 0);
        when(cartService.addToCart(cartItem)).thenReturn(false);

        ResponseEntity<Void> response = cartController.addToCart(cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem(1L, 2);
        when(cartService.updateCartItem(1L, cartItem)).thenReturn(true);

        ResponseEntity<Void> response = cartController.updateCartItem(1L, cartItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateCartItem_BadRequest() {
        CartItem cartItem = new CartItem(1L, 0);
        when(cartService.updateCartItem(1L, cartItem)).thenReturn(false);

        ResponseEntity<Void> response = cartController.updateCartItem(1L, cartItem);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRemoveCartItem() {
        when(cartService.removeCartItem(1L)).thenReturn(true);

        ResponseEntity<Void> response = cartController.removeCartItem(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoveCartItem_NotFound() {
        when(cartService.removeCartItem(1L)).thenReturn(false);

        ResponseEntity<Void> response = cartController.removeCartItem(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
