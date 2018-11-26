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

    public Problem getProblem(
        final long targetValue )
    {
        final List<Stock> stockItems = stockRepository.findByProductPriceLessThanEqualOrderByProductPriceDesc( targetValue );

        if( stockItems.isEmpty() ) {
            throw new StockQuantityEmptyException( "Stock Empty" );
        }

        final List<Item> problemItems = new LinkedList<>();

        for( final Stock stock : stockItems ) {
            final int stockQuantity = stock.getQuantity();
            if( stockQuantity < 1 ) {
                continue;
            }
            final long longPrice = Math.round( stock.getProduct().getPrice() * 10 * 10 );

            final int quantity = Long.valueOf( targetValue / longPrice ).intValue();
            final int code = stock.getProduct().getCode();

            final int possibleQuantity = Math.min( stockQuantity, quantity );
            problemItems.add( Item.create( code, longPrice, possibleQuantity ) );
        }
        return ProblemImpl.create( problemItems, targetValue );
    }
}
