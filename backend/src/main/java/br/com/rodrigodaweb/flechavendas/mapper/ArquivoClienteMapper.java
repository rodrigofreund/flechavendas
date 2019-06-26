package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.rodrigodaweb.flechavendas.dto.ArquivoClienteDto;
import br.com.rodrigodaweb.flechavendas.model.ArquivoCliente;
import br.com.rodrigodaweb.flechavendas.model.Cliente;

public class ArquivoClienteMapper {
	public static ArquivoCliente toArquivoCliente(ArquivoClienteDto dto) {
		return ArquivoCliente.builder().id(dto.getId()).cliente(Cliente.builder().id(dto.getIdCliente()).build())
				.nomeArquivo(dto.getNomeArquivo()).build();
	}

	public static ArquivoClienteDto toArquivoClienteDto(ArquivoCliente entity) {
		return ArquivoClienteDto.builder().id(entity.getId()).idCliente(entity.getCliente().getId())
				.nomeArquivo(entity.getNomeArquivo()).build();
	}

	public static List<ArquivoClienteDto> toArquivoClienteDtoList(Set<ArquivoCliente> arquivos) {
		return arquivos.stream().map(ArquivoClienteMapper::toArquivoClienteDto).collect(Collectors.toList());
	}
}
