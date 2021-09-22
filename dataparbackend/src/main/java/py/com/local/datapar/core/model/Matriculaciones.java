package py.com.local.datapar.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "matriculaciones")
@NamedQueries({ @NamedQuery(name = "Matriculaciones.findAll", query = "SELECT m FROM Matriculaciones m"), })
public class Matriculaciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "idusuario")
	private Long idusuario;

	@Column(name = "idmateria")
	private Long idmateria;

	@Column(name = "estado")
	private Boolean estado;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "materia")
	private String materia;

	@Column(name = "facultad")
	private String facultad;
	
	@Column(name = "periodo")
	private String periodo;
	
	@Column(name = "procesado")
	private Boolean procesado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public Long getIdmateria() {
		return idmateria;
	}

	public void setIdmateria(Long idmateria) {
		this.idmateria = idmateria;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Boolean getProcesado() {
		return procesado;
	}

	public void setProcesado(Boolean procesado) {
		this.procesado = procesado;
	}

}
