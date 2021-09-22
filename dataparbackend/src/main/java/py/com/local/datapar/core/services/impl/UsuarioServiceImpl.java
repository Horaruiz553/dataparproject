package py.com.local.datapar.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.local.datapar.core.dao.UsuarioDao;
import py.com.local.datapar.core.model.Usuario;
import py.com.local.datapar.core.services.UsuarioService;

@Service("UsuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao UsuarioDao;

	@Override
	public List<Usuario> findAll() {
		return UsuarioDao.listarTodos();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return UsuarioDao.getById(id);
	}

	@Override
	public Usuario save(Usuario cliente) {
		if (cliente.getId() == null) {
			UsuarioDao.insertar(cliente);
			return cliente;
		} else {
			UsuarioDao.actualizar(cliente);
			return cliente;
		}
	}

	@Override
	public void delete(Long id) {
		UsuarioDao.borrarPorId(id);
	}

	@Override
	public boolean existeUsuarioPass(String username, String pwd) {
		return UsuarioDao.existeUsuarioPass(username, pwd);
	}

	@Override
	public boolean isExiste(Usuario usuario) {
		try {
			return findById(usuario.getId()).isPresent();
		} catch (Exception e) {
			return false;
		} finally {
		}
	}

	@Override
	public Usuario existeUsuPass(String username, String pwd) {
		return UsuarioDao.existeUsuPass(username, pwd);
	}

	@Override
	public Usuario listarPorIdMedico(long id) {
		return UsuarioDao.listarPorIdMedico(id);
	}

	@Override
	public Usuario listarPorIdPaciente(long id) {
		return UsuarioDao.listarPorIdPaciente(id);
	}

}
