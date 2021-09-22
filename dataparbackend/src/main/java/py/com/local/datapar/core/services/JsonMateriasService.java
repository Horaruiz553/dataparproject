package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.JsonMaterias;

public interface JsonMateriasService {

	public List<JsonMaterias> findAll();	

	public Optional<JsonMaterias> findById(Long id);
	
	public JsonMaterias save(JsonMaterias cliente);

}
