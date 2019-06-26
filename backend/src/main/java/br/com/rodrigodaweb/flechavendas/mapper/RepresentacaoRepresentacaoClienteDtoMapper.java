package br.com.rodrigodaweb.flechavendas.mapper;

import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Representacao;

public class RepresentacaoRepresentacaoClienteDtoMapper {
	public static RepresentacaoClienteDto representacaoRepresentacaoClienteDto(Representacao representacao) {
		if(representacao == null) {
			return null;
		}
		RepresentacaoClienteDto representacaoClienteDto = new RepresentacaoClienteDto();
		representacaoClienteDto.id = representacao.getId();
		representacaoClienteDto.industria = IndustriaMapper.industriaIndustriaDto(representacao.getIndustria());
		representacaoClienteDto.usuario = UsuarioMapper.toUsuarioDto(representacao.getUsuario());
		return representacaoClienteDto;
	}
}
