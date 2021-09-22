package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Paciente;

public interface PacienteService {

	public List<Paciente> findAll();

	public Optional<Paciente> findById(Long id);

	public List<Paciente> getByCi(String ci);

	public Paciente save(Paciente cliente);

	public void delete(Long id);

	public boolean isExiste(Paciente paciente);

}
