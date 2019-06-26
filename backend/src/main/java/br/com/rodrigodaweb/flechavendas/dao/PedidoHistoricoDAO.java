package br.com.rodrigodaweb.flechavendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.PedidoHistorico;

@Repository
public interface PedidoHistoricoDAO extends CrudRepository<PedidoHistorico, Integer>{

}
