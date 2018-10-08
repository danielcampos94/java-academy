package br.com.neolog.welcomekit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.models.Product;

public interface ProductRepository
    extends
        JpaRepository<Product,Integer>
{

    Product findByCode(
        final int code );

    Product findByName(
        final String name );

    boolean existsByCode(
        final Integer code );

    Product findById(
        final int id );

}
