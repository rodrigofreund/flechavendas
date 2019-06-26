package br.com.rodrigodaweb.flechavendas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.ItemPedidoDAO;
import br.com.rodrigodaweb.flechavendas.dao.PedidoDAO;
import br.com.rodrigodaweb.flechavendas.dto.ItemPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.UltimasVendasItensResultDto;
import br.com.rodrigodaweb.flechavendas.dto.UltimasVendasItemSearchDto;
import br.com.rodrigodaweb.flechavendas.mapper.ItemPedidoMapper;
import br.com.rodrigodaweb.flechavendas.mapper.UltimasVendasItemMapper;
import br.com.rodrigodaweb.flechavendas.model.ItemPedido;
import br.com.rodrigodaweb.flechavendas.model.Pedido;

@Service
@Transactional
public class ItemPedidoService {
	@Autowired
	private ItemPedidoDAO itemPedidoDAO;

	@Autowired
	private PedidoDAO pedidoDAO;

	public boolean salvaItensAdicionadosPedido(Long idPedido, List<ItemPedidoDto> itens) {

		if (CollectionUtils.isEmpty(itens)) {
			return false;
		}

		List<ItemPedido> listaItensAltual = itemPedidoDAO.getItensBydIdPedido(idPedido);

		List<ItemPedido> listaItensPedido = new ArrayList<ItemPedido>();
		Boolean atualizarPedidoModificado = Boolean.FALSE;
		for (ItemPedidoDto itemPedidoDto : itens) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setCodigo(itemPedidoDto.getCodigo());
			itemPedido.setDesconto(itemPedidoDto.getDesconto());
			if (itemPedidoDto.getDesconto() != null && itemPedidoDto.getDesconto().floatValue() > 0.0) {
				atualizarPedidoModificado = Boolean.TRUE;
			}
			itemPedido.setDescricao(itemPedidoDto.getDescricao());
			Pedido pedido = Pedido.builder().build();
			pedido.setId(idPedido);
			itemPedido.setIdItemPedido(itemPedidoDto.getIdItemPedido());
			itemPedido.setPedido(pedido);
			itemPedido.setPreco(itemPedidoDto.getPreco());
			itemPedido.setPrecoFinal(itemPedidoDto.getPrecoFinal());
			itemPedido.setQuantidade(itemPedidoDto.getQuantidade());
			itemPedido.setQuantidadeSolicitada(itemPedidoDto.getQuantidadeSolicitada());
			itemPedido.setSt(itemPedidoDto.getSt());
			itemPedido.setIpi(itemPedidoDto.getIpi());
			itemPedido.setPrecoColocado(itemPedidoDto.getPrecoColocado());

			if (itemPedidoDto.getIdItemPedido() != null) {
				listaItensAltual.remove(itemPedido);
			}

			listaItensPedido.add(itemPedido);

		}

		if (!listaItensAltual.isEmpty()) {
			itemPedidoDAO.delete(listaItensAltual);
		}

		itemPedidoDAO.save(listaItensPedido);

		if (atualizarPedidoModificado) {
			Pedido pedido = pedidoDAO.findOne(idPedido);
			pedido.setAlterado(Boolean.TRUE);
			pedidoDAO.save(pedido);
		}
		return true;
	}

	public List<ItemPedidoDto> getItensPedido(Long idPedido) {
		return ItemPedidoMapper.toItemPedidoDtoList(itemPedidoDAO.getItensBydIdPedido(idPedido));
	}

	public List<UltimasVendasItensResultDto> getUltimasVendasItem(UltimasVendasItemSearchDto search) {
		List<ItemPedido> searchResult = itemPedidoDAO.getUltimasVendasItem(search.getIdCliente(),
				search.getCodigoItem(), search.getIdUsuario());

		List<UltimasVendasItensResultDto> result = Lists.newArrayList();

		if (searchResult.isEmpty()) {
			return result;
		}

		if (searchResult.size() > 3) {
			result = UltimasVendasItemMapper.toUltimasVendasItensResultDto(searchResult.subList(0, 3));
		} else {
			result = UltimasVendasItemMapper.toUltimasVendasItensResultDto(searchResult);
		}

		return calculaPrecoFinal(result);

	}

	private List<UltimasVendasItensResultDto> calculaPrecoFinal(List<UltimasVendasItensResultDto> result) {
		result.forEach(item -> {
			item.setPreco(item.getPrecoColocado() != null ? calculaPrecoColocadoComImpostos(item)
					: item.getPreco());
		});
		return result;
	}

	private BigDecimal calculaPrecoColocadoComImpostos(UltimasVendasItensResultDto item) {
		return item.getPrecoColocado().add(item.getPrecoColocado().multiply(item.getSt()))
				.add(item.getPrecoColocado().multiply(item.getIpi()));
	}
}
