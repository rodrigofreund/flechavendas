package br.com.rodrigodaweb.flechavendas.dto;

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
public class IndustriaPrazoDiaDto {
	private Integer id;
	private Integer idIndustriaPrazo;
	private Integer prazo;
}
