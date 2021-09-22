package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Periodo;
import py.com.local.datapar.core.model.Usuario;

public interface UsuarioDao {

	List<Usuario> listarTodos();

	Optional<Usuario> getById(long id);
	
	void insertar(Usuario usu);

	void actualizar(Usuario usu);

	void borrarPorId(Long id);

	boolean existeUsuarioPass(String username, String pwd);

	Usuario existeUsuPass(String username, String pwd);

	Usuario listarPorIdMedico(long id);

	Usuario listarPorIdPaciente(long id);

}
