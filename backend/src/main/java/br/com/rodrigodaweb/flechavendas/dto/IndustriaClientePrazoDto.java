package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IndustriaClientePrazoDto {
	private Integer id;
	private Integer idIndustriaCliente;
	private Integer idIndustriaPrazo;
	private String descricaoIndustriaPrazo;
	private boolean padrao;
}
