package br.com.rodrigodaweb.flechavendas;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodaweb.flechavendas.dto.ArquivoClienteDto;
import br.com.rodrigodaweb.flechavendas.dto.AtualizaStatusPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.BuscaClientesDto;
import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.dto.ConsultaClienteCadastradoDto;
import br.com.rodrigodaweb.flechavendas.dto.EstadoDto;
import br.com.rodrigodaweb.flechavendas.dto.FiltroPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ImportacaoIndustriaStatusDto;
import br.com.rodrigodaweb.flechavendas.dto.ImportarUsuarioDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaClienteDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaClientePrazoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoSearchDto;
import br.com.rodrigodaweb.flechavendas.dto.ItemDto;
import br.com.rodrigodaweb.flechavendas.dto.ItemPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ListagemPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ObservacaoPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.ObservacaoPedidoUpdateDto;
import br.com.rodrigodaweb.flechavendas.dto.PedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.PerfilDto;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.dto.StatusPedidoDto;
import br.com.rodrigodaweb.flechavendas.dto.TipoPessoaDto;
import br.com.rodrigodaweb.flechavendas.dto.UltimasVendasItemSearchDto;
import br.com.rodrigodaweb.flechavendas.dto.UltimasVendasItensResultDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioCadastroDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioSearchDto;
import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.facade.ClienteFacade;
import br.com.rodrigodaweb.flechavendas.facade.IndustriaClienteFacade;
import br.com.rodrigodaweb.flechavendas.facade.IndustriaPrazoFacade;
import br.com.rodrigodaweb.flechavendas.facade.UsuarioFacade;
import br.com.rodrigodaweb.flechavendas.service.ArquivoService;
import br.com.rodrigodaweb.flechavendas.service.ClienteService;
import br.com.rodrigodaweb.flechavendas.service.EstadoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaClientePrazoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaClienteService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaPrazoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaService;
import br.com.rodrigodaweb.flechavendas.service.ItemPedidoService;
import br.com.rodrigodaweb.flechavendas.service.ItemService;
import br.com.rodrigodaweb.flechavendas.service.ObservacaoPedidoService;
import br.com.rodrigodaweb.flechavendas.service.PedidoService;
import br.com.rodrigodaweb.flechavendas.service.PerfilService;
import br.com.rodrigodaweb.flechavendas.service.RepresentacaoService;
import br.com.rodrigodaweb.flechavendas.service.StatusPedidoService;
import br.com.rodrigodaweb.flechavendas.service.TipoPessoaService;
import br.com.rodrigodaweb.flechavendas.service.UsuarioService;

@CrossOrigin(origins = { "*", "https://sistema.flechavendas.com.br", "https://sistemateste.flechavendas.com.br" })
@RestController
public class EntryPoint {

	static final Logger log = Logger.getLogger(EntryPoint.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RepresentacaoService representacaoService;

	@Autowired
	private IndustriaService industriaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private IndustriaClienteService industriaClienteService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private StatusPedidoService statusPedidoService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private TipoPessoaService tipoPessoaService;

	@Autowired
	private IndustriaPrazoFacade industriaPrazoFacade;

	@Autowired
	private IndustriaClientePrazoService industriaClientePrazoService;

	@Autowired
	private ClienteFacade clienteFacade;

	@Autowired
	private IndustriaClienteFacade industriaClienteFacade;

	@Autowired
	private IndustriaPrazoService industriaPrazoService;

	@Autowired
	private ObservacaoPedidoService observacaoPedidoService;

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private UsuarioFacade usuarioFacade;

	@Autowired
	private PerfilService perfilService;

	@GetMapping(value = "/getIndustrias")
	public List<IndustriaDto> getIndustrias() {
		return industriaService.findAll();
	}

	@PostMapping(value = "/getIndustriasUsuario")
	public List<IndustriaDto> getIndustriasByIdUsuario(@RequestBody Integer idUsuario) {
		return industriaService.getIndustriasUsuario(idUsuario);
	}

	@PostMapping(value = "/getClientesByLogin")
	public List<ClienteDto> getClientes(@RequestBody String login) {
		return clienteService.buscaClientes();
	}

	@PostMapping(value = "/getClientesByFilter")
	public Page<ClienteDto> getClientesFilter(@RequestBody ClienteDto filter) {
		return clienteService.buscaClientes(filter);
	}

	@PostMapping(value = "/getClienteExistente")
	public ClienteDto getClientePorCnpj(@RequestBody String cnpj) {
		return clienteService.buscaClientePorCnpj(cnpj);
	}

	@GetMapping(value = "/getTotalClientes")
	public Long getTotalClientes() {
		return clienteService.buscaTotalClientes();
	}

	@PostMapping(value = "/getClientesPorRepresentacao")
	public List<ClienteDto> getClientesPorRepresentacao(@RequestBody BuscaClientesDto buscaClientesDto) {
		return clienteService.buscaClientesPorRepresentacao(buscaClientesDto);
	}

	@PostMapping(value = "/getIndustriaCliente")
	public boolean getIndustriaCliente(@RequestBody ConsultaClienteCadastradoDto consultacliente) {
		return industriaClienteService.estaClienteCadastradoIndustria(consultacliente.getIdCliente(),
				consultacliente.getIdIndustria());
	}

	@PostMapping(value = "/salvaPedido")
	public PedidoDto salvaPedido(@RequestBody PedidoDto pedidoDto) {
		return pedidoService.salvaPedido(pedidoDto);
	}

	@PostMapping(value = "/atualizarStatusPedido")
	public PedidoDto atualizarStatusPedido(@RequestBody AtualizaStatusPedidoDto statusPedido) {
		return pedidoService.atualizarStatusPedido(statusPedido.idPedido, statusPedido.statusPedido);
	}

	@PostMapping(value = "/getItensPorIdTabela")
	public List<ItemDto> getItensPorIdTabela(@RequestBody Long idTabela) {
		return itemService.getItensPorIdTabela(idTabela);
	}

	@PostMapping(value = "/getVendedoresPorIndustria")
	public List<UsuarioDto> getVendedoresPorIndustria(@RequestBody Integer idIndustria) {
		return usuarioService.getVendedoresPorIndustria(idIndustria);
	}

	@PostMapping(value = "/getPedidosPorCriteria")
	public Page<ListagemPedidoDto> getPedidosPorCriteria(@RequestBody FiltroPedidoDto search) {
		return pedidoService.getPedidosPorFiltro(search);
	}

	@PostMapping(value = "/getItensPedido")
	public List<ItemPedidoDto> getItensPedido(@RequestBody Long idPedido) {
		return itemPedidoService.getItensPedido(idPedido);
	}

	@PostMapping(value = "/getPedido")
	public PedidoDto getPedido(@RequestBody Long idPedido) {
		return pedidoService.getPedidoDetalhado(idPedido);
	}

	@PostMapping(value = "/uploadArquivoCliente")
	public List<ArquivoClienteDto> uploadArquivoCliente(
			@RequestPart(value = "files", required = true) MultipartFile[] mFile,
			@RequestPart(value = "cpfCnpj", required = true) String cpfCnpj) {
		return arquivoService.uploadArquivoCliente(cpfCnpj, mFile);
	}

	@GetMapping(value = "/downloadArquivoCliente", produces = MediaType.IMAGE_JPEG_VALUE)
	public String downloadArquivoCliente(@RequestParam(value = "cpfCnpj") String cpfCnpj,
			@RequestParam(value = "nomeArquivo") String nomeArquivo) throws IOException {
		return arquivoService.downloadArquivo(cpfCnpj, nomeArquivo);
	}

	@GetMapping(value = "/getListaStatusPedido")
	public List<StatusPedidoDto> getListaStatusPedido() {
		return statusPedidoService.getListaStatus();
	}

	@PostMapping(value = "/salvarCliente")
	public ClienteDto salvarCliente(@RequestBody ClienteDto clienteDto) {
		return clienteFacade.salvarCliente(clienteDto);
	}

	@GetMapping(value = "/buscaEstados")
	public List<EstadoDto> buscaEstados() {
		return estadoService.buscaEstados();
	}

	@GetMapping(value = "/buscaUsuarios")
	public List<UsuarioDto> buscaUsuarios() {
		return usuarioService.buscaUsuarios();
	}

	@GetMapping(value = "/buscaListaTipoPessoa")
	public List<TipoPessoaDto> buscaListaTipoPessoa() {
		return tipoPessoaService.getListaTipoPessoa();
	}

	@PostMapping(value = "/buscaIndustriaCliente")
	public List<IndustriaClienteDto> buscaIndustriaCliente(@RequestBody Integer idCliente) {
		return industriaClienteFacade.buscaIndustriaCliente(idCliente);
	}

	@PostMapping(value = "/buscaRepresentacoesIndustria")
	public List<RepresentacaoDto> buscaRepresentacoesIndustria(@RequestBody Integer idIndustria) {
		return representacaoService.getRepresentacoesIndustria(idIndustria);
	}

	@PostMapping(value = "/buscaRepresentacoesUsuario")
	public List<RepresentacaoDto> buscaRepresentacoesUsuario(@RequestBody Integer idUsuario) {
		return representacaoService.getRepresentacoesUsuario(idUsuario);
	}

	@PostMapping(value = "/excluirCliente")
	public ClienteDto excluirCliente(@RequestBody Integer idCliente) {
		return clienteService.excluirCliente(idCliente);
	}

	@GetMapping(value = "/getNumeroPedidosEnviados")
	public Integer getNumeroPedidosEnviados() {
		return pedidoService.getNumeroPedidosAprovados();
	}

	@GetMapping(value = "/getNumeroPedidosNegados")
	public Integer getNumeroPedidosNegados(@RequestParam("idUsuario") Integer idUsuario) {
		return pedidoService.getNumeroPedidosNegados(idUsuario);
	}

	@GetMapping(value = "/getNumeroClientesPendentes")
	public Long getNumeroClientesPendentes() {
		return clienteService.getNumeroClientesPendentes();
	}

	@GetMapping(value = "/getIdentificadorAtualizacaoBancoDeDados")
	public Long getIdentificadorAtualizacaoBancoDeDados() {
		return 115478445L;
	}

	@PostMapping(value = "/salvaIndustriaPrazo")
	public void salvaIndustriaPrazo(@RequestBody IndustriaPrazoDto industriaPrazoDto) {
		try {
			industriaPrazoFacade.salvar(industriaPrazoDto);
		} catch (Exception ex) {
			throw new BusinessException(1, "Erro ao salvar prazo da indústria");
		}
	}

	@GetMapping(value = "/getIndustriaPrazo", produces = "application/json")
	public List<IndustriaPrazoDto> getIndustriaPrazo(@RequestParam("idIndustria") Integer idIndustria) {
		return industriaPrazoFacade.getIndustriaPrazo(idIndustria);
	}

	@GetMapping(value = "/getIndustriaPrazoById", produces = "application/json")
	public IndustriaPrazoDto getIndustriaPrazoById(@RequestParam("idIndustriaPrazo") Integer idIndustriaPrazo) {
		return industriaPrazoService.buscarPorId(idIndustriaPrazo);
	}

	@PostMapping(value = "/getIndustriaPrazoOuIndustriaClientePrazo", produces = "application/json")
	public List<IndustriaPrazoPedidoDto> getIndustriaPrazoClientePrazo(HttpServletResponse response,
			@RequestBody IndustriaPrazoSearchDto industriaPrazoSearchDto) {
		return industriaPrazoFacade.buscaPrazoParaPedido(industriaPrazoSearchDto.getIdCliente(),
				industriaPrazoSearchDto.getIdIndustria());
	}

	@GetMapping(value = "/removerIndustriaPrazo")
	public void removerIndustriaPrazo(@RequestParam("idIndustriaPrazo") Integer idIndustriaPrazo) {
		industriaPrazoFacade.removerIndustriaPrazo(idIndustriaPrazo);
	}

	@GetMapping(value = "/getIndustriaClientePrazoPorIdIndustriaCliente")
	public List<IndustriaClientePrazoDto> getIndustriaClientePrazoPorIdIndustriaCliente(
			@RequestParam("idIndustriaCliente") Integer idIndustriaCliente) {
		return industriaClientePrazoService.getIndustriaClientePrazoPorIdIndustriaCliente(idIndustriaCliente);
	}

	@GetMapping(value = "/getObservacoesPedido")
	public List<ObservacaoPedidoDto> getObservacoesPedido(@RequestParam("idPedido") Long idPedido) {
		return observacaoPedidoService.getObservacoesPedido(idPedido);
	}

	@PostMapping(value = "/setObservacoesPedido")
	public List<ObservacaoPedidoDto> setObservacoesPedido(
			@RequestBody ObservacaoPedidoUpdateDto observacaoPedidoUpdateDto) {
		return observacaoPedidoService.salvaObservacoesPedido(observacaoPedidoUpdateDto);
	}

	@PostMapping(value = "/getUltimasVendasItem")
	public List<UltimasVendasItensResultDto> getUltimasVendasItem(@RequestBody UltimasVendasItemSearchDto search) {
		return itemPedidoService.getUltimasVendasItem(search);
	}

	@PostMapping(value = "/getUsuariosByFilter")
	public Page<UsuarioDto> getUsuariosByFilter(@RequestBody UsuarioSearchDto filter) {
		return usuarioService.buscaPorFiltro(filter);
	}

	@PostMapping(value = "/salvaUsuario")
	public UsuarioCadastroDto salvaUsuario(@RequestBody UsuarioCadastroDto cadastroDto) {
		return usuarioFacade.cadastroUsuarioFacade(cadastroDto);
	}
	
	@PostMapping(value = "/buscaUsuarioPorLogin")
	public UsuarioDto buscaUsuarioPorLogin(@RequestBody String login) {
		return usuarioService.buscaPorLogin(login);
	}

	@GetMapping(value = "/getListaPerfil")
	public List<PerfilDto> getListaPerfil() {
		return perfilService.getListaPerfil();
	}

	@GetMapping(value = "/buscaUsuarioPorId")
	public UsuarioCadastroDto buscaUsuarioPorId(@RequestParam("idUsuario") Integer idUsuario) {
		return usuarioFacade.buscaPorId(idUsuario);
	}

	@PostMapping(value = "/importarBaseUsuario")
	public Integer importarBaseUsuario(@RequestBody List<ImportacaoIndustriaStatusDto> listaParaImportar) {
		return usuarioFacade.importaBaseUsuario(listaParaImportar);
	}

	@PostMapping(value = "/verificarImportacaoBaseUsuario")
	public List<ImportacaoIndustriaStatusDto> verificarImportacaoBaseUsuario(
			@RequestBody ImportarUsuarioDto importacaoUsuarioDto) {
		return usuarioFacade.verificarImportacaoBaseUsuario(importacaoUsuarioDto);
	}

	@GetMapping(value = "/buscaRepresentacoesPorIdUsuario")
	public UsuarioCadastroDto buscaRepresentacoes(@RequestParam("idUsuario") Integer idUsuario) {
		return usuarioFacade.buscaRepresentacoesPorIdUsuario(idUsuario);
	}
}