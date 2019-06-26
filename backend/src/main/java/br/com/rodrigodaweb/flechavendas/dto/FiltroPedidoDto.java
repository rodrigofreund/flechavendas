package br.com.rodrigodaweb.flechavendas.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroPedidoDto {
	private Integer idIndustria;
	private Integer idUsuario;
	private Integer idStatus;
	private Integer idPedido;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtInicio;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtFim;
	private Integer idCliente;
	private Integer newPage;
	private Integer pageSize;
}
