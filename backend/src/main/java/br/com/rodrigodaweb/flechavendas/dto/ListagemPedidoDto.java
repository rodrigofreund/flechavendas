package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListagemPedidoDto {
	private Long idPedido;
	private Integer idVendedor;
	private BigInteger codigo;
	private Date dataPedido;
	private Date dataEntrega;
	private String nomeVendedor;
	private String nomeIndustria;
	private String nomeCliente;
	private Integer status;
	private Integer quantidadeSolicitada;
	private List<ItemPedidoDto> itensPedido;
}
