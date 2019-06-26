package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.EstadoDto;
import br.com.rodrigodaweb.flechavendas.model.Estado;

public class EstadoMapper {
	public static EstadoDto toEstadoDto(Estado entity) {
		if(entity == null) {
			return null;
		}
		return EstadoDto.builder().id(entity.getId()).nome(entity.getNome()).sigla(entity.getSigla()).build();
	}
}
