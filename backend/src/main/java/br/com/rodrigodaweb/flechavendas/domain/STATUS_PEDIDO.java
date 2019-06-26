package br.com.rodrigodaweb.flechavendas.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigodaweb.flechavendas.dto.StatusPedidoDto;

public enum STATUS_PEDIDO {
	INDEFINIDO(0, "Indefinido", false),
	CRIADO(1, "Criado", false),
	SALVO(2, "Salvo", false),
	ENVIADO(3, "Enviado", true),
	NEGADO(4, "Negado", true),
	COLOCADO(5, "Colocado", true),
	CANCELADO(6, "Cancelado", true);
	
	private Integer codigo;
	
	private String mensagem;
	
	private boolean mostraNaLista;
	
	private STATUS_PEDIDO(Integer codigo, String mensagem, boolean mostraNaLista) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.mostraNaLista = mostraNaLista;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	public STATUS_PEDIDO fromCodigo(Integer codigo) {
		for(STATUS_PEDIDO status :STATUS_PEDIDO.values()) {
			if(status.getCodigo().equals(codigo)) {
				return status;
			}
		}
		return INDEFINIDO;
	}
	
	public static List<StatusPedidoDto> getListaStatusDto() {
		List<StatusPedidoDto> listaStatus = new ArrayList<>();
		for(STATUS_PEDIDO status :STATUS_PEDIDO.values()) {
			if(status.mostraNaLista) {
				listaStatus.add(new StatusPedidoDto(status.codigo, status.mensagem));
			}
		}
		return listaStatus;
	}

	public String getMensagem() {
		return mensagem;
	}
}
