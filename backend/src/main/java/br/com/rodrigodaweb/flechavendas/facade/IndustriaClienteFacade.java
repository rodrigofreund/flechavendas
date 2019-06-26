package br.com.rodrigodaweb.flechavendas.facade;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.service.IndustriaClientePrazoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaClienteService;

@Service
public class IndustriaClienteFacade {

	@Autowired
	private IndustriaClienteService industriaClienteService;

	@Autowired
	private IndustriaClientePrazoService industriaClientePrazoService;

	public void salvaIndustriaCliente(List<IndustriaClienteDto> listaIndustriaCliente, Cliente cliente) {
		listaIndustriaCliente.forEach(industriaClienteDto -> {
			if(industriaClienteDto.isRemovido()) {
				removeIndustriaCliente(industriaClienteDto);
			} else {
				adicionaIndustriaCliente(cliente, industriaClienteDto);
			}
			removeIndustriaClientePrazo(industriaClienteDto);
		});
	}

	public List<IndustriaClienteDto> buscaIndustriaCliente(Integer idCliente) {
		List<IndustriaClienteDto> listaIndustriaCliente = industriaClienteService.getIndustriaClientePorIdCliente(idCliente);
		if(CollectionUtils.isEmpty(listaIndustriaCliente)) {
			return Lists.newArrayList();
		}
		listaIndustriaCliente.forEach(item -> {
			item.setListaIndustriaClientePrazo(industriaClientePrazoService.getIndustriaClientePrazoPorIdIndustriaCliente(item.getId()));
		});
		return listaIndustriaCliente;
	}

	private void removeIndustriaClientePrazo(IndustriaClienteDto industriaClienteDto) {
		industriaClienteDto.getListaIndustriaClientePrazoParaRemover().forEach(industriaClientePrazoDto -> {
			industriaClientePrazoService.removeIndustriaClientePrazo(industriaClientePrazoDto.getId());
		});
	}

	private void adicionaIndustriaCliente(Cliente cliente, IndustriaClienteDto industriaClienteDto) {
		industriaClienteDto.setIdCliente(cliente.getId());
		Integer idIndustriaCliente = industriaClienteService.salvaIndustriaCliente(industriaClienteDto);
		industriaClienteDto.getListaIndustriaClientePrazo().forEach(industriaClientePrazoDto -> {
			industriaClientePrazoDto.setIdIndustriaCliente(idIndustriaCliente);
			industriaClientePrazoService.salvaIndustriaClientePrazo(industriaClientePrazoDto);
		});
	}

	private void removeIndustriaCliente(IndustriaClienteDto industriaClienteDto) {
		industriaClienteDto.getListaIndustriaClientePrazo().forEach(industriaClientePrazoDto -> {
			industriaClientePrazoService.removeIndustriaClientePrazo(industriaClientePrazoDto.getId());
		});
		industriaClienteService.removeIndustriaCliente(industriaClienteDto.getId());
	}
}
