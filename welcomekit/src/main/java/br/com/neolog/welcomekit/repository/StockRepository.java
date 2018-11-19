package br.com.neolog.welcomekit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.models.Stock;

public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{

    boolean existsByProduct(
        final Product product );

    Stock findByProduct(
        final Product product );

    Stock findByProductId(
        final int idProduct );

    Stock findByProductCode(
        final int codeProduct );

    List<Stock> findByProductPriceLessThanEqualOrderByProductPriceDesc(
        final long price );
}
