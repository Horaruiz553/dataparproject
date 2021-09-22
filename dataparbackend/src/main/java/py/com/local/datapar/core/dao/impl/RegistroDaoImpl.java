package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.RegistroDao;
import py.com.local.datapar.core.model.Registro;

@Repository("RegistroDao")
public class RegistroDaoImpl extends AbstractDao<Registro> implements RegistroDao {

	@Override
	public List<Registro> listarTodos() {
		List<Registro> mo = getEntityManager().createQuery("SELECT m FROM Registro m ORDER BY m.id DESC").setMaxResults(3)
				.getResultList();
		return mo;
	}

	@Override
	public Optional<Registro> getById(long id) {
		Optional<Registro> mo = super.getById(id);
		return mo;
	}

	@Override
	public void actualizar(Registro usu) {
		super.actualizar(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

	@Override
	public void insertar(Registro usu) {
		super.persistir(usu);
	}
	
	@Override
	public Registro listarPorIdMedico(long id) {
		List<Registro> mo = getEntityManager()
				.createQuery("SELECT m FROM Registro m WHERE m.idmedico=:idmed")
				.setParameter("idmed", id).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0) == null ? null : mo.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Registro listarPorIdPaciente(long id) {
		List<Registro> mo = getEntityManager()
				.createQuery("SELECT m FROM Registro m WHERE m.idpaciente=:idpac")
				.setParameter("idpac", id).setMaxResults(1)
				.getResultList();
		try {
			return mo.get(0) == null ? null : mo.get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
