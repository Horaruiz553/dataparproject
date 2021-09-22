package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.JsonMateriasDao;
import py.com.local.datapar.core.model.JsonMaterias;

@Repository("JsonMateriasDao")
public class JsonMateriasDaoImpl extends AbstractDao<JsonMaterias> implements JsonMateriasDao {

	@Override
	public List<JsonMaterias> listarTodos() {
		List<JsonMaterias> mo = getEntityManager().createNamedQuery("JsonMaterias.findAll").getResultList();
		return mo;
	}

	@Override
	public Optional<JsonMaterias> getById(long id) {
		Optional<JsonMaterias> mo = super.getById(id);
		return mo;
	}
	
	@Override
	public void actualizar(JsonMaterias usu) {
		super.actualizar(usu);
	}

}
