package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Paciente;
import py.com.local.datapar.core.model.Usuario;

public interface PacienteDao {

	List<Paciente> listarTodos();

	Optional<Paciente> getById(long id);
	
	List<Paciente> getByCi(String cedula);
	
	void insertar(Paciente usu);

	void actualizar(Paciente usu);

	void borrarPorId(Long id);

}
