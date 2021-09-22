package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Medico;

public interface MedicoService {

	public List<Medico> findAll();

	public Optional<Medico> findById(Long id);

	public List<Medico> getByCi(String ci);

	public Medico save(Medico cliente);

	public void delete(Long id);

	public boolean isExiste(Medico medico);

}
