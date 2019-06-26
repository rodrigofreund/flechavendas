package br.com.rodrigodaweb.flechavendas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import br.com.rodrigodaweb.flechavendas.dto.UsuarioSearchDto;
import br.com.rodrigodaweb.flechavendas.model.Perfil;
import br.com.rodrigodaweb.flechavendas.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAOCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Usuario> find(UsuarioSearchDto search) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root);
		criteria.distinct(true);

		List<Predicate> listP = new ArrayList<>();
		if (search.getId() != null) {
			listP.add(builder.equal(root.get("id"), search.getId()));
		}
		if (!Strings.isNullOrEmpty(search.getLogin())) {
			listP.add(builder.like(root.get("login"), "%" + search.getLogin() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getNome())) {
			listP.add(builder.like(root.get("nome"), "%" + search.getNome() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getRegiao())) {
			listP.add(builder.like(root.get("regiao"), "%" + search.getRegiao() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getCpf())) {
			listP.add(builder.like(root.get("cpf"), search.getCpf() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getEmail())) {
			listP.add(builder.like(root.get("email"), "%" + search.getEmail() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getEndereco())) {
			listP.add(builder.like(root.get("endereco"), "%" + search.getEndereco() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getRg())) {
			listP.add(builder.like(root.get("rg"), search.getRg() + "%"));
		}
		if (search.getIdPerfil() != null) {
			Join<Usuario, Perfil> repJoin = root.join("perfil", JoinType.INNER);
			listP.add(repJoin.in(search.getIdPerfil()));
		}

		listP.add(builder.equal(root.get("excluido"), false));

		criteria.where(listP.toArray(new Predicate[] {}));

		criteria.orderBy(builder.asc(root.get("id")));

		if (search.getPageSize() != null) {
			return em.createQuery(criteria).setFirstResult((search.getNewPage() - 1) * search.getPageSize())
					.setMaxResults(search.getPageSize()).getResultList();
		} else {
			return em.createQuery(criteria).getResultList();
		}
	}

	@Override
	public Integer countResult(UsuarioSearchDto search) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(root);
		criteria.distinct(true);

		List<Predicate> listP = new ArrayList<>();
		if (search.getId() != null) {
			listP.add(builder.equal(root.get("id"), search.getId()));
		}
		if (!Strings.isNullOrEmpty(search.getLogin())) {
			listP.add(builder.like(root.get("login"), "%" + search.getLogin() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getNome())) {
			listP.add(builder.like(root.get("nome"), "%" + search.getNome() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getRegiao())) {
			listP.add(builder.like(root.get("regiao"), "%" + search.getRegiao() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getCpf())) {
			listP.add(builder.like(root.get("cpf"), search.getCpf() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getEmail())) {
			listP.add(builder.like(root.get("email"), "%" + search.getEmail() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getEndereco())) {
			listP.add(builder.like(root.get("endereco"), "%" + search.getEndereco() + "%"));
		}
		if (!Strings.isNullOrEmpty(search.getRg())) {
			listP.add(builder.like(root.get("rg"), search.getRg() + "%"));
		}
		if (search.getIdPerfil() != null) {
			Join<Usuario, Perfil> repJoin = root.join("perfil", JoinType.INNER);
			listP.add(repJoin.in(search.getIdPerfil()));
		}

		listP.add(builder.equal(root.get("excluido"), false));

		criteria.where(listP.toArray(new Predicate[] {}));

		criteria.orderBy(builder.asc(root.get("id")));

		return em.createQuery(criteria).getResultList().size();
	}

}
