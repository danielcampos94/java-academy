package br.com.neolog.welcomekit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.models.Cart;
import br.com.neolog.welcomekit.models.CartItem;
import br.com.neolog.welcomekit.models.CartItemHolder;
import br.com.neolog.welcomekit.services.CartService;

@RestController
@RequestMapping( value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE )
public class CartController
{
    @Autowired
    private CartService cartService;

    @PostMapping( "/add/item" )
    public ResponseEntity<CartItem> addCartItem(
        @RequestBody @Valid final CartItemHolder item )
    {
        return ResponseEntity.ok().body( cartService.addItem( item ) );
    }

    @PutMapping( "/remove/item/code" )
    public void removeItem(
        @RequestParam( "productCode" ) final Integer productCode )
    {
        cartService.removeItem( productCode );
    }

    @PutMapping( "/remove/item/code/quantity" )
    public ResponseEntity<CartItem> removeQuantityItem(
        @RequestBody final CartItemHolder item )
    {
        return ResponseEntity.ok().body( cartService.removeQuantityItem( item ) );
    }

    @GetMapping( "/search/all/cart/id" )
    public List<CartItem> findAllByCartId(
        @RequestParam( "cartId" ) final Integer id )
    {
        return cartService.findAllItens( id );
    }

    @GetMapping( "/get/current/price" )
    public ResponseEntity<Double> getPrice()
    {
        return ResponseEntity.ok().body( cartService.getCurrentPrice() );
    }

    @PutMapping( "/cancel" )
    public ResponseEntity<Cart> cancelCart()
    {
        final Cart cart = cartService.cancelCart();
        return ResponseEntity.ok().body( cart );
    }

    @PutMapping( "/checkout" )
    public ResponseEntity<Cart> checkout()
    {
        return ResponseEntity.ok().body( cartService.checkout());
    }
}
