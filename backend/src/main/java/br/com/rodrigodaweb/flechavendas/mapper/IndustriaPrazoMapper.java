package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;

public class IndustriaPrazoMapper {
	public static IndustriaPrazo toIndustriaPrazo(Industria industria, IndustriaPrazoDto dto) {
		if(dto == null) {
			return null;
		}
		return IndustriaPrazo.builder()
			.codigo(dto.getCodigo())
			.descricao(dto.getDescricao())
			.id(dto.getId())
			.industria(industria)
			.excluido(dto.isExcluido())
			.build();
	}
	
	public static IndustriaPrazoDto toIndustriaPrazoDto(IndustriaPrazo entity) {
		if(entity == null) {
			return null;
		}
		return IndustriaPrazoDto.builder()
			.id(entity.getId())
			.idIndustria(entity.getIndustria() == null ? null : entity.getIndustria().getId())
			.codigo(entity.getCodigo())
			.descricao(entity.getDescricao())
			.excluido(entity.isExcluido())
			.build();
	}
	
	public static IndustriaPrazoPedidoDto toIndustriaPrazoPedidoDto(IndustriaPrazoDto industriaPrazoDto) {
		if(industriaPrazoDto == null) {
			return null;
		}
		return IndustriaPrazoPedidoDto.builder()
				.idIndustriaPrazo(industriaPrazoDto.getId())
				.descricao(industriaPrazoDto.getDescricao())
				.padrao(false)
				.build();
	}
}
