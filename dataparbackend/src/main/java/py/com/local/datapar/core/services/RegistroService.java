package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Registro;

public interface RegistroService {

	public List<Registro> findAll();

	public Optional<Registro> findById(Long id);
	
	public Registro save(Registro cliente);

	public void delete(Long id);

	public boolean isExiste(Registro Registro);

	public Registro listarPorIdMedico(long id);
	
	public Registro listarPorIdPaciente(long id);

}
