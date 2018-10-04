package br.com.neolog.welcomekit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.ProductNotFoundException;
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
	

	public Optional<Product> findById(final Integer id) {
		return productRepository.findById(id);
	}

	public Integer save(final Product product) {
		return productRepository.save(product).getId();
	}
	
	public Integer delete(final Integer id){
		productRepository.deleteById(id);
		return id;
	}
	
	public Product updateProduct(final Product product) {
		
		if(!productRepository.existsById(product.getId())){
			throw new ProductNotFoundException("vc é burro CARA");
		}
		return productRepository.save(product);
	}
	
	public Product findByName(final String name){
		return productRepository.findByName(name);
	}
	
	public Product findByCode(final Integer code){
		return productRepository.findByCode(code);
	}
	
	public boolean hasProductByCode(final Integer code){
		return productRepository.existsByCode(code);
	}
	
	
}
