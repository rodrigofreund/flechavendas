
package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pedido")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido implements Serializable {

	private static final long serialVersionUID = -3514882821745125923L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idTabela", foreignKey = @ForeignKey(name = "FK_PEDIDO_TABELA"))
	private Tabela tabela;

	@ManyToOne
	@JoinColumn(name = "idIndustria", foreignKey = @ForeignKey(name = "FK_PEDIDO_INDUSTRIA"))
	private Industria industria;

	@ManyToOne
	@JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "FK_PEDIDO_USUARIO"))
	private Usuario usuario;

	@OneToMany(mappedBy = "pedido")
	private Set<ItemPedido> itensPedido;

	@OneToMany(mappedBy = "pedido")
	private Set<PedidoHistorico> historicoPedido;

	@ManyToOne
	@JoinColumn(name = "idIndustriaPrazo", foreignKey = @ForeignKey(name = "FK_PEDIDO_INDUSTRIAPRAZO"))
	private IndustriaPrazo industriaPrazo;

	private Boolean proposta;

	private Integer carga;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPedido;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataEntrega;

	@Column(name = "status")
	@Default
	private Integer status = 0;

	private BigInteger codigoPedidoIndustria;

	private Boolean alterado;

	@OneToMany(mappedBy = "pedido")
	private List<ObservacaoPedido> observacoes;

	@Deprecated
	private Integer prazo;
}
