package br.com.caelum.estoque.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.estoque.Produto;

@Repository
@Primary
@Transactional
public class ProdutoHibernateDao implements ProdutoDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ProdutoHibernateDao(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void salvar(Produto produto) {
		sessionFactory.getCurrentSession().save(produto);

	}

	@Override
	public void alterar(Produto produto) {
		sessionFactory.getCurrentSession().update(produto);
	}

	@Override
	public List<Produto> listar() {
		List<Produto> produtos = sessionFactory.getCurrentSession()
				.createQuery("from Produto").list();
		return produtos;
	}

	@Override
	public Produto buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return (Produto) sessionFactory.getCurrentSession().get(Produto.class,
				id);
	}

	@Override
	public void remover(Long id) {
		Produto produto = new Produto();
		produto.setId(id);
		sessionFactory.getCurrentSession().delete(produto);

	}

	@Override
	public Integer estoqueAtual(Produto produto) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select quantidade from Produto where id = :pid");
		query.setParameter("pid", produto.getId());
		return (Integer) query.uniqueResult();
	}

}
