package br.com.rodrigodaweb.flechavendas.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PedidoDto {
	private Long id;
	private Integer idIndustria;
	private int idCliente;
	private Long idTabela;
	private Integer idIndustriaPrazo;
	private Boolean proposta;
	private Integer carga;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale="pt_BR", timezone="Brazil/East")
	private Date dataEntrega;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", locale="pt_BR", timezone="Brazil/East")
	private Date dataPedido;
	private Integer idUsuario;
	private Integer statusPedido;
	private BigInteger codigoPedidoIndustria;
	private Boolean alterado;
	private List<ItemPedidoDto> itensPedido;
	private List<ObservacaoPedidoDto> observacoesPedidoDto;
	private UsuarioDto usuario;
	private UsuarioDto usuarioAlteracao;
	private IndustriaDto industria;
	private ClienteDto cliente;
	private TabelaDto tabela;
	private IndustriaPrazoDto industriaPrazo;
}
