package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Tabela;

@Repository
public interface TabelaDAO extends CrudRepository<Tabela, Long>{
	@Query("from Tabela where idIndustria = :idIndustria and excluido = false")
	public List<Tabela> getTabelasPorIdIndustria(@Param("idIndustria")Integer idIndustria);
}
