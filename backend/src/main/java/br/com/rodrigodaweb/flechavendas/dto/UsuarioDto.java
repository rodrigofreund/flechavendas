package br.com.rodrigodaweb.flechavendas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDto {
	private Integer id;
	private String login;
	private String senha;
	private String nome;
	private String regiao;
	private String email;
	private String endereco;
	private String cpf;
	private String rg;
	private PerfilDto perfil;
	private boolean ativo;
	private String telefonePrincipal;
	private String telefone1;
	private String telefone2;
	private boolean excluido;
}
