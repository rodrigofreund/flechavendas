package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.CadastroUsuarioSenhaDto;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioCadastroDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.model.Perfil;
import br.com.rodrigodaweb.flechavendas.model.Representacao;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public class UsuarioMapper {

	private UsuarioMapper() {
	}

	public static UsuarioDto toUsuarioDto(Usuario entity) {
		if (entity == null) {
			return null;
		}
		return UsuarioDto.builder()
				.cpf(entity.getCpf())
				.email(entity.getEmail())
				.endereco(entity.getEndereco())
				.id(entity.getId())
				.login(entity.getLogin())
				.nome(entity.getNome())
				.perfil(entity.getPerfil() == null ? null : PerfilMapper.toPerfilDto(entity.getPerfil()))
				.ativo(entity.isAtivo())
				.telefonePrincipal(entity.getTelefonePrincipal())
				.telefone1(entity.getTelefone1())
				.telefone2(entity.getTelefone2())
				.excluido(entity.isExcluido())
				.build();
	}

	public static Usuario toUsuario(UsuarioDto dto) {
		return Usuario.builder()
				.cpf(dto.getCpf())
				.email(dto.getEmail())
				.endereco(dto.getEndereco())
				.id(dto.getId())
				.login(dto.getLogin())
				.nome(dto.getNome())
				.regiao(dto.getRegiao())
				.rg(dto.getRg())
				.ativo(dto.isAtivo())
				.telefonePrincipal(dto.getTelefonePrincipal())
				.telefone1(dto.getTelefone1())
				.telefone2(dto.getTelefone2())
				.excluido(dto.isExcluido())
				.build();
	}

	public static Usuario toUsuario(UsuarioCadastroDto dto) {
		return Usuario.builder()
				.cpf(dto.getCpf())
				.email(dto.getEmail())
				.endereco(dto.getEndereco())
				.id(dto.getId())
				.login(dto.getLogin())
				.nome(dto.getNome())
				.regiao(dto.getRegiao())
				.rg(dto.getRg())
				.perfil(Perfil.builder().id(dto.getIdPerfil()).build())
				.senha(dto.getSenha().getSenha1())
				.ativo(dto.getAtivo())
				.telefonePrincipal(dto.getTelefonePrincipal())
				.telefone1(dto.getTelefone1())
				.telefone2(dto.getTelefone2())
				.excluido(dto.isExcluido())
				.build();
	}

	public static List<UsuarioDto> toUsuarioDtoList(List<Usuario> entityList) {
		return entityList.stream().map(item -> UsuarioMapper.toUsuarioDto(item)).collect(Collectors.toList());
	}

	public static UsuarioCadastroDto toUsuarioCadastroDto(Usuario usuario, List<RepresentacaoDto> representacoes) {
		return UsuarioCadastroDto.builder()
				.cpf(usuario.getCpf())
				.email(usuario.getEmail())
				.endereco(usuario.getEndereco())
				.id(usuario.getId())
				.idPerfil(usuario.getPerfil().getId())
				.login(usuario.getLogin())
				.nome(usuario.getNome())
				.regiao(usuario.getRegiao())
				.rg(usuario.getRg())
				.ativo(usuario.isAtivo())
				.telefonePrincipal(usuario.getTelefonePrincipal())
				.telefone1(usuario.getTelefone1())
				.telefone2(usuario.getTelefone2())
				.senha(CadastroUsuarioSenhaDto.builder().senha1(usuario.getSenha()).senha2(usuario.getSenha()).build())
				.representacoes(RepresentacaoMapper.toCadastroRepresentacaoDtoList(representacoes))
				.build();
	}

	public static UsuarioCadastroDto toUsuarioCadastroDto(Usuario usuario, Set<RepresentacaoDto> representacoes) {
		return UsuarioMapper.toUsuarioCadastroDto(usuario, Lists.newArrayList(representacoes));
		
	}
}
