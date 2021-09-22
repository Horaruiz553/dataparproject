package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Matriculaciones;

public interface MatriculacionDao {

	List<Matriculaciones> listarTodos(String cod);

	Optional<Matriculaciones> getById(long id);

	List<Matriculaciones> getByUsuario(Long idUsuario);
	
	List<Matriculaciones> getByUsuarioAndEstado(Long idUsuario, boolean valor);

	void insertar(Matriculaciones usu);

	void actualizar(Matriculaciones usu);

	void borrarPorId(Long id);

	Matriculaciones getByUsuarioAndMateria(long idusuario, long idmateria, boolean estado);

	List<Matriculaciones> listarPendientes(String codigo);
	
	List<Matriculaciones> listrarExistencia(long idUsuario, long idMateria, String periodo);

}
