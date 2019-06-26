package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import br.com.rodrigodaweb.flechavendas.dto.FiltroPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.Pedido;

public interface PedidoDAOCustom {
	List<Pedido> find(FiltroPedidoDto search);
	Integer countResult(FiltroPedidoDto search);
}
