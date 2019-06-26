package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.CadastroRepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.Representacao;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public class RepresentacaoMapper {
	public static RepresentacaoDto toRepresentacaoDto(Representacao entity) {
		if (entity == null) {
			return null;
		}
		return RepresentacaoDto.builder()
				.id(entity.getId())
				.ativo(entity.isAtivo())
				.industria(IndustriaMapper.industriaIndustriaDto(entity.getIndustria()))
				.usuario(UsuarioMapper.toUsuarioDto(entity.getUsuario())).build();
	}

	public static List<RepresentacaoDto> toRepresentacaoDtoList(List<Representacao> entityList) {
		if (CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(RepresentacaoMapper::toRepresentacaoDto).collect(Collectors.toList());
	}
	
	public static List<RepresentacaoDto> toRepresentacaoDtoList(Set<Representacao> entityList) {
		if (CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(RepresentacaoMapper::toRepresentacaoDto).collect(Collectors.toList());
	}

	public static Representacao toRepresentacao(CadastroRepresentacaoDto item) {
		return Representacao.builder()
				.id(item.getId())
				.industria(Industria.builder().id(item.getIdIndustria()).build())
				.usuario(Usuario.builder().id(item.getIdUsuario()).build())
				.ativo(item.isAtivo())
				.build();
	}

	public static List<CadastroRepresentacaoDto> toCadastroRepresentacaoDtoList(List<RepresentacaoDto> representacoes) {
		if(CollectionUtils.isNotEmpty(representacoes)) {
			return representacoes.stream().map(RepresentacaoMapper::toCadastroRepresentacaoDto)
					.collect(Collectors.toList());
		} else {
			return Lists.newArrayList();
		}
	}

	private static CadastroRepresentacaoDto toCadastroRepresentacaoDto(RepresentacaoDto entity) {
		return CadastroRepresentacaoDto.builder()
				.ativo(entity.isAtivo())
				.id(entity.getId())
				.idIndustria(entity.getIndustria().getId())
				.nomeIndustria(entity.getIndustria().getNome())
				.idUsuario(entity.getUsuario().getId())
				.nomeUsuario(entity.getUsuario().getNome())
				.build();
	}
}
