package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Periodo;

public interface PeriodoService {

	public List<Periodo> findAll();	
	
	public Periodo findUltimo();	

	public Optional<Periodo> findById(Long id);

	public Periodo save(Periodo cliente);

	public void delete(Long id);

	public List<Periodo> recuperarPorIdUsuario(Long id);
	
	public List<Periodo> recuperarPorIdUsuarioEstado(Long id, boolean estado);

	public boolean isExisteFrecuencia(Periodo frecu);

	

}
