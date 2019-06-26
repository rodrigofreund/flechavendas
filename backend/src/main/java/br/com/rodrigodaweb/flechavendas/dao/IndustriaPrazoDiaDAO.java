package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazoDia;

@Repository
public interface IndustriaPrazoDiaDAO extends CrudRepository<IndustriaPrazoDia, Integer>{

	@Query("select ipd from IndustriaPrazoDia ipd where ipd.industriaPrazo.id = :idIndustriaPrazo")
	List<IndustriaPrazoDia> buscaPorIdIndustriaPrazo(@Param("idIndustriaPrazo")Integer idIndustriaPrazo);
}
