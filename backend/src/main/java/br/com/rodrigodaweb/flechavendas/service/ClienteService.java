package br.com.rodrigodaweb.flechavendas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.ClienteDAO;
import br.com.rodrigodaweb.flechavendas.dto.BuscaClientesDto;
import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.dto.RepresentacaoDto;
import br.com.rodrigodaweb.flechavendas.mapper.ClienteMapper;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.model.Representacao;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDao;

	@Autowired
	private RepresentacaoService representacaoService;

	@Autowired
	private UsuarioService usuarioService;

	public List<ClienteDto> buscaClientes() {
		return clienteDao.findAll().stream().map(ClienteMapper::toClienteDto).collect(Collectors.toList());
	}

	public Page<ClienteDto> buscaClientes(ClienteDto searchDto) {
		adicionaRepresentacoesBusca(searchDto);
		return new PageImpl<>(ClienteMapper.toClienteDto(clienteDao.find(searchDto)));
	}

	public ClienteDto buscaClientePorCnpj(String cnpj) {
		return ClienteMapper.toClienteDto(clienteDao.findByCpfCnpj(cnpj));
	}

	public List<ClienteDto> buscaClientesPorRepresentacao(BuscaClientesDto buscaClientesDto) {
		Representacao representacao = representacaoService.getRepresentacaoUsuarioIndustria(buscaClientesDto.idUsuario,
				buscaClientesDto.idIndustria);
		return clienteDao.buscaClientePorRepresentacao(representacao.getId()).stream().map(ClienteMapper::toClienteDto)
				.collect(Collectors.toList());
	}

	public Cliente salvarCliente(ClienteDto clienteDto) {
		Cliente cliente = ClienteMapper.toCliente(clienteDto);
		preencheRepresentacoesCliente(clienteDto, cliente);
		return clienteDao.save(cliente);
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	public Cliente buscaCliente(Integer id) {
		return clienteDao.findOne(id);
	}

	public Cliente findById(Integer idCliente) {
		return clienteDao.findOne(idCliente);
	}

	public Long buscaTotalClientes() {
		return clienteDao.count();
	}

	public ClienteDto excluirCliente(Integer idCliente) {
		Cliente cliente = clienteDao.findOne(idCliente);
		cliente.setExcluido(true);
		return ClienteMapper.toClienteDto(clienteDao.save(cliente));
	}

	public Long getNumeroClientesPendentes() {
		return clienteDao.getNumeroClientesPendentes();
	}
	
	public Iterable<Cliente> salvarClientes(Set<Cliente> clientes) {
		return clienteDao.save(clientes);
	}

	private void preencheRepresentacoesCliente(ClienteDto clienteDto, Cliente cliente) {
		if (CollectionUtils.isNotEmpty(clienteDto.getListaRepresentacoesCliente())) {
			cliente.setRepresentantes(clienteDto.getListaRepresentacoesCliente().stream()
					.map(item -> representacaoService.findById(item.id)).collect(Collectors.toSet()));
		}
	}

	private void adicionaRepresentacoesBusca(ClienteDto searchDto) {
		Usuario usuario = usuarioService.buscaPorId(searchDto.getIdUsuario());
		boolean isVendedor = usuario.getPerfil().getNome().equals("Vendedor");
		if (isVendedor) {
			searchDto.setIdsRepresentacao(new ArrayList<Long>());
			List<RepresentacaoDto> listaRepresentacao = representacaoService
					.getRepresentacoesUsuario(searchDto.getIdUsuario());
			listaRepresentacao.forEach(item -> searchDto.getIdsRepresentacao().add(item.getId()));
		} else {
			if (searchDto.getVendedorFiltro() != null) {
				searchDto.setIdsRepresentacao(new ArrayList<Long>());
				List<RepresentacaoDto> listaRepresentacao = representacaoService
						.getRepresentacoesUsuario(searchDto.getVendedorFiltro().getId());
				listaRepresentacao.forEach(item -> searchDto.getIdsRepresentacao().add(item.getId()));
			}
		}
	}
}
