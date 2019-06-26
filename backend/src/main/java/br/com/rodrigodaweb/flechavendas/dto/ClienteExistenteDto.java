package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteExistenteDto {
	private boolean existente;
	private boolean existeNaoAtende;
	private ClienteDto cliente;
}
