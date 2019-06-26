package br.com.rodrigodaweb.flechavendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.RepresentacaoDAO;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioCadastroDto;
import br.com.rodrigodaweb.flechavendas.mapper.RepresentacaoMapper;
import br.com.rodrigodaweb.flechavendas.model.Representacao;

@Service
@Transactional
public class RepresentacaoService {

	@Autowired
	private RepresentacaoDAO representacaoDAO;

	public List<RepresentacaoDto> getRepresentacoesUsuario(Integer idUsuario) {
		return RepresentacaoMapper.toRepresentacaoDtoList(representacaoDAO.getRepresentacoesUsuario(idUsuario));
	}

	public List<RepresentacaoDto> getRepresentacoesIndustria(Integer idIndustria) {
		return RepresentacaoMapper.toRepresentacaoDtoList(representacaoDAO.getRepresentacoesIndustria(idIndustria));
	}

	public Representacao getRepresentacaoUsuarioIndustria(Integer idUsuario, Integer idIndustria) {
		List<Representacao> representacoesUsuario = representacaoDAO.getRepresentacoesUsuario(idUsuario);
		List<Representacao> result = new ArrayList<>();
		representacoesUsuario.stream().filter(item -> item.getIndustria().getId() == idIndustria).forEach(result::add);
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public Representacao findById(Long id) {
		return representacaoDAO.findOne(id);
	}

	public List<Representacao> salvaRepresentacaoUsuario(UsuarioCadastroDto cadastroDto) {
		List<Representacao> result = Lists.newArrayList();

		if (CollectionUtils.isNotEmpty(cadastroDto.getRepresentacoes())) {
			cadastroDto.getRepresentacoes().forEach(item -> {
				result.add(representacaoDAO.save(RepresentacaoMapper.toRepresentacao(item)));
			});
		}

		return result;
	}
	
	public Representacao salvaRepresentacao(Representacao entity) {
		return representacaoDAO.save(entity);
	}
}
