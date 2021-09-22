package py.com.local.datapar.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT m FROM Usuario m"), })
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "idpaciente")
	private Long idpaciente;

	@Column(name = "idmedico")
	private Long idmedico;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdpaciente() {
		return (idpaciente == null ? 0 : idpaciente);
	}

	public void setIdpaciente(Long idpaciente) {
		this.idpaciente = (idpaciente == null ? 0 : idpaciente);
	}

	public Long getIdmedico() {
		return (idmedico == null ? 0 : idmedico);
	}

	public void setIdmedico(Long idmedico) {
		this.idmedico = (idmedico == null ? 0 : idmedico);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
