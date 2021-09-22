package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.UsuarioDao;
import py.com.local.datapar.core.model.Usuario;

@Repository("UsuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao {

	@Override
	public List<Usuario> listarTodos() {
		List<Usuario> mo = getEntityManager().createQuery("SELECT m FROM Usuario m ORDER BY m.id DESC").setMaxResults(3)
				.getResultList();
		return mo;
	}

	@Override
	public Optional<Usuario> getById(long id) {
		Optional<Usuario> mo = super.getById(id);
		return mo;
	}

	@Override
	public void actualizar(Usuario usu) {
		super.actualizar(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

	@Override
	public void insertar(Usuario usu) {
		super.persistir(usu);
	}

	@Override
	public boolean existeUsuarioPass(String username, String pwd) {
		List<Usuario> mo = getEntityManager()
				.createQuery("SELECT m FROM Usuario m WHERE m.usuario=:user and m.password=:pwd")
				.setParameter("user", username).setParameter("pwd", pwd).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0).getId() != null ? true : false;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Usuario existeUsuPass(String username, String pwd) {
		List<Usuario> mo = getEntityManager()
				.createQuery("SELECT m FROM Usuario m WHERE m.usuario=:user and m.password=:pwd")
				.setParameter("user", username).setParameter("pwd", pwd).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0).getId() != null ? mo.get(0) : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario listarPorIdMedico(long id) {
		List<Usuario> mo = getEntityManager()
				.createQuery("SELECT m FROM Usuario m WHERE m.idmedico=:idmed")
				.setParameter("idmed", id).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0) == null ? null : mo.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario listarPorIdPaciente(long id) {
		List<Usuario> mo = getEntityManager()
				.createQuery("SELECT m FROM Usuario m WHERE m.idpaciente=:idpac")
				.setParameter("idpac", id).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0) == null ? null : mo.get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
