package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.JsonMaterias;

public interface JsonMateriasDao {

	List<JsonMaterias> listarTodos();

	Optional<JsonMaterias> getById(long id);
	
	void actualizar(JsonMaterias json);

}
