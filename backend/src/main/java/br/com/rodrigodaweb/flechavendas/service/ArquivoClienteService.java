package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.ArquivoClienteDAO;
import br.com.rodrigodaweb.flechavendas.dto.ArquivoClienteDto;
import br.com.rodrigodaweb.flechavendas.mapper.ArquivoClienteMapper;
import br.com.rodrigodaweb.flechavendas.model.ArquivoCliente;
import br.com.rodrigodaweb.flechavendas.model.Cliente;

@Service
@Transactional
public class ArquivoClienteService {

	@Autowired
	private ArquivoClienteDAO dao;

	public ArquivoCliente salvar(ArquivoClienteDto arquivoClienteDto) {
		return dao.save(ArquivoClienteMapper.toArquivoCliente(arquivoClienteDto));
	}

	public void salvar(List<ArquivoClienteDto> arquivoClienteDtoList, Cliente cliente) {
		arquivoClienteDtoList.forEach(item -> {
			if(item.getIdCliente() == null) {
				item.setIdCliente(cliente.getId());
			}
		});
		cliente.setArquivos(arquivoClienteDtoList.stream()
				.map(item -> salvar(item)).collect(Collectors.toSet()));
	}
}
