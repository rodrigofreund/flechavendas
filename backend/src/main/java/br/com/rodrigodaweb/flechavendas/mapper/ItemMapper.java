package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import br.com.rodrigodaweb.flechavendas.dto.ItemDto;
import br.com.rodrigodaweb.flechavendas.model.Item;

public class ItemMapper {
	private ItemMapper() {}
	public static ItemDto toItemDto(Item entity) {
		if(entity == null) {
			return null;
		}
		return ItemDto.builder()
				.codigo(entity.getCodigo())
				.descricao(entity.getDescricao())
				.id(entity.getId())
				.preco(entity.getPreco())
				.quantidade(entity.getQuantidade())
				.st(entity.getSt())
				.ipi(entity.getIpi())
				.descontoSugerido(entity.getDescontoSugerido())
				.build();
	}
	
	public static List<ItemDto> toItemDtoList(List<Item> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Collections.emptyList();
		}
		return entityList.stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
	}
}
