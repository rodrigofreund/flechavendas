package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.ItemPedido;

@Repository
public interface ItemPedidoDAO extends CrudRepository<ItemPedido, Long> {
	@Query("from ItemPedido where idPedido = :idPedido")
	public List<ItemPedido> getItensBydIdPedido(@Param("idPedido") Long IdPedido);

	@Query("FROM ItemPedido "
			+ "WHERE codigo = :codigo "
			+ "AND pedido.cliente.id = :idCliente "
			+ "AND pedido.status = 5 "
			+ "AND pedido.usuario.id = :idUsuario "
			+ "ORDER BY pedido.dataPedido DESC")
	public List<ItemPedido> getUltimasVendasItem(
			@Param("idCliente") Integer idCliente,
			@Param("codigo") String codigo,
			@Param("idUsuario") Integer idUsuario);
}
