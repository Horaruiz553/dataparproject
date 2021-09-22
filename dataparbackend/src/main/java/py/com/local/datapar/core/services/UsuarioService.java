package py.com.local.datapar.core.services;

import java.util.List;
import java.util.Optional;

import py.com.local.datapar.core.model.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();

	public Optional<Usuario> findById(Long id);
	
	public Usuario save(Usuario cliente);

	public void delete(Long id);

	public boolean existeUsuarioPass(String username, String pwd);

	public boolean isExiste(Usuario usuario);
	
	public Usuario existeUsuPass(String username, String pwd);

	public Usuario listarPorIdMedico(long id);
	
	public Usuario listarPorIdPaciente(long id);

}
