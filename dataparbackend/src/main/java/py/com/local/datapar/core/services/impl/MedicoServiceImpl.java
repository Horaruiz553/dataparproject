package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.MedicoDao;
import py.com.local.datapar.core.model.Medico;
import py.com.local.datapar.core.services.MedicoService;

@Service("MedicoService")
@Transactional
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	private MedicoDao MedicoDao;

	@Override
	public List<Medico> findAll() {
		return MedicoDao.listarTodos();
	}

	@Override
	public Optional<Medico> findById(Long id) {
		return MedicoDao.getById(id);
	}

	@Override
	public Medico save(Medico cliente) {
		if (cliente.getId() == null) {
			MedicoDao.insertar(cliente);
			return cliente;
		} else {
			MedicoDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		MedicoDao.borrarPorId(id);
	}

	@Override
	public List<Medico> getByCi(String ci) {
		return MedicoDao.getByCi(ci);
	}

	@Override
	public boolean isExiste(Medico medico) {
		try {
			return findById(medico.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}
	}

}
