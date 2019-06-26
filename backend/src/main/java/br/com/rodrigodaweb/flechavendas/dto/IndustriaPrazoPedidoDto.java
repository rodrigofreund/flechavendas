package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndustriaPrazoPedidoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idIndustriaPrazo;
	private String descricao;
	private boolean padrao;
}
