package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.IndustriaClienteDAO;
import br.com.rodrigodaweb.flechavendas.dao.IndustriaDAO;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaDto;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaMapper;
import br.com.rodrigodaweb.flechavendas.model.Industria;

@Service
@Transactional
public class IndustriaService {

	@Autowired
	private IndustriaClienteDAO industriaClienteDAO;

	@Autowired
	private IndustriaDAO industriaDao;

	public boolean clienteCadastradoIndustria(Integer idIndustria, Integer idCliente) {
		return industriaClienteDAO.estaClienteCadastradoIndustria(idIndustria, idCliente) > 0;
	}

	public Industria buscaIndustriaByNome(String nome) {
		return industriaDao.getIndustriaByNome(nome);
	}

	public List<IndustriaDto> getIndustriasUsuario(Integer idUsuario) {
		return industriaDao.getIndustriasUsuario(idUsuario).stream()
				.map(IndustriaMapper::industriaIndustriaDto).collect(Collectors.toList());
	}

	public Industria findById(Integer id) {
		return industriaDao.findOne(id);
	}

	public List<IndustriaDto> findAll() {
		return Lists.newArrayList(industriaDao.findAll()).stream()
				.map(IndustriaMapper::industriaIndustriaDto).collect(Collectors.toList());
	}
}
