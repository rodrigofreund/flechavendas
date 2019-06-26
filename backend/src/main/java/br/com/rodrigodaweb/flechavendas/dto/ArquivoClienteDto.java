package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idCliente;
	private String nomeArquivo;
}
