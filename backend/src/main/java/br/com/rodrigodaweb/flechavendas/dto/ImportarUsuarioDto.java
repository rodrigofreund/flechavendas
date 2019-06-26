package br.com.rodrigodaweb.flechavendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportarUsuarioDto {
	private Integer idUsuarioOrigem;
	private Integer idUsuarioDestino;
	private boolean importarInativos;
}
