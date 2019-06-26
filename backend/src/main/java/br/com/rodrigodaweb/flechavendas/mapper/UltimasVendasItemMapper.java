package br.com.rodrigodaweb.flechavendas.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rodrigodaweb.flechavendas.dto.UltimasVendasItensResultDto;
import br.com.rodrigodaweb.flechavendas.model.ItemPedido;

public class UltimasVendasItemMapper {
	public static UltimasVendasItensResultDto toUltimasVendasItensResultDto(ItemPedido itemPedido) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		return UltimasVendasItensResultDto.builder()
				.dataPedido(itemPedido.getPedido().getDataPedido())
				.prazo(itemPedido.getPedido().getIndustriaPrazo().getDescricao())
				.preco(itemPedido.getPrecoFinal())
				.quantidade(itemPedido.getQuantidadeSolicitada())
				.idItemPedido(itemPedido.getIdItemPedido())
				.quantidadeCaixa(itemPedido.getQuantidade())
				.precoColocado(itemPedido.getPrecoColocado())
				.st(itemPedido.getSt())
				.ipi(itemPedido.getIpi())
				.tabela(itemPedido.getPedido().getTabela().getNome() + "-"
						+ df.format(itemPedido.getPedido().getTabela().getData()))
				.build();
	}

	public static List<UltimasVendasItensResultDto> toUltimasVendasItensResultDto(List<ItemPedido> itemPedidoList) {
		return itemPedidoList.stream().map(UltimasVendasItemMapper::toUltimasVendasItensResultDto)
				.collect(Collectors.toList());
	}
}
