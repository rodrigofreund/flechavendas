package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoDiaDto;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazoDia;

public class IndustriaPrazoDiaMapper {
	public static IndustriaPrazoDia toIndustriaPrazoDia(IndustriaPrazoDiaDto industriaPrazoDiaDto) {
		if(industriaPrazoDiaDto == null) {
			return null;
		}
		return IndustriaPrazoDia.builder()
			.id(industriaPrazoDiaDto.getId())
			.industriaPrazo(IndustriaPrazo.builder().id(industriaPrazoDiaDto.getIdIndustriaPrazo()).build())
			.prazo(industriaPrazoDiaDto.getPrazo())
			.build();
	}
	
	public static IndustriaPrazoDiaDto toIndustriaPrazoDia(IndustriaPrazoDia entity) {
		if(entity == null) {
			return null;
		}
		return IndustriaPrazoDiaDto.builder()
				.id(entity.getId())
				.idIndustriaPrazo(entity.getIndustriaPrazo().getId())
				.prazo(entity.getPrazo())
				.build();
	}
}
