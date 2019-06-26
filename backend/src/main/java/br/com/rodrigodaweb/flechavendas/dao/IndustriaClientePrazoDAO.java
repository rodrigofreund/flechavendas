package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.IndustriaClientePrazo;

@Repository
public interface IndustriaClientePrazoDAO extends CrudRepository<IndustriaClientePrazo, Integer>{
	List<IndustriaClientePrazo> findByIndustriaClienteId(Integer idIndustriaCliente);

	List<IndustriaClientePrazo> findByIndustriaClienteClienteIdAndIndustriaClienteIndustriaId(Integer idCliente,
			Integer idIndustria);

	List<IndustriaClientePrazo> findByIndustriaClienteIndustriaId(Integer idIndustria);
}
