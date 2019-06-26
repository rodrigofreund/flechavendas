package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;

@Repository
public interface IndustriaPrazoDAO extends CrudRepository<IndustriaPrazo, Integer> {
	@Query("select ip from IndustriaPrazo ip where ip.industria.id = :idIndustria and  ip.excluido = 0")
	public List<IndustriaPrazo> buscaPorIndustria(@Param("idIndustria") Integer idindustria);

	@Query("select ip from IndustriaPrazo ip where ip.id = :idIndustriaPrazo and ip.excluido = 0")
	public IndustriaPrazo buscaPorId(@Param("idIndustriaPrazo") Integer idIndustriaPrazo);
}
