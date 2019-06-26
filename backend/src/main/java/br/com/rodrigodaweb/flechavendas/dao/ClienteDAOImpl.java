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

import br.com.rodrigodaweb.flechavendas.dto.ClienteDto;
import br.com.rodrigodaweb.flechavendas.model.Cliente;
import br.com.rodrigodaweb.flechavendas.model.Representacao;

public class ClienteDAOImpl implements ClienteDAOCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cliente> find(ClienteDto search) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);

		criteria.select(root);
		criteria.distinct(true);

		List<Predicate> listP = new ArrayList<>();
		if (search.getRazaoSocial() != null && !search.getRazaoSocial().isEmpty()) {
			listP.add(builder.like(root.get("razaoSocial"), "%" + search.getRazaoSocial() + "%"));
		}

		if (search.getNomeFantasia() != null && !search.getNomeFantasia().isEmpty()) {
			listP.add(builder.like(root.get("nomeFantasia"), search.getNomeFantasia() + "%"));
		}

		if (search.getCpfCnpj() != null && !search.getCpfCnpj().isEmpty()) {
			listP.add(builder.like(root.get("cpfCnpj"), search.getCpfCnpj() + "%"));
		}

		if(search.getVendedor() != null && search.getVendedor() == Boolean.TRUE) {
			listP.add(builder.equal(root.get("excluido"), 0));
		}
		
		if(search.isPendenteRegistro()) {
			listP.add(builder.equal(root.get("pendenteRegistro"), true));
		}

		if (search.getIdsRepresentacao() != null && search.getIdsRepresentacao().size() > 0) {
			Join<Cliente, Representacao> repJoin = root.join("representantes", JoinType.INNER);
			listP.add(repJoin.in(search.getIdsRepresentacao()));
		}

		criteria.where(listP.toArray(new Predicate[] {}));

		criteria.orderBy(builder.asc(root.get("razaoSocial")));
		
		if(search.getPageSize() != null) {
			return em.createQuery(criteria).setFirstResult((search.getNewPage() - 1) * search.getPageSize())
				.setMaxResults(search.getPageSize()).getResultList();
		} else {
			return em.createQuery(criteria).getResultList();
		}
	}

}
