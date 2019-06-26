package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.IndustriaCliente;

public class IndustriaClienteMapper {
	private IndustriaClienteMapper(){}

	public static IndustriaClienteDto toIndustriaClienteDto(IndustriaCliente industriaCliente) {
		if(industriaCliente == null) {
			return null;
		}
		IndustriaClienteDto industriaClienteDto = new IndustriaClienteDto();
		industriaClienteDto.setId(industriaCliente.getId());
		industriaClienteDto.setAtivo(industriaCliente.isAtivo());
		industriaClienteDto.setBloqueioVenda(industriaCliente.isBloqueioVenda());
		industriaClienteDto.setCodigo(industriaCliente.getCodigo());
		industriaClienteDto.setId(industriaCliente.getId());
		industriaClienteDto.setIdCliente((industriaCliente.getCliente() == null ? null
				: industriaCliente.getCliente().getId()));
		industriaClienteDto.setIdIndustria((industriaCliente.getIndustria() == null ? null
				: industriaCliente.getIndustria().getId()));
		industriaClienteDto.setLimiteCredito(industriaCliente.getLimiteCredito());
		industriaClienteDto.setNomeIndustria((industriaCliente.getIndustria() == null ? null
				: industriaCliente.getIndustria().getNome()));
		industriaClienteDto.setListaIndustriaClientePrazoParaRemover(Lists.newArrayList());
		return industriaClienteDto;
	}

	public static IndustriaCliente toIndustriaCliente(IndustriaClienteDto dto) {
		if(dto == null) {
			return null;
		}
		return IndustriaCliente.builder()
			.ativo(dto.isAtivo())
			.bloqueioVenda(dto.isBloqueioVenda())
			.cliente(Cliente.builder().id(dto.getIdCliente()).build())
			.codigo(dto.getCodigo())
			.id(dto.getId())
			.industria(Industria.builder().id(dto.getIdIndustria()).build())
			.limiteCredito(dto.getLimiteCredito())
			.prazos(dto.getListaIndustriaClientePrazo().stream().map(IndustriaClientePrazoMapper::toIndustriaClientePrazo).collect(Collectors.toList()))
			.build();
	}
}
