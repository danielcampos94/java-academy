package br.com.neolog.welcomekit.services;

import static br.com.neolog.welcomekit.CartStatus.ACTIVE;
import static br.com.neolog.welcomekit.CustomerLocal.getCurrentCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.CustomerLocal;
import br.com.neolog.welcomekit.exceptions.item.CartItemIllegalQuantityException;
import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;
import br.com.neolog.welcomekit.models.Cart;
import br.com.neolog.welcomekit.models.CartItem;
import br.com.neolog.welcomekit.models.Customer;
import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.repository.CartItemRepository;
import br.com.neolog.welcomekit.repository.CartRepository;
import br.com.neolog.welcomekit.repository.ProductRepository;

@Service
public class CartService
{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private StockService stockService;

    public Cart save(
        final Cart cart )
    {
        return cartRepository.save( cart );
    }

    public CartItem addCartItem(
        final CartItem item )
    {
        CustomerLocal.setCurrentCustomer( new Customer( 1, "teste5@teste.com.br", "teste", "abc123", false ) );
        final Cart cart = getActiveCart( getCurrentCustomer() );
        final Product product = productRepository.findByCode( item.getProduct().getCode() );
        if( product == null ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        item.setProduct( product );
        item.setCart( cart );

        if( item.getQuantity() > stockService.findQuantityStockByProductCode( product.getCode() ) ) {
            throw new CartItemIllegalQuantityException( "This quantity greater than stock" );
        }
        stockService.decreaseStock( product.getCode(), item.getQuantity() );
        return cartItemRepository.save( item );
    }

    private Cart getActiveCart(
        final Customer customer )
    {
        Cart cart = cartRepository.findByCustomerIdAndCartStatus( customer.getId(), ACTIVE );
        if( cart == null ) {
            cart = save( new Cart( getCurrentCustomer(), 0L, ACTIVE ) );
        }
        return cart;
    }

}
