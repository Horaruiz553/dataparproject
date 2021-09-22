package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.PacienteDao;
import py.com.local.datapar.core.model.Paciente;

@Repository("PacienteDao")
public class PacienteDaoImpl extends AbstractDao<Paciente> implements PacienteDao {

	@Override
	public List<Paciente> listarTodos() {
		List<Paciente> mo = getEntityManager()
				.createQuery("SELECT m FROM Paciente m ORDER BY m.id DESC")
				.setMaxResults(3)
				.getResultList();
		return mo;
	}

	@Override
	public Optional<Paciente> getById(long id) {
		Optional<Paciente> mo = super.getById(id);
		return mo;
	}

	@Override
	public void actualizar(Paciente usu) {
		super.actualizar(usu);
	}

	@Override
	public List<Paciente> getByCi(String cedula) {
		
		List<Paciente> mo = getEntityManager()
				.createQuery("SELECT m FROM Paciente m WHERE m.cedula=:idusu")
				.setParameter("idusu", cedula).getResultList();
		return mo;
	}

	@Override
	public void insertar(Paciente usu) {
		super.persistir(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

}
