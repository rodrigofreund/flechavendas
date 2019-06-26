package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.IndustriaCliente;

@Repository
public interface IndustriaClienteDAO extends CrudRepository<IndustriaCliente, Integer> {

	@Query("select count(*) from IndustriaCliente where idCliente=:idCliente and idIndustria=:idIndustria")
	public Integer estaClienteCadastradoIndustria(@Param("idIndustria") Integer idIndustria,
			@Param("idCliente") Integer idCliente);
	
	@Query("from IndustriaCliente where idCliente=:idCliente")
	public List<IndustriaCliente> buscaIndustriaCliente(@Param("idCliente") Integer idCliente);
}
