package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioSearchDto {
	private Integer id;
	private String login;
	private String nome;
	private String regiao;
	private String email;
	private String endereco;
	private String cpf;
	private String rg;
	private Integer idPerfil;
	private Integer pageSize;
	private Integer newPage;
}
