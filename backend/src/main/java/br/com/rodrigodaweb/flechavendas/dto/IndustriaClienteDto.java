package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustriaClienteDto {
	private Integer id;
	private Integer idCliente;
	private Integer idIndustria;
	private String codigo;
	private boolean ativo;
	private BigDecimal limiteCredito;
	private boolean bloqueioVenda;
	private String nomeIndustria;
	private boolean removido;
	private List<IndustriaClientePrazoDto> listaIndustriaClientePrazo;
	private List<IndustriaClientePrazoDto> listaIndustriaClientePrazoParaRemover;
}
