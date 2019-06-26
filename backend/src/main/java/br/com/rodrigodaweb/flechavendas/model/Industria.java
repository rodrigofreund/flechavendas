package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Industria")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Industria implements Serializable {

	private static final long serialVersionUID = 524150985912708155L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String telefone;
	
	private String cor;
	
	private String imagem;
	
	@OneToMany(mappedBy="industria")
	private Set<Representacao> representacoes;
	
	@OneToMany(mappedBy="industria")
	private Set<Pedido> pedidos;
	
	@OneToMany(mappedBy="industria")
	private Set<Tabela> tabelas;
	
	@OneToMany(mappedBy = "industria")
	@Default
	private Set<IndustriaCliente> industriaClientes = new HashSet<>();
	
	@OneToMany(mappedBy="industria")
	private Set<IndustriaPrazo> industriaPrazo;
	
}
