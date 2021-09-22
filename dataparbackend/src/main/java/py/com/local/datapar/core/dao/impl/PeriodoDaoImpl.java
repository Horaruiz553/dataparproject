package py.com.local.datapar.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import py.com.local.datapar.core.dao.AbstractDao;
import py.com.local.datapar.core.dao.PeriodoDao;
import py.com.local.datapar.core.model.Periodo;

@Repository("PeriodoDao")
public class PeriodoDaoImpl extends AbstractDao<Periodo> implements PeriodoDao {

	@Override
	public List<Periodo> listarTodos() {
		List<Periodo> mo = getEntityManager()
				.createQuery("SELECT m FROM Periodo m ORDER BY m.id DESC")
				.setMaxResults(3)
				.getResultList();
		return mo;
	}
	
	@Override
	public Periodo findUltimo() {
		Periodo mo = (Periodo) getEntityManager()
				.createQuery("SELECT m FROM Periodo m ORDER BY m.id DESC")
				.setMaxResults(1)
				.getSingleResult();
		return mo;
	}

	@Override
	public Optional<Periodo> getById(long id) {
		Optional<Periodo> mo = super.getById(id);
		return mo;
	}

	@Override
	public List<Periodo> getByUsuario(Long idUsuario) {
		List<Periodo> mo = getEntityManager()
				.createQuery("SELECT m FROM Periodo m WHERE m.idusuario=:idusu")
				.setParameter("idusu", idUsuario).getResultList();
		return mo;
	}

	@Override
	public List<Periodo> getByUsuarioAndEstado(Long idUsuario, boolean valor) {
		List<Periodo> mo = getEntityManager()
				.createQuery("SELECT m FROM Periodo m WHERE m.idusuario=:idUsu AND m.estado=:status")
				.setParameter("idUsu", idUsuario).setParameter("status", valor).getResultList();
		return mo;
	}

	@Override
	public void insertar(Periodo usu) {
		super.persistir(usu);
	}

	@Override
	public void actualizar(Periodo usu) {
		super.actualizar(usu);
	}

	@Override
	public void borrarPorId(Long id) {
		super.eliminar(super.getById(id).get());
	}

	@Override
	public Periodo getByUsuarioAndMateria(long idusuario, long idmateria, boolean estado) {
		String sql = "SELECT * FROM Periodo WHERE idusuario=" + idusuario + " AND idmateria=" + idmateria
				+ " AND estado='" + estado + "'";
		List<Periodo> list = getEntityManager().createNativeQuery(sql, Periodo.class).getResultList();
		return list.get(0);
	}

	@Override
	public List<Periodo> listarPendientes(String codigo) {
		String sql = "SELECT * FROM Periodo WHERE periodo='" + codigo + "' AND procesado='false'";
		List<Periodo> list = getEntityManager().createNativeQuery(sql, Periodo.class).getResultList();
		return list;
	}

}
