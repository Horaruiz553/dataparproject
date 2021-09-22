package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Registro;

public interface RegistroDao {

	List<Registro> listarTodos();

	Optional<Registro> getById(long id);
	
	void insertar(Registro usu);

	void actualizar(Registro usu);

	void borrarPorId(Long id);

	Registro listarPorIdMedico(long id);

	Registro listarPorIdPaciente(long id);

}
