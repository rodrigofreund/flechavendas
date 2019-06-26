package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;
import java.util.List;

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
public class ObservacaoPedidoUpdateDto implements Serializable {
	private static final long serialVersionUID = 1372585537078425265L;
	private Long idPedido;
	private List<ObservacaoPedidoDto> listaObservacaoPedidoDto;
}
