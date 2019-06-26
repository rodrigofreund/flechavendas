package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tabela")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tabela implements Serializable {

	private static final long serialVersionUID = 6626522168211675257L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private Date data;

	@ManyToOne
	@JoinColumn(name = "idIndustria")
	private Industria industria;

	@OneToMany(mappedBy = "tabela")
	private Set<Pedido> pedidos;

	@OneToMany(mappedBy = "tabela")
	private Set<Item> itens;
	
	@Column(name="excluido", columnDefinition="tinyint default 0")
	@Default
	private boolean excluido = false;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "tabela", cascade = CascadeType.REFRESH)
	private ArquivoTabela arquivo;
}
