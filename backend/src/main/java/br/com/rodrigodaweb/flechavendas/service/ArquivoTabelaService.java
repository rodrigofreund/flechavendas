package br.com.rodrigodaweb.flechavendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.ArquivoTabelaDAO;
import br.com.rodrigodaweb.flechavendas.model.ArquivoTabela;

@Service
public class ArquivoTabelaService {
	@Autowired
	private ArquivoTabelaDAO arquivoTabelaDAO;

	public ArquivoTabela salvaArquivoTabela(ArquivoTabela entity) {
		return arquivoTabelaDAO.save(entity);
	}
	
	public ArquivoTabela buscaTabela(Long idTabela) {
		return arquivoTabelaDAO.findOne(idTabela);
	}

}
