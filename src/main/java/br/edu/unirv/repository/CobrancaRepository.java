package br.edu.unirv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unirv.model.Cobranca;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long>{


	Cobranca findByTxid(String txid);
	
	
}
