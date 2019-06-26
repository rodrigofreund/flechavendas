package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Item;

@Repository
public interface ItemDAO extends CrudRepository<Item, Long>{
	@Query("from Item where idTabela = :idTabela order by descricao")
	public List<Item> getItensPorIdTabela(@Param("idTabela")Long idTabela);
}
