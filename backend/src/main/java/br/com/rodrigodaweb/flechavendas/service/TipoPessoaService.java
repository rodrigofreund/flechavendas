package br.com.rodrigodaweb.flechavendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.domain.TIPO_PESSOA;
import br.com.rodrigodaweb.flechavendas.dto.TipoPessoaDto;

@Service
@Transactional
public class TipoPessoaService {
	public List<TipoPessoaDto> getListaTipoPessoa() {
		List<TipoPessoaDto> listaTipoPessoa = new ArrayList<>();
		for(TIPO_PESSOA ptPessoa :TIPO_PESSOA.values()) {
			listaTipoPessoa.add(new TipoPessoaDto(ptPessoa.getId(), ptPessoa.getDescricao()));
		}
		return listaTipoPessoa;
	}
}
