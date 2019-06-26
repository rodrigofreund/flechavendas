package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.IndustriaClientePrazoDAO;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaClientePrazoDto;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaClientePrazoMapper;
import br.com.rodrigodaweb.flechavendas.model.IndustriaClientePrazo;

@Service
@Transactional
public class IndustriaClientePrazoService {

	@Autowired
	private IndustriaClientePrazoDAO dao;

	public void salvaListaIndustriaClientePrazo(List<IndustriaClientePrazoDto> listaIndustriaClientePrazoDto) {
		List<IndustriaClientePrazo> listaIndustriaClientePrazo = listaIndustriaClientePrazoDto.stream()
				.map(IndustriaClientePrazoMapper::toIndustriaClientePrazo).collect(Collectors.toList());
		
		dao.save(listaIndustriaClientePrazo);
	}
	
	public void salvaIndustriaClientePrazo(IndustriaClientePrazoDto dto) {
		dao.save(IndustriaClientePrazoMapper.toIndustriaClientePrazo(dto));
	}
	
	public void removeIndustriaClientePrazo(Integer id) {
		dao.delete(id);
	}
	
	public List<IndustriaClientePrazoDto> getIndustriaClientePrazoPorIdIndustriaCliente(Integer idIndustriaCliente) {
		return dao.findByIndustriaClienteId(idIndustriaCliente).stream()
				.map(IndustriaClientePrazoMapper::toIndustriaClientePrazoDto).collect(Collectors.toList());
	}

	public List<IndustriaClientePrazoDto> getIndustriaClientePrazo(Integer idIndustria, Integer idCliente) {
		return dao.findByIndustriaClienteClienteIdAndIndustriaClienteIndustriaId(idCliente, idIndustria).stream()
				.map(IndustriaClientePrazoMapper::toIndustriaClientePrazoDto).collect(Collectors.toList());
	}

	public List<IndustriaClientePrazoDto> getIndustriaClientePrazoPorIdIndustria(Integer idIndustria) {
		return dao.findByIndustriaClienteIndustriaId(idIndustria).stream()
				.map(IndustriaClientePrazoMapper::toIndustriaClientePrazoDto).collect(Collectors.toList());
	}
}
