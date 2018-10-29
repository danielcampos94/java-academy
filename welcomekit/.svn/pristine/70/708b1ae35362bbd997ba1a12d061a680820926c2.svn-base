package br.com.neolog.welcomekit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;
import br.com.neolog.welcomekit.exceptions.stock.StockIllegalQuantityException;
import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.models.Stock;
import br.com.neolog.welcomekit.repository.ProductRepository;
import br.com.neolog.welcomekit.repository.StockRepository;

@Service
public class StockService
{

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    public Stock save(
        final Stock stock )
    {
        if( ! productRepository.existsById( stock.getProduct().getId() ) ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
        final Product product = productRepository.findById( stock.getProduct().getId().intValue() );
        if( hasProductInStock( product ) ) {
            return increaseStock( product.getCode(), stock.getQuantity() );
        }
        return stockRepository.save( new Stock( product, stock.getQuantity() ) );
    }

    public Stock increaseStock(
        final int productCode,
        final int quantity )
    {
        existsProduct( productCode );
        final Product product = productRepository.findByCode( productCode );
        final Stock stock = stockRepository.findByProduct( product );
        return stockRepository.save( new Stock( stock.getId(), product, ( stock.getQuantity() + quantity ) ) );
    }

    public Stock decreaseStock(
        final int productCode,
        final int quantity )
    {
        existsProduct( productCode );
        final Product product = productRepository.findByCode( productCode );
        final Stock stock = stockRepository.findByProduct( product );
        if(stock.getQuantity()< quantity){
            throw new StockIllegalQuantityException( "This quantity greater than stock" );
        }
        return stockRepository.save( new Stock( stock.getId(), product, ( stock.getQuantity() - quantity ) ) );
    }

    public Integer delete(
        final Integer productCode )
    {
        if( ! productRepository.existsByCode( productCode ) ) {
            throw new ProductNotFoundException( "CODE_PRODUCT=" + productCode + " not exists" );
        }

        final Stock stock = stockRepository.findByProductCode( productCode );

        stockRepository.deleteById( stock.getId() );
        return productCode;
    }

    public boolean hasProductInStock(
        final Product product )
    {
        return stockRepository.existsByProduct( product );
    }

    public int findQuantityStockByProductCode(
        final int codeProduct )
    {
        final Stock stock = stockRepository.findByProductCode( codeProduct );
        if( stock == null ) {
            throw new ProductNotFoundException( "CODE_PRODUCT=" + codeProduct + " not exists " );
        }
        return stock.getQuantity();
    }

    private void existsProduct(
        final int productCode )
    {
        if( ! productRepository.existsByCode( productCode ) ) {
            throw new ProductNotFoundException( "This product not exists" );
        }
    }

}
