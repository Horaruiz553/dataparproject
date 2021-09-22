package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.MatriculacionDao;
import py.com.local.datapar.core.model.Matriculaciones;
import py.com.local.datapar.core.services.MatriculacionService;

@Service("matriculacionService")
@Transactional
public class MatriculacionServiceImpl implements MatriculacionService {

	@Autowired
	private MatriculacionDao matriculacionesDao;

	@Override
	public List<Matriculaciones> recuperarPorIdUsuario(Long id) {
		return matriculacionesDao.getByUsuario(id);
	}

	@Override
	public List<Matriculaciones> recuperarPorIdUsuarioEstado(Long id, boolean estado) {
		return matriculacionesDao.getByUsuarioAndEstado(id, estado);
	}

	@Override
	public List<Matriculaciones> findAll(String cod) {
		return matriculacionesDao.listarTodos(cod);
	}

	@Override
	public Optional<Matriculaciones> findById(Long id) {
		return matriculacionesDao.getById(id);
	}

	@Override
	public Matriculaciones save(Matriculaciones cliente) {
		if (cliente.getId() == null) {
			matriculacionesDao.insertar(cliente);
			return cliente;
		} else {
			matriculacionesDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		matriculacionesDao.borrarPorId(id);
	}

	@Override
	public boolean isExisteFrecuencia(Matriculaciones frecu) {
		// TODO Auto-generated method stub
		try {
			return findById(frecu.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}

	}
	
	@Override
	public void quitarMatriculacion(long idusuario, long idmateria) {
		Matriculaciones matri = matriculacionesDao.getByUsuarioAndMateria(idusuario, idmateria, false);
		matriculacionesDao.borrarPorId(matri.getId());
		
	}

	@Override
	public List<Matriculaciones> listarPendientes(String codigo) {
		return matriculacionesDao.listarPendientes(codigo);
	}

	@Override
	public Boolean listrarExistencia(long idUsuario, long idMateria, String periodo) {
		return matriculacionesDao.listrarExistencia(idUsuario, idMateria, periodo).size() > 0 ? true : false;	
		}

}
