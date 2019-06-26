package br.com.rodrigodaweb.flechavendas.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TabelaDto {
	private Long id;
	private String nome;
	private Date data;
	private List<ItemDto> itens;
}
