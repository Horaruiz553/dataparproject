package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.JsonMateriasDao;
import py.com.local.datapar.core.dao.MatriculacionDao;
import py.com.local.datapar.core.model.JsonMaterias;
import py.com.local.datapar.core.model.Matriculaciones;
import py.com.local.datapar.core.services.JsonMateriasService;
import py.com.local.datapar.core.services.MatriculacionService;

@Service("jsonMateriasService")
@Transactional
public class JsonMateriasServiceImpl implements JsonMateriasService {

	@Autowired
	private JsonMateriasDao matriculacionesDao;

	@Override
	public List<JsonMaterias> findAll() {
		return matriculacionesDao.listarTodos();
	}

	@Override
	public Optional<JsonMaterias> findById(Long id) {
		return matriculacionesDao.getById(id);
	}

	@Override
	public JsonMaterias save(JsonMaterias cliente) {
		matriculacionesDao.actualizar(cliente);
		return cliente;
	}

}
