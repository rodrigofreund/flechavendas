package br.com.rodrigodaweb.flechavendas.facade;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import br.com.rodrigodaweb.flechavendas.dto.ImportacaoIndustriaClienteDto;
import br.com.rodrigodaweb.flechavendas.dto.ImportacaoIndustriaStatusDto;
import br.com.rodrigodaweb.flechavendas.dto.ImportacaoIndustriaStatusDto.ImportacaoIndustriaStatusDtoBuilder;
import br.com.rodrigodaweb.flechavendas.dto.ImportarUsuarioDto;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioCadastroDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.mapper.RepresentacaoMapper;
import br.com.rodrigodaweb.flechavendas.mapper.UsuarioMapper;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.Representacao;
import br.com.rodrigodaweb.flechavendas.model.Usuario;
import br.com.rodrigodaweb.flechavendas.service.ClienteService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaService;
import br.com.rodrigodaweb.flechavendas.service.RepresentacaoService;
import br.com.rodrigodaweb.flechavendas.service.UsuarioService;

@Service
@Transactional
public class UsuarioFacade {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RepresentacaoService representacaoService;
	
	@Autowired
	private IndustriaService industriaService;

	@Autowired
	private ClienteService clienteService;

	public UsuarioCadastroDto cadastroUsuarioFacade(UsuarioCadastroDto cadastroDto) {
		UsuarioDto usuarioExistente = usuarioService.buscaPorLogin(cadastroDto.getLogin());
		if(usuarioExistente != null && cadastroDto.getId() == null) {
			throw new BusinessException(63, "Usuário com login já cadastrado");
		}
		Usuario usuario = usuarioService.salvarUsuario(cadastroDto);
		List<Representacao> representacoes = representacaoService.salvaRepresentacaoUsuario(cadastroDto);
		representacoes.forEach(item -> {
			item.setIndustria(industriaService.findById(item.getIndustria().getId()));
			item.setUsuario(usuarioService.buscaPorId(item.getUsuario().getId()));
		});
		
		return UsuarioMapper.toUsuarioCadastroDto(usuario, RepresentacaoMapper.toRepresentacaoDtoList(representacoes));
	}

	public UsuarioCadastroDto buscaPorId(Integer idUsuario) {
		Usuario usuario = usuarioService.buscaPorId(idUsuario);
		return UsuarioMapper.toUsuarioCadastroDto(usuario, RepresentacaoMapper.toRepresentacaoDtoList(usuario.getRepresentacoes()));
	}
	
	public UsuarioCadastroDto buscaRepresentacoesPorIdUsuario(Integer idUsuario) {
		Usuario usuario = usuarioService.buscaPorId(idUsuario);
		List<RepresentacaoDto> reps = representacaoService.getRepresentacoesUsuario(idUsuario);
		return UsuarioMapper.toUsuarioCadastroDto(usuario, reps);
	}

	/**
	 * IMPORTA USUARIOS DO USUARIO DE ORIGEM PARA O USUARIO DE DESTINO
	 */
	public Integer importaBaseUsuario(List<ImportacaoIndustriaStatusDto> listaParaImportar) {

		Set<Cliente> clienteParaSalvar = Sets.newTreeSet();

		listaParaImportar.forEach(impIndDto -> {

			if (impIndDto.getIdRepresentacao() == null) {
				impIndDto.getListaCliente().forEach(cliente -> {
					if (cliente.isImportar()) {
						Representacao repDestino = buscaRepresentacao(impIndDto);
						repDestino.setId(representacaoService.salvaRepresentacao(repDestino).getId());
						Cliente cli = clienteService.findById(cliente.getIdCliente());
						cli.getRepresentantes().add(repDestino);
						clienteParaSalvar.add(cli);
					}
				});
			} else {
				impIndDto.getListaCliente().forEach(cliente -> {
					if (cliente.isImportar()) {
						Representacao repDestino = representacaoService.findById(impIndDto.getIdRepresentacao());
						Cliente cli = clienteService.findById(cliente.getIdCliente());
						cli.getRepresentantes().add(repDestino);
						clienteParaSalvar.add(cli);
					}
				});
			}
		});
		return Sets.newTreeSet(clienteService.salvarClientes(clienteParaSalvar)).size();
	}

	private Representacao buscaRepresentacao(ImportacaoIndustriaStatusDto impIndDto) {
		Representacao repDestino = representacaoService.getRepresentacaoUsuarioIndustria(impIndDto.getIdUsuario(),
				impIndDto.getIdIndustria());
		if (repDestino == null) {
			repDestino = Representacao.builder()
					.ativo(true)
					.industria(Industria.builder().id(impIndDto.getIdIndustria()).build())
					.usuario(Usuario.builder().id(impIndDto.getIdUsuario()).build())
					.build();
		} else {
			repDestino.setAtivo(true);
		}
		return repDestino;
	}

	public List<ImportacaoIndustriaStatusDto> verificarImportacaoBaseUsuario(ImportarUsuarioDto importacaoUsuarioDto) {

		List<ImportacaoIndustriaStatusDto> result = Lists.newArrayList();

		Usuario usuarioOrigem = usuarioService.buscaPorId(importacaoUsuarioDto.getIdUsuarioOrigem());
		Usuario usuarioDestino = usuarioService.buscaPorId(importacaoUsuarioDto.getIdUsuarioDestino());

		usuarioOrigem.getRepresentacoes().forEach(representacaoOrigem -> {
			Representacao representacaoDestino = existeRepresentacaoNoDestino(representacaoOrigem, usuarioDestino);
			Set<ImportacaoIndustriaClienteDto> clientes = criaListaClientesParaImportar(
					representacaoDestino, representacaoOrigem);

			if (!clientes.isEmpty()) {
				ImportacaoIndustriaStatusDtoBuilder builder = ImportacaoIndustriaStatusDto.builder();
				builder.idIndustria(representacaoOrigem.getIndustria().getId());
				builder.idUsuario(usuarioDestino.getId());
				builder.idRepresentacao(representacaoDestino == null ? null : representacaoDestino.getId());
				builder.nomeIndustria(representacaoOrigem.getIndustria().getNome());
				builder.listaCliente(clientes);
				result.add(builder.build());
			}
		});

		return result;
	}

	private Set<ImportacaoIndustriaClienteDto> criaListaClientesParaImportar(Representacao representacaoDestino,
			Representacao representacaoOrigem) {

		Set<ImportacaoIndustriaClienteDto> usuarios = Sets.newHashSet();

		// CASO JA EXISTA UMA REPRESENTACAO NO USUARIO DE DESTINO
		if (representacaoDestino != null) {
			Set<Cliente> listaClientesInexistente = criaListaClienteInexistente(representacaoOrigem,
					representacaoDestino);
			listaClientesInexistente.forEach(clienteParaSerAdicionado -> {
				usuarios.add(ImportacaoIndustriaClienteDto.builder()
						.idCliente(clienteParaSerAdicionado.getId())
						.nome(clienteParaSerAdicionado.getRazaoSocial())
						.importar(false)
						.build());
			});
		} else {
			// CASO NAO EXISTA A REPRESENTACAO NO USUARIO DE DESTINO
			representacaoOrigem.getClientes().forEach(clienteParaSerAdicionado -> {
				usuarios.add(ImportacaoIndustriaClienteDto.builder()
						.idCliente(clienteParaSerAdicionado.getId())
						.nome(clienteParaSerAdicionado.getRazaoSocial())
						.importar(false)
						.build());
			});
		}
		return usuarios;
	}

	private Set<Cliente> criaListaClienteInexistente(Representacao representacaoOrigem,
			Representacao representacaoDestino) {

		Set<Cliente> novosClientesOrigem = new TreeSet<Cliente>(representacaoOrigem.getClientes());
		novosClientesOrigem.removeAll(representacaoDestino.getClientes());
		return novosClientesOrigem;
	}

	private Representacao existeRepresentacaoNoDestino(Representacao repOrigem, Usuario usuarioDestino) {
		Optional<Representacao> optRep = usuarioDestino.getRepresentacoes().stream().filter(repDest -> {
			return repDest.getIndustria().getId().equals(repOrigem.getIndustria().getId());
		}).findFirst();
		return optRep.isPresent() ? optRep.get() : null;
	}
}
