package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Representacao;

@Repository
public interface RepresentacaoDAO extends CrudRepository<Representacao, Long>{
	
	@Query("Select r from Representacao r inner join r.usuario u where u.id = :idUsuario order by u.nome")
	public List<Representacao> getRepresentacoesUsuario(@Param("idUsuario")Integer idUsuario);

	@Query("Select r from Representacao r inner join r.industria i where i.id = :idIndustria")
	public List<Representacao> getRepresentacoesIndustria(@Param("idIndustria")Integer idIndustria);

}
