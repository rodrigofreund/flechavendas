package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PerfilDto {
	private Integer id;
	private String nome;
}
