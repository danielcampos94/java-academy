package br.com.neolog.welcomekit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.models.Stock;
import br.com.neolog.welcomekit.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;
	
	public Integer save(final Stock stock){
		return stockRepository.save(stock).getId();
	}
	
	public Stock update(final Stock stock){
		return stockRepository.save(stock);
	}
	
	public Integer delete(final Integer id){
		stockRepository.deleteById(id);
		return id;
	}
	
	
	
}
