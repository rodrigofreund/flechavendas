package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rodrigo Freund
 *
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value="precoSugerido")
public class ItemPedidoDto {
	private Long id;
	private Long idItemPedido;
	private String codigo;
	private String descricao;
	private BigDecimal st;
	private BigDecimal ipi;
	private BigDecimal preco;
	private Integer quantidade;
	private Integer quantidadeSolicitada;
	private BigDecimal precoFinal;
	private BigDecimal desconto;
	private BigDecimal precoColocado;
}
