package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.ListagemPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.PedidoDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;
import br.com.rodrigodaweb.flechavendas.model.Pedido;
import br.com.rodrigodaweb.flechavendas.model.Tabela;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public class PedidoMapper {
	private PedidoMapper(){}
	public static Pedido toPedido(PedidoDto pedidoDto) {
		if(pedidoDto == null) {
			return null;
		}
		Pedido pedido = Pedido.builder().build();
		pedido.setId(pedidoDto.getId());
		pedido.setCarga(pedidoDto.getCarga());
		pedido.setCodigoPedidoIndustria(pedidoDto.getCodigoPedidoIndustria());
		pedido.setAlterado(pedidoDto.getAlterado());
		pedido.setCliente(Cliente.builder().id(pedidoDto.getIdCliente()).build());
		pedido.setDataEntrega(pedidoDto.getDataEntrega());
		pedido.setDataPedido(pedidoDto.getDataPedido());
		pedido.setIndustria(Industria.builder().id(pedidoDto.getIdIndustria()).build());
		pedido.setObservacoes(ObservacaoPedidoMapper.toObservacaoPedidoList(pedidoDto.getObservacoesPedidoDto()));
		pedido.setProposta(pedidoDto.getProposta());
		pedido.setTabela(Tabela.builder().id(pedidoDto.getIdTabela()).build());
		pedido.setUsuario(Usuario.builder().id(pedidoDto.getIdUsuario()).build());
		pedido.setIndustriaPrazo(IndustriaPrazo.builder().id(pedidoDto.getIdIndustriaPrazo()).build());
		pedido.setStatus(pedidoDto.getStatusPedido());
		return pedido;
	}
	public static PedidoDto toPedidoDto(Pedido entity) {
		if(entity == null) {
			return null;
		}
		return PedidoDto.builder()
				.id(entity.getId())
				.carga(entity.getCarga())
				.codigoPedidoIndustria(entity.getCodigoPedidoIndustria())
				.alterado(entity.getAlterado())
				.idCliente(entity.getCliente().getId())
				.dataEntrega(entity.getDataEntrega())
				.dataPedido(entity.getDataPedido())
				.idIndustria(entity.getIndustria().getId())
				.observacoesPedidoDto(CollectionUtils.isEmpty(entity.getObservacoes()) ? null : ObservacaoPedidoMapper.toObservacaoPedidoDtoList(entity.getObservacoes()))
				.proposta(entity.getProposta())
				.idTabela(entity.getTabela().getId())
				.idUsuario(entity.getUsuario().getId())
				.idIndustriaPrazo(entity.getIndustriaPrazo() == null ? null : entity.getIndustriaPrazo().getId())
				.usuario(entity.getUsuario() == null ? null : UsuarioMapper.toUsuarioDto(entity.getUsuario()))
				.industria(entity.getIndustria() == null ? null :IndustriaMapper.industriaIndustriaDto(entity.getIndustria()))
				.statusPedido(entity.getStatus())
				.itensPedido(CollectionUtils.isEmpty(entity.getItensPedido()) ? null : ItemPedidoMapper.toItemPedidoDtoList(Lists.newArrayList(entity.getItensPedido())))
				.cliente(ClienteMapper.toClienteDto(entity.getCliente()))
				.tabela(entity.getTabela() == null ? null : TabelaMapper.toTabelaDto(entity.getTabela()))
				.industriaPrazo(entity.getIndustriaPrazo() == null ? null : IndustriaPrazoMapper.toIndustriaPrazoDto(entity.getIndustriaPrazo()))
				.build();
	}

	public static List<PedidoDto> toPedidoDtoList(List<Pedido> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(PedidoMapper::toPedidoDto).collect(Collectors.toList());
	}

	public static ListagemPedidoDto toListagemPedidoDto(Pedido entity) {
		if(entity == null) {
			return null;
		}
		return ListagemPedidoDto.builder()
				.codigo(entity.getCodigoPedidoIndustria())
				.idPedido(entity.getId())
				.idVendedor(entity.getUsuario().getId())
				.dataPedido(entity.getDataPedido())
				.dataEntrega(entity.getDataEntrega())
				.nomeVendedor(entity.getUsuario().getNome())
				.nomeIndustria(entity.getIndustria().getNome())
				.nomeCliente(entity.getCliente().getRazaoSocial())
				.status(entity.getStatus())
				.itensPedido(entity.getItensPedido() != null ? ItemPedidoMapper.toItemPedidoDtoList(entity.getItensPedido()) : null)
				.build();
	}
	
	public static List<ListagemPedidoDto> toListagemPedidoDtoList(List<Pedido> entityList) {
		if(CollectionUtils.isEmpty(entityList)) {
			return Lists.newArrayList();
		}
		return entityList.stream().map(PedidoMapper::toListagemPedidoDto).collect(Collectors.toList());
	}
}
