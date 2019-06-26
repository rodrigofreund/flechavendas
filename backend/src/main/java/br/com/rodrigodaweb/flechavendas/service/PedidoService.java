package br.com.rodrigodaweb.flechavendas.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.PedidoDAO;
import br.com.rodrigodaweb.flechavendas.domain.STATUS_PEDIDO;
import br.com.rodrigodaweb.flechavendas.dto.FiltroPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ListagemPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.PedidoDto;
import br.com.rodrigodaweb.flechavendas.mapper.PedidoMapper;
import br.com.rodrigodaweb.flechavendas.model.ObservacaoPedido;
import br.com.rodrigodaweb.flechavendas.model.Pedido;

@Service
@Transactional
public class PedidoService {
	
	static final Logger log = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private ObservacaoPedidoService observacaoPedidoService;
	
	@Autowired
	private PedidoHistoricoService pedidoHistoricoService;

	public PedidoDto salvaPedido(PedidoDto pedidoDto) {
		log.info("Novo pedido usuario {} para o cliente {}", pedidoDto.getUsuario().getId(), pedidoDto.getCliente().getId());
		preencheDataPedido(pedidoDto);

		log.info("Data do pedido: {}", pedidoDto.getDataPedido());
		Pedido novoPedido = pedidoDAO.save(PedidoMapper.toPedido(pedidoDto));
		log.info("Pedido Salvo com ID: {}", novoPedido.getId());

		log.info("Salvando Historico");
		pedidoHistoricoService.salvaHistorico(pedidoDto.getUsuarioAlteracao(), novoPedido);

		log.info("Salvando Itens do Pedido");
		itemPedidoService.salvaItensAdicionadosPedido(novoPedido.getId(), pedidoDto.getItensPedido());

		log.info("Salvando Observacoes");
		observacaoPedidoService.salvaObservacao(novoPedido.getId(), pedidoDto.getObservacoesPedidoDto());

		return PedidoMapper.toPedidoDto(novoPedido);
	}

	public Page<ListagemPedidoDto> getPedidosPorFiltro(FiltroPedidoDto search) {
		Pageable pageable = new PageRequest(search.getNewPage() - 1, search.getPageSize());
		List<ListagemPedidoDto> listagemPedidoDtoList = PedidoMapper.toListagemPedidoDtoList(pedidoDAO.find(search));
		return new PageImpl<>(listagemPedidoDtoList, pageable, pedidoDAO.countResult(search));
	}

	public PedidoDto atualizarStatusPedido(Long idPedido, Integer status) {
		Pedido pedido = pedidoDAO.findOne(idPedido);
		pedido.setStatus(status);
		return PedidoMapper.toPedidoDto(pedidoDAO.save(pedido));
	}

	public Integer getNumeroPedidosAprovados() {
		return pedidoDAO.getNumeroPedidosPorStatus(STATUS_PEDIDO.ENVIADO.getCodigo());
	}

	public Integer getNumeroPedidosNegados(Integer idUsuario) {
		return pedidoDAO.getNumeroPedidosNegados(STATUS_PEDIDO.NEGADO.getCodigo(), idUsuario);
	}

	private void preencheDataPedido(PedidoDto pedidoDto) {
		if (pedidoDto.getId() == null) {
			pedidoDto.setDataPedido(new Date());
		}
	}

	public PedidoDto getPedidoDetalhado(Long idPedido) {
		Pedido pedido = pedidoDAO.findOne(idPedido);
		ordenaObservacoesPorData(pedido);
		return PedidoMapper.toPedidoDto(pedido);
	}

	private void ordenaObservacoesPorData(Pedido pedido) {
		pedido.setObservacoes(pedido.getObservacoes().stream().sorted(new Comparator<ObservacaoPedido>() {
			@Override
			public int compare(ObservacaoPedido arg0, ObservacaoPedido arg1) {
				return arg0.getDataCriacao().compareTo(arg1.getDataCriacao());
			}
		}).collect(Collectors.toList()));
		
	}
}
