package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.EstadoDAO;
import br.com.rodrigodaweb.flechavendas.dto.EstadoDto;
import br.com.rodrigodaweb.flechavendas.mapper.EstadoMapper;

@Service
@Transactional
public class EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	public List<EstadoDto> buscaEstados() {
		return Lists.newArrayList(estadoDAO.findAll()).stream().map(EstadoMapper::toEstadoDto)
				.collect(Collectors.toList());
	}
}
