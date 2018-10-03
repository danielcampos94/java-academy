package br.com.neolog.welcomekit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.welcomekit.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{
}
