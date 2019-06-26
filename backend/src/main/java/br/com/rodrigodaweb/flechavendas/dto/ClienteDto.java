package br.com.rodrigodaweb.flechavendas.dto;

import java.util.List;

import br.com.rodrigodaweb.flechavendas.model.Estado;
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
public class ClienteDto{

	private Integer id;
	private Integer codigo;
	private String razaoSocial;
	private String nomeFantasia;
	private String cpfCnpj;
	private String rgIe;
	private String rua;
	private Integer numero;
	private String sala;
	private String andar;
	private String complemento;
	private String bairro;
	private String cidade;
	private Long cep;
	private String telefone;
	private String celular;
	private String fax;
	private String contato;
	private String email;
	private String diasEntrega;
	private String horarioEntrega;
	private String nomeBanco;
	private Integer numeroAgencia;
	private String nomeAgencia;
	private Integer numeroConta;
	private Integer idPessoa;
	private boolean ativo;
	private boolean bloqueioVenda;
	private String informacoesAdicionais;
	private List<IndustriaClienteDto> listaIndustriaCliente;
	private List<RepresentacaoClienteDto> listaRepresentacoesCliente;
	private Estado estado;
	private Integer newPage;
	private Integer pageSize;
	private Integer idUsuario;
	private List<Long> idsRepresentacao; 
	private UsuarioDto vendedorFiltro;
	private boolean pendenteRegistro;
	private String nomeFormatado;
	private Boolean vendedor;
	private boolean excluido;
	private String referenciasComerciais;
	List<ArquivoClienteDto> arquivos;
}
