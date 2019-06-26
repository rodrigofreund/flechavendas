package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
	private Long id;
	private String codigo;
	private String descricao;
	private BigDecimal st;
	private BigDecimal ipi;
	private BigDecimal preco;
	private Integer quantidade;
	private BigDecimal precoFinal;
	private BigDecimal descontoSugerido;
}
