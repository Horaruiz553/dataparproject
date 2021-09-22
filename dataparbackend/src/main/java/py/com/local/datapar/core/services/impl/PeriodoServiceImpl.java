package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.PeriodoDao;
import py.com.local.datapar.core.model.Periodo;
import py.com.local.datapar.core.services.PeriodoService;

@Service("PeriodoService")
@Transactional
public class PeriodoServiceImpl implements PeriodoService {

	@Autowired
	private PeriodoDao PeriodoDao;

	@Override
	public List<Periodo> recuperarPorIdUsuario(Long id) {
		return PeriodoDao.getByUsuario(id);
	}

	@Override
	public List<Periodo> recuperarPorIdUsuarioEstado(Long id, boolean estado) {
		return PeriodoDao.getByUsuarioAndEstado(id, estado);
	}

	@Override
	public List<Periodo> findAll() {
		return PeriodoDao.listarTodos();
	}
	
	@Override
	public Periodo findUltimo() {
		return PeriodoDao.findUltimo();
	}

	@Override
	public Optional<Periodo> findById(Long id) {
		return PeriodoDao.getById(id);
	}

	@Override
	public Periodo save(Periodo cliente) {
		if (cliente.getId() == null) {
			PeriodoDao.insertar(cliente);
			return cliente;
		} else {
			PeriodoDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		PeriodoDao.borrarPorId(id);
	}

	@Override
	public boolean isExisteFrecuencia(Periodo frecu) {
		// TODO Auto-generated method stub
		try {
			return findById(frecu.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}

	}
	
	

}
