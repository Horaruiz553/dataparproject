package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Medico;

public interface MedicoDao {

	List<Medico> listarTodos();

	Optional<Medico> getById(long id);
	
	List<Medico> getByCi(String cedula);
	
	void insertar(Medico usu);

	void actualizar(Medico usu);

	void borrarPorId(Long id);

}
