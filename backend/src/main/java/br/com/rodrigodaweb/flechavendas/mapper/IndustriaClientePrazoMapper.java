package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaClientePrazoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.IndustriaCliente;
import br.com.rodrigodaweb.flechavendas.model.IndustriaClientePrazo;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;

public class IndustriaClientePrazoMapper {
	public static IndustriaClientePrazoDto toIndustriaClientePrazoDto(IndustriaClientePrazo entity) {
		if(entity == null) {
			return null;
		}
		return IndustriaClientePrazoDto.builder()
				.id(entity.getId())
				.idIndustriaCliente(entity.getIndustriaCliente().getId())
				.idIndustriaPrazo(entity.getIndustriaPrazo().getId())
				.descricaoIndustriaPrazo(entity.getIndustriaPrazo().getDescricao())
				.padrao(entity.isPadrao()).build();
	}

	public static IndustriaClientePrazo toIndustriaClientePrazo(IndustriaClientePrazoDto dto) {
		if(dto == null) {
			return null;
		}
		return IndustriaClientePrazo.builder()
				.id(dto.getId())
				.industriaCliente(IndustriaCliente.builder().id(dto.getIdIndustriaCliente()).build())
				.industriaPrazo(IndustriaPrazo.builder().id(dto.getIdIndustriaPrazo()).build())
				.padrao(dto.isPadrao())
				.build();
	}
	
	public static IndustriaPrazoPedidoDto toIndustriaPrazoPedidoDto(IndustriaClientePrazoDto industriaClientePrazoDto) {
		if(industriaClientePrazoDto == null) {
			return null;
		}
		return IndustriaPrazoPedidoDto.builder()
				.descricao(industriaClientePrazoDto.getDescricaoIndustriaPrazo())
				.idIndustriaPrazo(industriaClientePrazoDto.getIdIndustriaPrazo())
				.padrao(industriaClientePrazoDto.isPadrao())
				.build();
	}
}
