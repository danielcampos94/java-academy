package br.com.neolog.welcomekit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.models.ProductCategory;
import br.com.neolog.welcomekit.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	ProductCategoryRepository categoryRepository;
	
	public ProductCategory save(final ProductCategory category){
		return categoryRepository.save(category);
	}
	
	public void delete(final Integer id){
		categoryRepository.deleteById(id);
	}
	
	public Optional<ProductCategory> findById(final Integer id){
		return categoryRepository.findById(id);
	}
	
	public List<ProductCategory> findAll(){
		return categoryRepository.findAll();
	}
	
	public ProductCategory update(final ProductCategory category){
		return categoryRepository.save(category);
	}
	
	public ProductCategory findByCode(final int code){
		return categoryRepository.findByCode(code);
	}
	
}

