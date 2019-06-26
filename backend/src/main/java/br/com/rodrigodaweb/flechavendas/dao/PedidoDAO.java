package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Pedido;

@Repository
public interface PedidoDAO extends CrudRepository<Pedido, Long>, PedidoDAOCustom{
	
	@Query("from Pedido where idIndustria = :idIndustria and status != 0")
	List<Pedido> findPedidosPorIndustria(@Param("idIndustria")Integer idIndustria);

	@Query("from Pedido where idIndustria = :idIndustria and status = :statusPedido and status != 0")
	List<Pedido> findPedidosPorIndustriaStatus(@Param("idIndustria")Integer idIndustria, @Param("statusPedido")Integer statusPedido);

	@Query("from Pedido where idIndustria = :idIndustria and idUsuario = :idVendedor and status = :statusPedido and status != 0")
	List<Pedido> findPedidosPorVendedorIndustriaStatus(@Param("idVendedor")Integer idVendedor, @Param("idIndustria") Integer idIndustria, @Param("statusPedido") Integer statusPedido);
	
	@Query("from Pedido where idIndustria = :idIndustria and idUsuario = :idVendedor and status != 0")
	List<Pedido> findPedidosPorVendedorIndustria(@Param("idVendedor")Integer idVendedor, @Param("idIndustria") Integer idIndustria);
	
	@Query("select count(*) from Pedido where status = :idStatus")
	Integer getNumeroPedidosPorStatus(@Param("idStatus")Integer idStatus);
	
	@Query("select count(*) from Pedido where status = :idStatus and idUsuario = :idUsuario")
	Integer getNumeroPedidosNegados(@Param("idStatus")Integer idStatus, @Param("idUsuario")Integer idUsuario);

}
