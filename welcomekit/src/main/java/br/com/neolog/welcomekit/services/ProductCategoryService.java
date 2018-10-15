package br.com.neolog.welcomekit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.category.CategoryDuplicateCodeException;
import br.com.neolog.welcomekit.exceptions.category.CategoryDuplicateNameException;
import br.com.neolog.welcomekit.exceptions.category.CategoryNotEmptyException;
import br.com.neolog.welcomekit.exceptions.category.CategoryNotFoundException;
import br.com.neolog.welcomekit.models.ProductCategory;
import br.com.neolog.welcomekit.repository.ProductCategoryRepository;
import br.com.neolog.welcomekit.repository.ProductRepository;

@Service
public class ProductCategoryService
{

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Integer save(
        final ProductCategory category )
    {
        if( categoryRepository.existsByCode( category.getCode() ) ) {
            throw new CategoryDuplicateCodeException( "CODE=" + category.getCode() + " already exists" );
        }
        if( categoryRepository.existsByName( category.getName() ) ) {
            throw new CategoryDuplicateNameException( "NAME=" + category.getName() + " already exists" );
        }
        return categoryRepository.save( category ).getId();
    }

    public void delete(
        final int code )
    {
        if( ! categoryRepository.existsByCode( code ) ) {
            throw new CategoryNotFoundException( "CODE=" + code + " not exists" );
        }
        if( productRepository.existsByCategoryCode( code ) ) {
            throw new CategoryNotEmptyException( "This category contains products" );
        }
        final ProductCategory productCategory = categoryRepository.findByCode( code );
        categoryRepository.delete( productCategory );
    }

    public ProductCategory findById(
        final Integer id )
    {
        final Optional<ProductCategory> productCategory = categoryRepository.findById( id );
        if( ! productCategory.isPresent() ) {
            throw new CategoryNotFoundException( "ID=" + id + "not exists" );
        }
        return productCategory.get();
    }

    public List<ProductCategory> findAll()
    {
        return categoryRepository.findAll();
    }

    public ProductCategory update(
        final ProductCategory category )
    {
        if( ! categoryRepository.existsById( category.getId() ) ) {
            throw new CategoryNotFoundException( "ID=" + category.getId() + " not exists" );
        }
        return categoryRepository.save( category );
    }

    public ProductCategory findByCode(
        final int code )
    {
        return categoryRepository.findByCode( code );
    }

}
