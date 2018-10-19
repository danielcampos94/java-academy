package br.com.neolog.welcomekit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neolog.welcomekit.models.Session;

@Repository
public interface SessionRepository
    extends
        JpaRepository<Session,Integer>
{
    Session findByToken(final String token);
    boolean existsByToken(final String token);
}
