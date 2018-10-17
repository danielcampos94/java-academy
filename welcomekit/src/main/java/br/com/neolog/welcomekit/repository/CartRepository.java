package br.com.neolog.welcomekit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neolog.welcomekit.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>
{

}
