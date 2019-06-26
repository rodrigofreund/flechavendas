package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.stream.Collectors;

import br.com.rodrigodaweb.flechavendas.dto.TabelaDto;
import br.com.rodrigodaweb.flechavendas.model.Tabela;

public class TabelaMapper {
	public static TabelaDto toTabelaDto(Tabela entity) {
		if(entity == null) {
			return null;
		}
		return TabelaDto.builder()
				.data(entity.getData())
				.id(entity.getId())
				.nome(entity.getNome())
				.itens(entity.getItens() == null ? null : ItemMapper.toItemDtoList(entity.getItens().stream().sorted().collect(Collectors.toList())))
				.build();
	}
}
