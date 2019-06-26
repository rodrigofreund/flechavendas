package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.ObservacaoPedido;

@Repository
public interface ObservacaoPedidoDAO extends CrudRepository<ObservacaoPedido, Long> {
	@Query("SELECT op FROM ObservacaoPedido op WHERE op.pedido.id = :idPedido ORDER BY op.dataCriacao")
	List<ObservacaoPedido> findByPedidoIdPedido(@Param("idPedido")Long idPedido);
}
