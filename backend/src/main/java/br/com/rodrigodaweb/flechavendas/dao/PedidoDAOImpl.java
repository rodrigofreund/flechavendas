package br.com.rodrigodaweb.flechavendas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.rodrigodaweb.flechavendas.dto.FiltroPedidoDto;
import br.com.rodrigodaweb.flechavendas.model.Pedido;

public class PedidoDAOImpl implements PedidoDAOCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Pedido> find(FiltroPedidoDto search) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		criteria.select(root);

		List<Predicate> listP = new ArrayList<>();
		if (search.getIdIndustria() != null) {
			listP.add(builder.equal(root.get("industria"), search.getIdIndustria()));
		}

		if (search.getIdUsuario() != null) {
			listP.add(builder.equal(root.get("usuario"), search.getIdUsuario()));
		}

		if (search.getIdStatus() != null) {
			listP.add(builder.equal(root.get("status"), search.getIdStatus()));
		}

		if (search.getIdPedido() != null) {
			listP.add(builder.equal(root.get("id"), search.getIdPedido()));
		}

		if (search.getDtInicio() != null) {
			listP.add(builder.greaterThanOrEqualTo(root.get("dataPedido"), search.getDtInicio()));
		}

		if (search.getDtFim() != null) {
			listP.add(builder.lessThanOrEqualTo(root.get("dataPedido"), search.getDtFim()));
		}

		if (search.getIdCliente() != null) {
			listP.add(builder.equal(root.get("cliente"), search.getIdCliente()));
		}

		criteria.where(listP.toArray(new Predicate[] {}));

		criteria.orderBy(builder.desc(root.get("id")));

		return em.createQuery(criteria)
					.setFirstResult((search.getNewPage() - 1) * search.getPageSize())
					.setMaxResults(search.getPageSize())
					.getResultList();
	}

	@Override
	public Integer countResult(FiltroPedidoDto search) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);

		criteria.select(root);

		List<Predicate> listP = new ArrayList<>();
		if (search.getIdIndustria() != null) {
			listP.add(builder.equal(root.get("industria"), search.getIdIndustria()));
		}

		if (search.getIdUsuario() != null) {
			listP.add(builder.equal(root.get("usuario"), search.getIdUsuario()));
		}

		if (search.getIdStatus() != null) {
			listP.add(builder.equal(root.get("status"), search.getIdStatus()));
		}

		if (search.getIdPedido() != null) {
			listP.add(builder.equal(root.get("id"), search.getIdPedido()));
		}

		if (search.getDtInicio() != null) {
			listP.add(builder.greaterThanOrEqualTo(root.get("dataPedido"), search.getDtInicio()));
		}

		if (search.getDtFim() != null) {
			listP.add(builder.lessThanOrEqualTo(root.get("dataPedido"), search.getDtFim()));
		}

		if (search.getIdCliente() != null) {
			listP.add(builder.equal(root.get("cliente"), search.getIdCliente()));
		}

		criteria.where(listP.toArray(new Predicate[] {}));

		return em.createQuery(criteria).getResultList().size();
	}

}
