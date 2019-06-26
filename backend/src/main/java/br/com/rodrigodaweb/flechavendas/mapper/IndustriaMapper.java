package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaDto;
import br.com.rodrigodaweb.flechavendas.model.Industria;

public class IndustriaMapper {
	public static IndustriaDto industriaIndustriaDto(Industria industria) {
		if(industria == null) {
			return null;
		}
		IndustriaDto industriaDto = new IndustriaDto();
		industriaDto.setId(industria.getId());
		industriaDto.setNome(industria.getNome());
		industriaDto.setImagem(industria.getImagem());
		industriaDto.setTelefone(industria.getTelefone());
		return industriaDto;
	}
}
