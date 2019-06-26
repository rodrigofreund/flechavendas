package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Data;

@Data
public class ClienteExistenteSearchDto {
	private Long idUsuario;
	private String cpfCnpj;
}
