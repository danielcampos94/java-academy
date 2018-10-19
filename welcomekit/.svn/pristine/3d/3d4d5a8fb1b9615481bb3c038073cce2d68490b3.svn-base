package br.com.neolog.welcomekit.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.models.CartItem;
import br.com.neolog.welcomekit.services.CartService;

@RestController
@RequestMapping( value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE )
public class CartController
{
    @Autowired
    private CartService cartService;
    
    @PostMapping("/add/item")
    public ResponseEntity<CartItem> addCartItem(@RequestBody @Valid final CartItem item){
        return ResponseEntity.ok().body(cartService.addCartItem( item ));
    }
}
