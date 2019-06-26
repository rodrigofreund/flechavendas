package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.ObservacaoPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.ObservacaoPedido;
import br.com.rodrigodaweb.flechavendas.model.Pedido;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public class ObservacaoPedidoMapper {
	public static List<ObservacaoPedido> toObservacaoPedidoList(List<ObservacaoPedidoDto> dtoList) {
		if(CollectionUtils.isEmpty(dtoList)) {
			return Lists.newArrayList();
		}
		return dtoList.stream().map(item -> toObservacaoPedido(item)).collect(Collectors.toList());
	}
	public static ObservacaoPedido toObservacaoPedido(ObservacaoPedidoDto dto) {
		if(dto == null) {
			return null;
		}
		return ObservacaoPedido.builder()
				.dataCriacao(dto.getDataCriacao())
				.dataLeitura(dto.getDataLeitura())
				.idObservacao(dto.getIdObservacao())
				.usuario(Usuario.builder().id(dto.getIdUsuario()).build())
				.lido(dto.isLido())
				.observacao(dto.getObservacao())
				.pedido(Pedido.builder().id(dto.getIdPedido()).build())
				.build();
	}

	public static List<ObservacaoPedidoDto> toObservacaoPedidoDtoList(List<ObservacaoPedido> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(entity -> toObservacaoPedidoDto(entity)).collect(Collectors.toList());
	}

	private static ObservacaoPedidoDto toObservacaoPedidoDto(ObservacaoPedido entity) {
		if(entity == null) {
			return null;
		}
		return ObservacaoPedidoDto.builder()
				.dataCriacao(entity.getDataCriacao())
				.dataLeitura(entity.getDataLeitura())
				.idObservacao(entity.getIdObservacao())
				.idPedido(entity.getPedido() == null ? null : entity.getPedido().getId())
				.idUsuario(entity.getUsuario() == null ? null : entity.getUsuario().getId())
				.nomeUsuario(entity.getUsuario() == null ? null : entity.getUsuario().getNome())
				.lido(entity.isLido())
				.observacao(entity.getObservacao())
				.build();
	}
}
