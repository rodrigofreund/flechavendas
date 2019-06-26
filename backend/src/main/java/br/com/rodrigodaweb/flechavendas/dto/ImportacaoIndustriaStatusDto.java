package br.com.rodrigodaweb.flechavendas.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportacaoIndustriaStatusDto {
	private Integer idIndustria;
	private Integer idUsuario;
	private Long idRepresentacao;
	private String nomeIndustria;
	private Set<ImportacaoIndustriaClienteDto> listaCliente;
}
