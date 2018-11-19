package br.com.neolog.welcomekit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.product.IllegalPriceException;
import br.com.neolog.welcomekit.exceptions.product.IllegalWeightException;
import br.com.neolog.welcomekit.exceptions.product.ProductDuplicateCodeException;
import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;
import br.com.neolog.welcomekit.exceptions.stock.StockQuantityNotEmptyException;
import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.models.Stock;
import br.com.neolog.welcomekit.repository.ProductRepository;
import br.com.neolog.welcomekit.repository.StockRepository;

@Service
public class ProductService
{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;

    public Integer save(
        final Product product )
    {
        if( existsProductByCode( product.getCode() ) ) {
            throw new ProductDuplicateCodeException( "CODE=" + product.getCode() + " already exists" );
        }
        if( product.getWeight() < 0 ) {
            throw new IllegalWeightException( "This field must be greater than zero" );
        }
        if( product.getPrice() < 0 ) {
            throw new IllegalPriceException( "This field must be greater than zero" );
        }

        return productRepository.save( product ).getId();
    }

    public Integer delete(
        final Integer id )
    {
        if( ! productRepository.existsById( id ) ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        final Stock stock = stockRepository.findByProductId( id );
        if( stock != null && stock.getQuantity() > 0 ) {
            throw new StockQuantityNotEmptyException( "Stock of this product is not empty" );
        }
        productRepository.deleteById( id );
        return id;
    }

    public List<Product> findAllProducts()
    {
        return productRepository.findAll();
    }

    public Product findById(
        final int id )
    {
        return productRepository.findById( id );
    }

    public Product updateProduct(
        final Product product )
    {
        if( ! productRepository.existsByCode( product.getCode() ) ) {
            throw new ProductNotFoundException( "CODE=" + product.getCode() + " not exists" );
        }
        if( product.getWeight() < 0 ) {
            throw new IllegalWeightException( "This field must be greater than zero" );
        }
        if( product.getPrice() < 0 ) {
            throw new IllegalPriceException( "This field must be greater than zero" );
        }

        return productRepository.save( product );
    }

    public Product findByName(
        final String name )
    {
        return productRepository.findByName( name );
    }

    public Product findByCode(
        final Integer code )
    {
        return productRepository.findByCode( code );
    }

    public List<Product> findAllProductByCategory(
        final int id )
    {
        return productRepository.findAllByCategoryId( id );
    }

    private boolean existsProductByCode(
        final Integer code )
    {
        return productRepository.existsByCode( code );
    }
}
