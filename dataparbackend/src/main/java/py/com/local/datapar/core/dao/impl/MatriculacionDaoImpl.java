package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.MatriculacionDao;
import py.com.local.datapar.core.model.Matriculaciones;

@Repository("MatriculacionDao")
public class MatriculacionDaoImpl extends AbstractDao<Matriculaciones> implements MatriculacionDao {

	@Override
	public List<Matriculaciones> listarTodos(String cod) {
		List<Matriculaciones> mo = getEntityManager()
				.createQuery("SELECT m FROM Matriculaciones m WHERE m.periodo=:perd").setParameter("perd", cod)
				.getResultList();
		return mo;
	}

	@Override
	public Optional<Matriculaciones> getById(long id) {
		Optional<Matriculaciones> mo = super.getById(id);
		return mo;
	}

	@Override
	public List<Matriculaciones> getByUsuario(Long idUsuario) {
		List<Matriculaciones> mo = getEntityManager()
				.createQuery("SELECT m FROM Matriculaciones m WHERE m.idusuario=:idusu")
				.setParameter("idusu", idUsuario).getResultList();
		return mo;
	}

	@Override
	public List<Matriculaciones> getByUsuarioAndEstado(Long idUsuario, boolean valor) {
		System.out.println(valor+" ======>>> "+idUsuario);
		List<Matriculaciones> mo = getEntityManager()
				.createQuery("SELECT m FROM Matriculaciones m WHERE m.idusuario=:idUsu AND m.estado=:status")
				.setParameter("idUsu", idUsuario).setParameter("status", valor).getResultList();
		return mo;
	}

	@Override
	public void insertar(Matriculaciones usu) {
		super.persistir(usu);
	}

	@Override
	public void actualizar(Matriculaciones usu) {
		super.actualizar(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

	@Override
	public Matriculaciones getByUsuarioAndMateria(long idusuario, long idmateria, boolean estado) {
		String sql = "SELECT * FROM matriculaciones WHERE idusuario=" + idusuario + " AND idmateria=" + idmateria
				+ " AND estado='" + estado + "'";
		List<Matriculaciones> list = getEntityManager().createNativeQuery(sql, Matriculaciones.class).getResultList();
		return list.get(0);
	}

	@Override
	public List<Matriculaciones> listarPendientes(String codigo) {
		String sql = "SELECT * FROM matriculaciones WHERE periodo='" + codigo + "' AND procesado='false'";
		List<Matriculaciones> list = getEntityManager().createNativeQuery(sql, Matriculaciones.class).getResultList();
		return list;
	}

	@Override
	public List<Matriculaciones> listrarExistencia(long idUsuario, long idMateria, String periodo) {
		// TODO Auto-generated method stub
		List<Matriculaciones> mo = getEntityManager()
				.createQuery("SELECT m FROM Matriculaciones m WHERE m.idusuario=:idUsu AND m.idmateria=:idmate "
						+ "AND m.periodo=:period")
				.setParameter("idUsu", idUsuario)
				.setParameter("idmate", idMateria)
				.setParameter("period", periodo)
				.getResultList();
		return mo;
	}

}
