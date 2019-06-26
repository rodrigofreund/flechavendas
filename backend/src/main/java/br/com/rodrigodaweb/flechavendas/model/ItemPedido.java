package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ItemPedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 8217332138310636542L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idItemPedido;

	@ManyToOne
	@JoinColumn(name = "idPedido", foreignKey = @ForeignKey(name = "FK_ITEMPEDIDO_PEDIDO"))
	private Pedido pedido;

	private String codigo;

	private String descricao;

	@Column(precision = 10, scale = 5)
	private BigDecimal st;

	/**
	 * Valor do acrescimo de IPI
	 **/
	@Column(columnDefinition = "Decimal(10,5) default '0.00'")
	private BigDecimal ipi;

	private BigDecimal preco;

	private Integer quantidade;

	private Integer quantidadeSolicitada;

	private BigDecimal precoFinal;

	@Column(precision = 10, scale = 5)
	private BigDecimal desconto;

	private BigDecimal precoColocado;

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getSt() {
		return st;
	}

	public void setSt(BigDecimal st) {
		this.st = st;
	}
	
	public BigDecimal getIpi() {
		return ipi;
	}

	public void setIpi(BigDecimal ipi) {
		this.ipi = ipi;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(BigDecimal precoFinal) {
		this.precoFinal = precoFinal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public BigDecimal getPrecoColocado() {
		return precoColocado;
	}

	public void setPrecoColocado(BigDecimal precoColocado) {
		this.precoColocado = precoColocado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemPedido == null) ? 0 : idItemPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (idItemPedido == null) {
			if (other.idItemPedido != null)
				return false;
		} else if (!idItemPedido.equals(other.idItemPedido))
			return false;
		return true;
	}

}
