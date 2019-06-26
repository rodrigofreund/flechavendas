package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable, Comparable<Cliente> {

	private static final long serialVersionUID = 5116516515L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String razaoSocial;
	private String nomeFantasia;

	@Column(nullable = false, unique = true)
	private String cpfCnpj;

	@Column(nullable = false)
	private String rgIe;

	private String rua;
	private Integer numero;
	private String sala;
	@Column(length=30)
	private String andar;
	private String complemento;
	private String bairro;
	private String cidade;

	@ManyToOne
	@JoinColumn(name = "idEstado", foreignKey=@ForeignKey(name="FK_CLIENTE_ESTADO"))
	private Estado estado;

	private Long cep;

	@Column(nullable = false)
	private String telefone;
	private String celular;
	private String fax;

	@Column(nullable = false)
	private String contato;

	@Column(nullable = false)
	private String email;
	private String diasEntrega;
	private String horarioEntrega;
	private String nomeBanco;
	private Integer numeroAgencia;
	private String nomeAgencia;
	private Integer numeroConta;
	private Integer idPessoa;
	private boolean ativo;
	private boolean bloqueioVenda;
	private String informacoesAdicionais;
	private boolean excluido;
	private boolean pendenteRegistro;
	private String referenciasComerciais;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RepresentacaoCliente",
		joinColumns = @JoinColumn(name = "idCliente", referencedColumnName = "id", foreignKey=@ForeignKey(name="FK_CLIENTE_REPRESENTACAO")),
		inverseJoinColumns = @JoinColumn(name = "idRepresentacao", referencedColumnName = "id", foreignKey=@ForeignKey(name="FK_REPRESENTACAO_CLIENTE")))
	@Default
	private Set<Representacao> representantes = new TreeSet<Representacao>();

	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos;

	@OneToMany(mappedBy = "cliente")
	@Default
	private Set<IndustriaCliente> clienteIndustrias = new HashSet<>();

	@OneToMany(mappedBy = "cliente")
	private Set<ArquivoCliente> arquivos;

	@Override
	public int compareTo(Cliente o) {
		if (this.id < o.id) {
			return -1;
		}
		if (this.id > o.id) {
			return 1;
		}
		return 0;
	}
}
