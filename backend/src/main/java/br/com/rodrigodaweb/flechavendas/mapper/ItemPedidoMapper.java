package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.ItemPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.ItemPedido;

public class ItemPedidoMapper {
	public static ItemPedidoDto toItemPedidoDto(ItemPedido entity) {
		if(entity == null) {
			return null;
		}
		return ItemPedidoDto.builder()
				.id(entity.getIdItemPedido())
				.codigo(entity.getCodigo())
				.desconto(entity.getDesconto())
				.descricao(entity.getDescricao())
				.idItemPedido(entity.getIdItemPedido())
				.preco(entity.getPreco())
				.precoColocado(entity.getPrecoColocado())
				.precoFinal(entity.getPrecoFinal())
				.quantidade(entity.getQuantidade())
				.quantidadeSolicitada(entity.getQuantidadeSolicitada())
				.st(entity.getSt())
				.ipi(entity.getIpi())
				.build();
	}
	
	public static List<ItemPedidoDto> toItemPedidoDtoList(List<ItemPedido> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(ItemPedidoMapper::toItemPedidoDto).collect(Collectors.toList());
	}
	
	public static List<ItemPedidoDto> toItemPedidoDtoList(Set<ItemPedido> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(ItemPedidoMapper::toItemPedidoDto).collect(Collectors.toList());
	}
}
