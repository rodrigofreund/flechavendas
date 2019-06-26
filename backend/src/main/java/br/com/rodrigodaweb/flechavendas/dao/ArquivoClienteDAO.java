package br.com.rodrigodaweb.flechavendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.ArquivoCliente;

@Repository
public interface ArquivoClienteDAO
		extends CrudRepository<ArquivoCliente, Integer>, PagingAndSortingRepository<ArquivoCliente, Integer> {
}
