package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UltimasVendasItensResultDto {
	private Long idItemPedido;
	private Date dataPedido;
	private String prazo;
	private Integer quantidade;
	private Integer quantidadeCaixa;
	//Preço com ST (ItemPedido.precoFinal)
	private BigDecimal preco;
	private BigDecimal precoColocado;
	private BigDecimal st;
	private BigDecimal ipi;
	private String tabela;
}
