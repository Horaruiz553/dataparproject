package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.RegistroDao;
import py.com.local.datapar.core.model.Registro;
import py.com.local.datapar.core.services.RegistroService;

@Service("RegistroService")
@Transactional
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private RegistroDao RegistroDao;

	@Override
	public List<Registro> findAll() {
		return RegistroDao.listarTodos();
	}

	@Override
	public Optional<Registro> findById(Long id) {
		return RegistroDao.getById(id);
	}

	@Override
	public Registro save(Registro cliente) {
		if (cliente.getId() == null) {
			RegistroDao.insertar(cliente);
			return cliente;
		} else {
			RegistroDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		RegistroDao.borrarPorId(id);
	}

	@Override
	public boolean isExiste(Registro Registro) {
		try {
			return findById(Registro.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}
	}

	@Override
	public Registro listarPorIdMedico(long id) {
		return RegistroDao.listarPorIdMedico(id);
	}

	@Override
	public Registro listarPorIdPaciente(long id) {
		return RegistroDao.listarPorIdPaciente(id);
	}

}
