package br.com.neolog.welcomekit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.models.Stock;
import br.com.neolog.welcomekit.services.StockService;

@RestController
@RequestMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

	@Autowired
	StockService stockService;
	
	@PostMapping("/add")
	public ResponseEntity<Integer> save(@RequestBody final Stock stock) {
		final Integer stockId = stockService.save(stock);
		return new ResponseEntity<Integer>(stockId, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("drop/{id}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable("id") final Integer id) {
		final Integer stockId = stockService.delete(id);
		return new ResponseEntity<Integer>(stockId, HttpStatus.OK);
	}
	
}
