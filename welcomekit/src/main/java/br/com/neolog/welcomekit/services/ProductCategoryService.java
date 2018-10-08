package br.com.neolog.welcomekit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.category.CategoryDuplicateCodeException;
import br.com.neolog.welcomekit.exceptions.category.CategoryDuplicateNameException;
import br.com.neolog.welcomekit.exceptions.category.CategoryNotFoundException;
import br.com.neolog.welcomekit.models.ProductCategory;
import br.com.neolog.welcomekit.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService
{

    @Autowired
    ProductCategoryRepository categoryRepository;

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
        final Integer id )
    {
        categoryRepository.deleteById( id );
    }

    public Optional<ProductCategory> findById(
        final Integer id )
    {
        return categoryRepository.findById( id );
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
