package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.PacienteDao;
import py.com.local.datapar.core.model.Paciente;
import py.com.local.datapar.core.services.PacienteService;

@Service("PacienteService")
@Transactional
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteDao PacienteDao;

	@Override
	public List<Paciente> findAll() {
		return PacienteDao.listarTodos();
	}

	@Override
	public Optional<Paciente> findById(Long id) {
		return PacienteDao.getById(id);
	}

	@Override
	public Paciente save(Paciente cliente) {
		if (cliente.getId() == null) {
			PacienteDao.insertar(cliente);
			return cliente;
		} else {
			PacienteDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		PacienteDao.borrarPorId(id);
	}

	@Override
	public List<Paciente> getByCi(String ci) {
		return PacienteDao.getByCi(ci);
	}

	@Override
	public boolean isExiste(Paciente paciente) {
		try {
			return findById(paciente.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}
	}

}
