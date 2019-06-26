package br.com.rodrigodaweb.flechavendas.facade;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.mapper.ClienteMapper;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.service.ArquivoClienteService;
import br.com.rodrigodaweb.flechavendas.service.ClienteService;

@Service
public class ClienteFacade {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private IndustriaClienteFacade industriaClienteFacade;
	
	@Autowired
	private ArquivoClienteService arquivoClienteService;

	public ClienteDto salvarCliente(ClienteDto clienteDto) {
		Cliente cliente = clienteService.salvarCliente(clienteDto);

		if (CollectionUtils.isNotEmpty(clienteDto.getListaIndustriaCliente())) {
			industriaClienteFacade.salvaIndustriaCliente(clienteDto.getListaIndustriaCliente(), cliente);
		}

		if(CollectionUtils.isNotEmpty(clienteDto.getArquivos())) {
			arquivoClienteService.salvar(clienteDto.getArquivos(), cliente);
		}
		return ClienteMapper.toClienteDto(cliente);
	}
}
