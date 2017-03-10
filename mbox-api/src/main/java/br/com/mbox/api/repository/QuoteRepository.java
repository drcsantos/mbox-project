package br.com.mbox.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.mbox.api.domain.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

}