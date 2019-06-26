package br.com.rodrigodaweb.flechavendas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportacaoIndustriaClienteDto {
	private Integer idCliente;
	private String nome;
	private boolean importar;
}
