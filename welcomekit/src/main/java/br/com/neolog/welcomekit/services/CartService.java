package br.com.neolog.welcomekit.services;

import static br.com.neolog.welcomekit.CartStatus.ACTIVE;
import static br.com.neolog.welcomekit.CartStatus.CANCELLED;
import static br.com.neolog.welcomekit.CartStatus.FINISHED;
import static br.com.neolog.welcomekit.CustomerLocal.getCurrentCustomerId;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.welcomekit.exceptions.item.CartItemIllegalQuantityException;
import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;
import br.com.neolog.welcomekit.models.Cart;
import br.com.neolog.welcomekit.models.CartItem;
import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.repository.CartItemRepository;
import br.com.neolog.welcomekit.repository.CartRepository;
import br.com.neolog.welcomekit.repository.CustomerRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    public CartItem addItem(
        final CartItem item )
    {
        final Cart cart = getActiveCart();
        final Product product = productRepository.findByCode( item.getProduct().getCode() );
        if( product == null ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        item.setProduct( product );
        item.setCart( cart );

        final CartItem oldItem = cartItemRepository.findByProductAndCart( product, cart );
        if( oldItem != null ) {
            final int quantity = item.getQuantity();
            item.setQuantity( quantity + oldItem.getQuantity() );
            item.setId( oldItem.getId() );
        }

        verifyStockQuantity( item.getQuantity(), product.getCode() );

        return cartItemRepository.save( item );
    }

    public void removeItem(
        final Integer productCode )
    {
        final Cart cart = getActiveCart();
        final Product product = productRepository.findByCode( productCode );
        if( product == null ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        final CartItem item = cartItemRepository.findByProductAndCart( product, cart );
        cartItemRepository.delete( item );
    }

    public CartItem removeQuantityItem(
        final Integer productCode,
        final int quantity )
    {
        final Cart cart = getActiveCart();
        final Product product = productRepository.findByCode( productCode );
        if( product == null ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        final CartItem item = cartItemRepository.findByProductAndCart( product, cart );
        if( quantity > item.getQuantity() ) {
            throw new CartItemIllegalQuantityException( "This quantity is greater than quantity you have" );
        }
        item.setQuantity( item.getQuantity() - quantity );
        return cartItemRepository.save( item );
    }

    public List<CartItem> findAllItens(
        final Integer id )
    {
        return cartItemRepository.findAllByCartId( id );
    }

    public Double getCurrentPrice()
    {
        final Integer cartId = cartRepository.findByCustomerIdAndCartStatus( getCurrentCustomerId(), ACTIVE ).getId();
        final List<CartItem> list = findAllItens( cartId );
        return list.stream().mapToDouble( item -> item.getProduct().getPrice() * item.getQuantity() ).sum();
    }

    public Cart cancelCart()
    {
        final Cart cart = cartRepository.findByCustomerIdAndCartStatus( getCurrentCustomerId(), ACTIVE );
        cart.setCartStatus( CANCELLED );
        return cart;
    }

    @Transactional
    public void checkout()
    {
        final Cart cart = cartRepository.findByCustomerIdAndCartStatus( getCurrentCustomerId(), ACTIVE );
        final List<CartItem> list = findAllItens( cart.getId() );

        final List<CartItem> invalidQuantity = list.stream()
            .filter( item -> item.getQuantity() > stockService.findQuantityStockByProductCode( item.getProduct().getCode() ) )
            .collect( toList() );

        final List<String> errors = new ArrayList<>();
        if( ! invalidQuantity.isEmpty() ) {
            invalidQuantity.stream().forEach( item -> errors.add( item.getProduct().getName() + "--> Not have this quantity in stock" ) );
            throw new CartItemIllegalQuantityException( errors );
        }

        cart.setTotalValue( getCurrentPrice() );
        cart.setCartStatus( FINISHED );
        cartRepository.save( cart );
        list.stream().forEach( item -> stockService.decreaseStock( item.getProduct().getCode(), item.getQuantity() ) );

    }

    public Cart getActiveCart()
    {
        final Cart cart = cartRepository.findByCustomerIdAndCartStatus( getCurrentCustomerId(), ACTIVE );
        if( cart != null ) {
            return cart;
        }
        return cartRepository.save( new Cart( customerRepository.findById( getCurrentCustomerId() ), 0L, ACTIVE ) );
    }

    private boolean verifyStockQuantity(
        final int quantity,
        final Integer productCode )
    {
        if( quantity > stockService.findQuantityStockByProductCode( productCode ) ) {
            throw new CartItemIllegalQuantityException( "This quantity is greater than stock" );
        }
        return true;
    }

}
