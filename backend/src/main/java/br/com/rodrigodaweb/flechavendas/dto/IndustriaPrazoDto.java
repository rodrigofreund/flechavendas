package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;
import java.util.List;

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
public class IndustriaPrazoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
	private String descricao;
	private Integer idIndustria;
	private List<IndustriaPrazoDiaDto> dias;
	private boolean excluido;
}
