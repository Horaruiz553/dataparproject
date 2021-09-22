package py.com.local.datapar.core.dao;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Periodo;

public interface PeriodoDao {

	List<Periodo> listarTodos();
	
	Periodo findUltimo();

	Optional<Periodo> getById(long id);

	List<Periodo> getByUsuario(Long idUsuario);
	
	List<Periodo> getByUsuarioAndEstado(Long idUsuario, boolean valor);

	void insertar(Periodo usu);

	void actualizar(Periodo usu);

	void borrarPorId(Long id);

	Periodo getByUsuarioAndMateria(long idusuario, long idmateria, boolean estado);

	List<Periodo> listarPendientes(String codigo);

}
