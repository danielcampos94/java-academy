package br.com.neolog.welcomekit.optimization.problem;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.exceptions.stock.StockQuantityEmptyException;
import br.com.neolog.welcomekit.models.Stock;
import br.com.neolog.welcomekit.optimization.Item;
import br.com.neolog.welcomekit.repository.StockRepository;

@Component
public class ProblemFactory
{
    @Autowired
    private StockRepository stockRepository;

    public ProblemImpl getProblem(
        final long targetValue )
    {
        final List<Stock> stockItems = stockRepository.findByProductPriceLessThanEqualOrderByProductPriceDesc( targetValue );

        if( stockItems.isEmpty() ) {
            throw new StockQuantityEmptyException( "Stock Empty" );
        }

        final List<Item> problemItens = new LinkedList<>();

        for( final Stock stock : stockItems ) {
            final int stockQuantity = stock.getQuantity();
            if( stockQuantity < 1 ) {
                continue;
            }
            final long longPrice = Math.round( stock.getProduct().getPrice() * 10 * 10 );
            if( targetValue >= longPrice ) {

                final int quantity = Long.valueOf( targetValue / longPrice ).intValue();
                final int code = stock.getProduct().getCode();

                if( quantity <= stockQuantity ) {
                    problemItens.add( Item.create( code, longPrice, quantity ) );
                } else {
                    problemItens.add( Item.create( code, longPrice, stockQuantity ) );
                }
            }
        }
        return ProblemImpl.create( problemItens, targetValue );
    }
}
