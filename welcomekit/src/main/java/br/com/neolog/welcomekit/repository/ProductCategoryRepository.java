package br.com.neolog.welcomekit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.models.ProductCategory;

public interface ProductCategoryRepository
    extends
        JpaRepository<ProductCategory,Integer>
{

    ProductCategory findByCode(
        final int code );

    boolean existsByCode(
        final int code );

    boolean existsByName(
        final String name );
}
