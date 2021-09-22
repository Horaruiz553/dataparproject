package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Matriculaciones;

public interface MatriculacionService {

	public List<Matriculaciones> findAll(String codigo);	

	public Optional<Matriculaciones> findById(Long id);

	public Matriculaciones save(Matriculaciones cliente);

	public void delete(Long id);

	public List<Matriculaciones> recuperarPorIdUsuario(Long id);
	
	public List<Matriculaciones> recuperarPorIdUsuarioEstado(Long id, boolean estado);

	public boolean isExisteFrecuencia(Matriculaciones frecu);

	public void quitarMatriculacion(long id, long estado);

	public List<Matriculaciones> listarPendientes(String codigo);
	
	public Boolean listrarExistencia(long idUsuario, long idMateria, String periodo);

}
