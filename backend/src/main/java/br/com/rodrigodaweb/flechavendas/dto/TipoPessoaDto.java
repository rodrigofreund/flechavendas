package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;

public class TipoPessoaDto implements Serializable{

	private static final long serialVersionUID = 5398049064572211829L;
	
	private Integer id;
	private String descricao;
	
	public TipoPessoaDto(){}
	
	public TipoPessoaDto(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
