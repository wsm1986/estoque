package br.com.caelum.estoque.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.estoque.Usuario;

@Repository
public class UsuarioHibernateDao implements UsuarioDao {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public UsuarioHibernateDao(SessionFactory factory) {
		hibernateTemplate = new HibernateTemplate(factory);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		List usuario = hibernateTemplate.find("from Usuario where login = ?",
				username);
		if (!usuario.isEmpty()) {
			return (Usuario) usuario.get(0);
		}

		return null;
	}

}
