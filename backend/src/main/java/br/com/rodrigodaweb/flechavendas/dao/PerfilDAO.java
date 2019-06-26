package br.com.rodrigodaweb.flechavendas.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Perfil;

@Repository
public interface PerfilDAO extends CrudRepository<Perfil, Integer>{
}
