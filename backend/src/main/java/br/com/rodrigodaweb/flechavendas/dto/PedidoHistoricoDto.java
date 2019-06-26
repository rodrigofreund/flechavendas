package br.com.rodrigodaweb.flechavendas.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoHistoricoDto {

	private Long id;
	private PedidoDto pedidoDto;
	private Date dataHoraAlteracao;
	private Integer status;
	private UsuarioDto usuarioDto;
}
