package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.PedidoHistoricoDto;
import br.com.rodrigodaweb.flechavendas.model.PedidoHistorico;

public class PedidoHistoricoMappper {

	private PedidoHistoricoMappper() {
	}

	public static PedidoHistorico toPedidoHistorico(PedidoHistoricoDto dto) {
		return PedidoHistorico.builder()
				.dataHoraAlteracao(dto.getDataHoraAlteracao())
				.id(dto.getId())
				.pedido(PedidoMapper.toPedido(dto.getPedidoDto() == null ? null : dto.getPedidoDto()))
				.status(dto.getStatus())
				.usuario(UsuarioMapper.toUsuario(dto.getUsuarioDto()))
				.build();
	}
}
