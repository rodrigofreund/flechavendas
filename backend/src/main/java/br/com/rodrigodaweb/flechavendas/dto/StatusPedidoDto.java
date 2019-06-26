package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;

public class StatusPedidoDto implements Serializable{

	private static final long serialVersionUID = 1403126390411544242L;
	
	private Integer id;
	private String descricao;
	
	public StatusPedidoDto(){}
	
	public StatusPedidoDto(Integer id, String descricao){
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
