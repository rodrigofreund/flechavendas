package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "login", unique = true, nullable = false, length = 30)
	private String login;

	@Column(name = "senha", nullable = false, length = 30)
	private String senha;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(length = 30)
	private String regiao;

	@Column(length = 60)
	private String email;

	@Column(length = 100)
	private String endereco;

	@Column(length = 12)
	private String cpf;

	@Column(length = 12)
	private String rg;

	private boolean ativo;

	@Column(length = 20)
	private String telefonePrincipal;

	@Column(length = 20)
	private String telefone1;

	@Column(length = 20)
	private String telefone2;

	@Column(columnDefinition="bit(1) default 0")
	private boolean excluido;

	@ManyToOne
	@JoinColumn(name = "idPerfil", foreignKey = @ForeignKey(name = "FK_USUARIO_PERFIL"))
	private Perfil perfil;

	@OneToMany(mappedBy = "usuario")
	private Set<Representacao> representacoes;

	@OneToMany(mappedBy = "usuario")
	private Set<Pedido> pedidos;

	@OneToMany(mappedBy = "usuario")
	private Set<ObservacaoPedido> observacoes;
}
