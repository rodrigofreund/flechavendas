package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.PerfilDto;
import br.com.rodrigodaweb.flechavendas.model.Perfil;

public class PerfilMapper {
	public static PerfilDto toPerfilDto(Perfil perfil) {
		if(perfil == null) {
			return null;
		}
		return PerfilDto.builder()
				.id(perfil.getId())
				.nome(perfil.getNome())
				.build();
	}
	
	public static List<PerfilDto> toPerfilDtoList(List<Perfil> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(PerfilMapper::toPerfilDto).collect(Collectors.toList());
	}
}
