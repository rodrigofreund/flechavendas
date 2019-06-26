package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCadastroDto implements Serializable {

	private static final long serialVersionUID = -8414830342621252476L;

	private Integer id;
	private String login;
	private String nome;
	private String regiao;
	private String email;
	private String endereco;
	private String cpf;
	private String rg;
	private Integer idPerfil;
	private List<CadastroRepresentacaoDto> representacoes;
	private Boolean ativo;
	private CadastroUsuarioSenhaDto senha;
	private String telefonePrincipal;
	private String telefone1;
	private String telefone2;
	private boolean excluido;
}
