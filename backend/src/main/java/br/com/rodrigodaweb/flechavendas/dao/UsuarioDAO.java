package br.com.rodrigodaweb.flechavendas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rodrigodaweb.flechavendas.model.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>, UsuarioDAOCustom, PagingAndSortingRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u WHERE u.login=:login AND u.senha=:senha AND u.excluido = false")
	public Usuario findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);

	@Query("SELECT u FROM Usuario u INNER JOIN u.representacoes r WHERE r.industria.id = :idIndustria AND u.excluido = false")
	public List<Usuario> findUsuariosPorIndustria(@Param("idIndustria") Integer idIndustria);

	@Query("SELECT u FROM Usuario u WHERE u.excluido = false")
	@Override
	public Iterable<Usuario> findAll();
	
	@Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.excluido = false")
	@Override
	public Usuario findOne(@Param("id")Integer id);
	
	@Query("SELECT count(u) FROM Usuario u WHERE u.excluido = false")
	@Override
	long count();

	public Usuario findByLogin(String login);

}
