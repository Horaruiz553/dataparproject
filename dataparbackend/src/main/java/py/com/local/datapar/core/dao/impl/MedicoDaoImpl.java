package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.MedicoDao;
import py.com.local.datapar.core.model.Medico;

@Repository("MedicoDao")
public class MedicoDaoImpl extends AbstractDao<Medico> implements MedicoDao {

	@Override
	public List<Medico> listarTodos() {
		List<Medico> mo = getEntityManager()
				.createQuery("SELECT m FROM Medico m ORDER BY m.id DESC")
				.setMaxResults(3)
				.getResultList();
		return mo;
	}

	@Override
	public Optional<Medico> getById(long id) {
		Optional<Medico> mo = super.getById(id);
		return mo;
	}

	@Override
	public void actualizar(Medico usu) {
		super.actualizar(usu);
	}

	@Override
	public List<Medico> getByCi(String cedula) {
		
		List<Medico> mo = getEntityManager()
				.createQuery("SELECT m FROM Medico m WHERE m.cedula=:idusu")
				.setParameter("idusu", cedula).getResultList();
		return mo;
	}

	@Override
	public void insertar(Medico usu) {
		super.persistir(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

}
