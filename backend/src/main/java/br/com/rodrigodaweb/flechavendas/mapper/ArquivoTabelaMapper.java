package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.ArquivoTabelaDto;
import br.com.rodrigodaweb.flechavendas.model.ArquivoTabela;

public class ArquivoTabelaMapper {
	private ArquivoTabelaMapper() {
	}

	public static ArquivoTabelaDto toArquivoTabelaDto(ArquivoTabela entity) {
		return ArquivoTabelaDto.builder()
				.id(entity.getIdTabela())
				.nomeArquivo(entity.getNomeArquivo())
				.diretorio(entity.getDiretorio())
				.build();
	}
}
