package br.com.rodrigodaweb.flechavendas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CadastroRepresentacaoDto {
	private Long id;
	private Integer idIndustria;
	private String nomeIndustria;
	private Integer idUsuario;
	private String nomeUsuario;
	private boolean ativo;
}
