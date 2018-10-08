package br.com.neolog.welcomekit.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.models.ProductCategory;
import br.com.neolog.welcomekit.services.ProductCategoryService;

@RestController
@RequestMapping( value = "/category", produces = MediaType.APPLICATION_JSON_VALUE )
public class ProductCategoryController
{

    @Autowired
    ProductCategoryService categoryService;

    @PostMapping( "/save" )
    public ResponseEntity<Integer> saveCategory(
        @RequestBody @Valid final ProductCategory category )
    {

        final Integer categoryId = categoryService.save( category );
        return new ResponseEntity<Integer>( categoryId, HttpStatus.CREATED );
    }

    @DeleteMapping( "/remove/{id}" )
    public ResponseEntity<ProductCategory> deleteCategory(
        @PathVariable( "id" ) final Integer id )
    {
        categoryService.delete( id );
        return new ResponseEntity<ProductCategory>( HttpStatus.OK );
    }

    @GetMapping( "/search/id/{id}" )
    public Optional<ProductCategory> findCategoryById(
        @PathVariable( "id" ) final Integer id )
    {
        return categoryService.findById( id );
    }

    @GetMapping( "/search/all" )
    public List<ProductCategory> findAllCategories()
    {
        return categoryService.findAll();
    }

    @PutMapping( "/update" )
    public ResponseEntity<ProductCategory> updateCategory(
        @RequestBody @Valid final ProductCategory category )
    {
        categoryService.update( category );
        return new ResponseEntity<ProductCategory>( HttpStatus.OK );
    }

    @GetMapping( "/search/code/{code}" )
    public ProductCategory findCategoryByCode(
        @PathVariable( "code" ) final int code )
    {
        return categoryService.findByCode( code );
    }

}
