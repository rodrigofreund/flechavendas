package br.com.rodrigodaweb.flechavendas.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;

public class ClienteMapper {
	private ClienteMapper() {}

	public static ClienteDto toClienteDto(Cliente cliente) {
		if(cliente == null) {
			return null;
		}
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setAndar(cliente.getAndar());
		clienteDto.setAtivo(cliente.isAtivo());
		clienteDto.setBairro(cliente.getBairro());
		clienteDto.setBloqueioVenda(cliente.isBloqueioVenda());
		clienteDto.setCelular(cliente.getCelular());
		clienteDto.setCep(cliente.getCep());
		clienteDto.setCidade(cliente.getCidade());
		clienteDto.setComplemento(cliente.getComplemento());
		clienteDto.setContato(cliente.getContato());
		clienteDto.setCpfCnpj(cliente.getCpfCnpj());
		clienteDto.setDiasEntrega(cliente.getDiasEntrega());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setEstado(cliente.getEstado());
		clienteDto.setFax(cliente.getFax());
		clienteDto.setHorarioEntrega(cliente.getHorarioEntrega());
		clienteDto.setId(cliente.getId());
		clienteDto.setIdPessoa(cliente.getIdPessoa());
		clienteDto.setInformacoesAdicionais(cliente.getInformacoesAdicionais());
		clienteDto.setNomeAgencia(cliente.getNomeAgencia());
		clienteDto.setNomeBanco(cliente.getNomeBanco());
		clienteDto.setNomeFantasia(cliente.getNomeFantasia());
		clienteDto.setNumero(cliente.getNumero());
		clienteDto.setNumeroAgencia(cliente.getNumeroAgencia());
		clienteDto.setNumeroConta(cliente.getNumeroConta());
		clienteDto.setRazaoSocial(cliente.getRazaoSocial());
		clienteDto.setRgIe(cliente.getRgIe());
		clienteDto.setRua(cliente.getRua());
		clienteDto.setSala(cliente.getSala());
		clienteDto.setTelefone(cliente.getTelefone());
		clienteDto.setPendenteRegistro(cliente.isPendenteRegistro());
		clienteDto.setNomeFormatado(cliente.getCpfCnpj() + " - " + cliente.getRazaoSocial());
		clienteDto.setExcluido(cliente.isExcluido());
		clienteDto.setReferenciasComerciais(cliente.getReferenciasComerciais());

		if (cliente.getClienteIndustrias() != null && !cliente.getClienteIndustrias().isEmpty()) {
			clienteDto.setListaIndustriaCliente(new ArrayList<>());
			cliente.getClienteIndustrias().forEach(item -> clienteDto.getListaIndustriaCliente()
					.add(IndustriaClienteMapper.toIndustriaClienteDto(item)));
		}

		if (cliente.getRepresentantes() != null && !cliente.getRepresentantes().isEmpty()) {
			clienteDto.setListaRepresentacoesCliente(new ArrayList<>());
			cliente.getRepresentantes().forEach(item -> clienteDto.getListaRepresentacoesCliente()
					.add(RepresentacaoRepresentacaoClienteDtoMapper.representacaoRepresentacaoClienteDto(item)));
		}
		
		if(!CollectionUtils.isEmpty(cliente.getArquivos())) {
			clienteDto.setArquivos(ArquivoClienteMapper.toArquivoClienteDtoList(cliente.getArquivos()));
		}

		return clienteDto;
	}

	public static Cliente toCliente(ClienteDto dto) {
		return Cliente.builder()
			.id(dto.getId())
			.andar(dto.getAndar())
			.ativo(dto.isAtivo())
			.bairro(dto.getBairro())
			.bloqueioVenda(dto.isBloqueioVenda())
			.celular(dto.getCelular())
			.cep(dto.getCep())
			.cidade(dto.getCidade())
			.complemento(dto.getComplemento())
			.contato(dto.getContato())
			.cpfCnpj(dto.getCpfCnpj())
			.diasEntrega(dto.getDiasEntrega())
			.email(dto.getEmail())
			.fax(dto.getFax())
			.horarioEntrega(dto.getHorarioEntrega())
			.nomeBanco(dto.getNomeBanco())
			.idPessoa(dto.getIdPessoa())
			.informacoesAdicionais(dto.getInformacoesAdicionais())
			.nomeAgencia(dto.getNomeAgencia())
			.nomeFantasia(dto.getNomeFantasia())
			.numero(dto.getNumero())
			.numeroAgencia(dto.getNumeroAgencia())
			.numeroConta(dto.getNumeroConta())
			.razaoSocial(dto.getRazaoSocial())
			.rgIe(dto.getRgIe())
			.rua(dto.getRua())
			.sala(dto.getSala())
			.telefone(dto.getTelefone())
			.estado(dto.getEstado())
			.excluido(false)
			.pendenteRegistro(dto.isPendenteRegistro())
			.referenciasComerciais(dto.getReferenciasComerciais())
		.build();
	}
	
	public static List<ClienteDto> toClienteDto(List<Cliente> cliente) {
		return cliente.stream().map(ClienteMapper::toClienteDto).collect(Collectors.toList());
	}
}
