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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable, Comparable<Item> {

	private static final long serialVersionUID = -2462591105963503509L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String codigo;
	private String descricao;

	/**
	 * Valor do acrescimo de ST
	 **/
	@Column(precision = 10, scale = 5)
	private BigDecimal st;

	/**
	 * Valor do acrescimo de IPI
	 **/
	@Column(columnDefinition="Decimal(10,5) default '0.00'")
	private BigDecimal ipi;

	/**
	 * Valor da caixa sem st
	 **/
	private BigDecimal preco;

	/**
	 * Valor do desconto inserido em tabela
	 **/
	private BigDecimal descontoSugerido;

	/**
	 * Quantidade de itens na caixa
	 **/
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "idTabela", foreignKey = @ForeignKey(name = "FK_ITEM_TABELA"))
	private Tabela tabela;
	

	@Override
	public int compareTo(Item arg0) {
		return this.getDescricao().compareTo(arg0.getDescricao());
	}
}
