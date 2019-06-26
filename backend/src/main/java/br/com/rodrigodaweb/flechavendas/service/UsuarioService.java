package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.UsuarioDAO;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioCadastroDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioSearchDto;
import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.mapper.UsuarioMapper;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public UsuarioDto doLogin(String login, String senha) {
		Usuario usuario = usuarioDAO.findByLoginAndSenha(login, senha);
		if (usuario != null) {
			if(!usuario.isAtivo()) {
				throw new BusinessException(12,
						"Este login encontra-se desativado. Constulte a administração.");
			}
			return UsuarioMapper.toUsuarioDto(usuario);
		} else {
			throw new BusinessException(12,
					"Usuário não encontrado ou credenciais inválidas. Constulte a administração.");
		}
	}

	public List<UsuarioDto> getVendedoresPorIndustria(Integer idIndustria) {
		return usuarioDAO.findUsuariosPorIndustria(idIndustria).stream().map(UsuarioMapper::toUsuarioDto)
				.collect(Collectors.toList());
	}

	public List<UsuarioDto> buscaUsuarios() {
		return Lists.newArrayList(usuarioDAO.findAll()).stream().map(UsuarioMapper::toUsuarioDto)
				.collect(Collectors.toList());
	}

	public Usuario buscaPorId(Integer idUsuario) {
		return usuarioDAO.findOne(idUsuario);
	}
	
	public UsuarioDto buscaPorLogin(String login) {
		if (Strings.isNullOrEmpty(login)) {
			return null;
		}
		return UsuarioMapper.toUsuarioDto(usuarioDAO.findByLogin(login));
	}

	public PageImpl<UsuarioDto> buscaPorFiltro(UsuarioSearchDto filter) {
		Pageable pageable = new PageRequest(filter.getNewPage() - 1, filter.getPageSize());
		List<UsuarioDto> listagemUsuarioDtoList = UsuarioMapper.toUsuarioDtoList(usuarioDAO.find(filter));
		return new PageImpl<>(listagemUsuarioDtoList, pageable, usuarioDAO.count());
	}

	public Usuario salvarUsuario(UsuarioCadastroDto cadastroDto) {
		if(cadastroDto.getSenha().getSenha1().equals(cadastroDto.getSenha().getSenha2())) {
			return usuarioDAO.save(UsuarioMapper.toUsuario(cadastroDto));
		} else {
			throw new BusinessException(56, "Senhas informadas não são válidas.");
		}
	}
}
