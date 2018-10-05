package br.com.neolog.welcomekit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.product.ProductDuplicateCodeException;
import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;
import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
//	private static final Logger logger = LogManager.getLogger(ProductService.class);
	

	public List<Product> findAllProducts() {
		final List<Product> list = productRepository.findAll();
		return list;
	}
	

	public Product findById(final int id) {
		return productRepository.findById(id);
	}

	public Integer save(final Product product) {
		if(existsProductByCode(product.getCode())){
			throw new ProductDuplicateCodeException("CODE=" + product.getCode() + " already exists");
		}
		return productRepository.save(product).getId();
	}
	
	public Integer delete(final Integer id){
		productRepository.deleteById(id);
		return id;
	}
	
	public Product updateProduct(final Product product) {
		
		if(!productRepository.existsById(product.getId())){
			throw new ProductNotFoundException("ID=" + product.getId() + " not exists");
		}
		return productRepository.save(product);
	}
	
	public Product findByName(final String name){
		return productRepository.findByName(name);
	}
	
	public Product findByCode(final Integer code){
		return productRepository.findByCode(code);
	}
	
	
	public boolean existsProductByCode(final Integer code){
		return productRepository.existsByCode(code);
	}
	
	public boolean hasProductById(final Integer id){
		return productRepository.existsById(id);
	}
	
	
	
}
