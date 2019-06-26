package br.com.rodrigodaweb.flechavendas.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DownloadTabelaDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nomeArquivo;
	private String arquivo;
}
