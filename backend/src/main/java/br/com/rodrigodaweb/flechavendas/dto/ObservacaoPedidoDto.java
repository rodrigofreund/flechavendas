package br.com.rodrigodaweb.flechavendas.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObservacaoPedidoDto {

	private Long idObservacao;

	private Long idPedido;

	private String nomeUsuario;

	private Date dataCriacao;

	private Date dataLeitura;

	private boolean lido;

	private Integer idUsuario;

	private String observacao;
}
