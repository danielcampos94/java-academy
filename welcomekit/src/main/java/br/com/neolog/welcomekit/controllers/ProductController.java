package br.com.neolog.welcomekit.controllers;

import java.util.List;

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

import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.services.ProductService;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Integer> saveProduct(@RequestBody final Product product) {
		
		final Integer productId = productService.save(product);
		return new ResponseEntity<Integer>(productId, HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable("id") final Integer id) {
		final Integer productId = productService.delete(id);
		return new ResponseEntity<Integer>(productId, HttpStatus.OK);
	}

	@GetMapping("/searchId/{id}")
	public Product findProductById(@PathVariable("id") final Integer id) {
		return productService.findById(id);
	}

	@GetMapping("searchAll")
	public List<Product> findAllProducts() {
		return productService.findAllProducts();
	}

	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid final Product product) {
		productService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/searchName/{name}")
	public Product findProductByName(@PathVariable("name") final String name){
		return productService.findByName(name);
	}
	
	@GetMapping("/searchCode/{code}")
	public Product findProductByCode(@PathVariable("code") final Integer code){
		return productService.findByCode(code);
	}
}
