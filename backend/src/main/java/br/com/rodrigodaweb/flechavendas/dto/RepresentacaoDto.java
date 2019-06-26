package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RepresentacaoDto {
	private Long id;
	private UsuarioDto usuario;
	private IndustriaDto industria;
	private boolean ativo;
}
