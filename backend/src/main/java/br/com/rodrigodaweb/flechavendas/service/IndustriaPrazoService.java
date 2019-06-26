package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.IndustriaPrazoDAO;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoDto;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaPrazoMapper;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;

@Service
@Transactional
public class IndustriaPrazoService {

	@Autowired
	private IndustriaPrazoDAO industriaPrazoDao;

	public IndustriaPrazo salvar(IndustriaPrazo industriaPrazo) {
		return industriaPrazoDao.save(industriaPrazo);
	}

	public IndustriaPrazoDto buscarPorId(Integer idIndustriaPrazo) {
		return IndustriaPrazoMapper.toIndustriaPrazoDto(industriaPrazoDao.buscaPorId(idIndustriaPrazo));
	}

	public List<IndustriaPrazoDto> buscarPorIdIndustria(Integer idIndustria) {
		return industriaPrazoDao.buscaPorIndustria(idIndustria).stream().map(IndustriaPrazoMapper::toIndustriaPrazoDto)
				.collect(Collectors.toList());
	}

	public boolean excluir(Integer idIndustriaPrazo) {
		IndustriaPrazo industriaPrazo = industriaPrazoDao.findOne(idIndustriaPrazo);
		industriaPrazo.setExcluido(true);
		return industriaPrazoDao.save(industriaPrazo).isExcluido();
	}
}
