package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;

public interface ClienteDAOCustom {
	List<Cliente> find(ClienteDto search);
}
