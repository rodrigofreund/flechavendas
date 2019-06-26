package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import br.com.rodrigodaweb.flechavendas.dao.ObservacaoPedidoDAO;
import br.com.rodrigodaweb.flechavendas.dto.ObservacaoPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ObservacaoPedidoUpdateDto;
import br.com.rodrigodaweb.flechavendas.mapper.ObservacaoPedidoMapper;
import br.com.rodrigodaweb.flechavendas.model.ObservacaoPedido;

@Service
@Transactional
public class ObservacaoPedidoService {

	@Autowired
	private ObservacaoPedidoDAO dao;

	@Autowired
	private UsuarioService usuarioService;

	public List<ObservacaoPedido> salvaObservacao(Long idPedido, List<ObservacaoPedidoDto> listaObservacoesDto) {
		if(CollectionUtils.isEmpty(listaObservacoesDto)) {
			return null;
		}
		listaObservacoesDto.forEach(item -> item.setIdPedido(idPedido));
		List<ObservacaoPedido> listaObservacaoPedido = ObservacaoPedidoMapper.toObservacaoPedidoList(listaObservacoesDto);
		return listaObservacaoPedido.stream().map(dao::save).collect(Collectors.toList());
	}

	public List<ObservacaoPedidoDto> salvaObservacoesPedido(ObservacaoPedidoUpdateDto observacaoPedidoUpdateDto) {
		List<ObservacaoPedidoDto> result = ObservacaoPedidoMapper.toObservacaoPedidoDtoList(salvaObservacao(
				observacaoPedidoUpdateDto.getIdPedido(), observacaoPedidoUpdateDto.getListaObservacaoPedidoDto()));
		preencheNomeUsuario(result);
		return result;
	}

	public List<ObservacaoPedidoDto> getObservacoesPedido(Long idPedido) {
		List<ObservacaoPedidoDto> observacoes = ObservacaoPedidoMapper.toObservacaoPedidoDtoList(dao.findByPedidoIdPedido(idPedido));
		preencheNomeUsuario(observacoes);
		return observacoes;
	}

	private void preencheNomeUsuario(List<ObservacaoPedidoDto> observacoes) {
		observacoes.forEach(obsDto -> {
			if(Strings.isNullOrEmpty(obsDto.getNomeUsuario())) {
				obsDto.setNomeUsuario(usuarioService.buscaPorId(obsDto.getIdUsuario()).getNome());
			}
		});
	}
}
