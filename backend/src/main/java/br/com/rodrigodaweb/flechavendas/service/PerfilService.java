package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dao.PerfilDAO;
import br.com.rodrigodaweb.flechavendas.dto.PerfilDto;
import br.com.rodrigodaweb.flechavendas.mapper.PerfilMapper;

@Service
public class PerfilService {
	@Autowired
	private PerfilDAO perfilDao;
	
	public List<PerfilDto> getListaPerfil() {
		return PerfilMapper.toPerfilDtoList(Lists.newArrayList(perfilDao.findAll()));
	}
}
