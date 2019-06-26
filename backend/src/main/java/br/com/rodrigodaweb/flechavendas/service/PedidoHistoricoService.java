package br.com.rodrigodaweb.flechavendas.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.PedidoHistoricoDAO;
import br.com.rodrigodaweb.flechavendas.dto.PedidoHistoricoDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.mapper.PedidoHistoricoMappper;
import br.com.rodrigodaweb.flechavendas.mapper.UsuarioMapper;
import br.com.rodrigodaweb.flechavendas.model.Pedido;
import br.com.rodrigodaweb.flechavendas.model.PedidoHistorico;

@Service
public class PedidoHistoricoService {

	@Autowired
	private PedidoHistoricoDAO dao;

	public PedidoHistorico salvaHistorico(UsuarioDto usuarioAlteracao, Pedido pedido) {
		PedidoHistorico pedidohistorico = PedidoHistorico.builder()
				.dataHoraAlteracao(new Date())
				.pedido(pedido)
				.status(pedido.getStatus())
				.usuario(UsuarioMapper.toUsuario(usuarioAlteracao))
				.build();
		return dao.save(pedidohistorico);
	}

	public PedidoHistorico salva(PedidoHistoricoDto dto) {
		return dao.save(PedidoHistoricoMappper.toPedidoHistorico(dto));
	}
}
