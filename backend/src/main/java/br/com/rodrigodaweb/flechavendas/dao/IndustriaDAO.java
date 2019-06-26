package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.rodrigodaweb.flechavendas.model.Industria;

@Transactional
public interface IndustriaDAO extends CrudRepository<Industria, Integer>{
	@Query("SELECT i FROM Industria i INNER JOIN i.representacoes r WHERE r.usuario.id = :idUsuario and r.ativo is true ORDER BY i.nome")
	public List<Industria> getIndustriasUsuario(@Param("idUsuario")Integer idUsuario);
	
	@Query("SELECT i FROM Industria i WHERE i.nome LIKE :nomeIndustria")
	public Industria getIndustriaByNome(@Param("nomeIndustria")String nomeIndustria);
	
}
