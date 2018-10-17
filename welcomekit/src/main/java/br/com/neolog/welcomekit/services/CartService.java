package br.com.neolog.welcomekit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.models.Cart;
import br.com.neolog.welcomekit.repository.CartRepository;

@Service
public class CartService
{
    @Autowired
    private CartRepository cartRepository;

    public Integer save(
        final Cart cart )
    {
        return cartRepository.save( cart ).getId();
    }
    
}
