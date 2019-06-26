package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Cliente;

@Repository
public interface ClienteDAO
		extends CrudRepository<Cliente, Integer>, ClienteDAOCustom, PagingAndSortingRepository<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c JOIN c.representantes r WHERE r.id=:idRepresentacao AND c.excluido = 0 ORDER BY c.razaoSocial")
	public List<Cliente> buscaClientePorRepresentacao(@Param("idRepresentacao") Long idRepresentacao);

	@Override
	@Query("SELECT c FROM Cliente c WHERE c.excluido = 0")
	public List<Cliente> findAll();

	public Cliente findByCpfCnpj(@Param("cpfCnpj") String cpfCnpj);

	@Query("SELECT COUNT(c) FROM Cliente c WHERE c.excluido = 0 AND c.pendenteRegistro = true")
	public Long getNumeroClientesPendentes();
}
