package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import br.com.rodrigodaweb.flechavendas.dto.UsuarioSearchDto;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public interface UsuarioDAOCustom {
	List<Usuario> find(UsuarioSearchDto search);
	Integer countResult(UsuarioSearchDto search);
}
