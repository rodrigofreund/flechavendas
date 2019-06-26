package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.IndustriaClienteDAO;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaClienteDto;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaClienteMapper;
import br.com.rodrigodaweb.flechavendas.model.IndustriaCliente;

@Service
@Transactional
public class IndustriaClienteService {
	@Autowired
	private IndustriaClienteDAO industriaClienteDAO;

	public boolean estaClienteCadastradoIndustria(Integer idCliente, Integer idIndustria) {
		return industriaClienteDAO.estaClienteCadastradoIndustria(idIndustria, idCliente) > 0;
	}

	public List<IndustriaClienteDto> getIndustriaClientePorIdCliente(Integer idCliente) {
		return industriaClienteDAO.buscaIndustriaCliente(idCliente).stream()
				.map(IndustriaClienteMapper::toIndustriaClienteDto).collect(Collectors.toList());
	}

	public IndustriaCliente findById(Integer id) {
		return industriaClienteDAO.findOne(id);
	}
	
	public Integer salvaIndustriaCliente(IndustriaClienteDto industriaClienteDto) {
		return industriaClienteDAO.save(IndustriaClienteMapper.toIndustriaCliente(industriaClienteDto)).getId();
	}

	public void removeIndustriaCliente(Integer id) {
		industriaClienteDAO.delete(id);
	}
}
