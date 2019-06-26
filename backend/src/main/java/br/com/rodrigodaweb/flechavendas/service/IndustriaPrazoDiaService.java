package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.IndustriaPrazoDiaDAO;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazoDia;

@Service
@Transactional
public class IndustriaPrazoDiaService {

	@Autowired
	private IndustriaPrazoDiaDAO industriaPrazoDiaDao;

	public IndustriaPrazoDia salvar(IndustriaPrazoDia industriaPrazoDia) {
		return industriaPrazoDiaDao.save(industriaPrazoDia);
	}

	public List<IndustriaPrazoDia> buscaPorIdIndustriaPrazo(Integer idIndustriaPrazo) {
		return industriaPrazoDiaDao.buscaPorIdIndustriaPrazo(idIndustriaPrazo);
	}

	public void excluirPorIdIndustriaPrazo(Integer idIndustriaPrazo) {
		buscaPorIdIndustriaPrazo(idIndustriaPrazo).forEach(item -> {
			item.setExcluido(true);
			industriaPrazoDiaDao.save(item);
		});
	}
}
