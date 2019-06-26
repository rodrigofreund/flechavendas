package br.com.rodrigodaweb.flechavendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.ArquivoTabela;

@Repository
public interface ArquivoTabelaDAO extends CrudRepository<ArquivoTabela, Long>{

}
