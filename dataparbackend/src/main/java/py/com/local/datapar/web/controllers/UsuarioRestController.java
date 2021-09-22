package py.com.local.datapar.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import py.com.local.datapar.core.model.Usuario;
import py.com.local.datapar.core.services.UsuarioService;
import py.com.local.datapar.util.ErrorDTO;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioRestController {

	@Autowired
	private UsuarioService UsuarioService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public Usuario login(@RequestBody Usuario usu) {
		Usuario usuario = UsuarioService.existeUsuPass(usu.getUsuario(), usu.getPassword());
		if (usuario != null) {
			String token = getJWTToken(usu.getUsuario());
			usuario.setUsuario(usuario.getUsuario());
			usuario.setPassword(token);
			return usuario;
		} else {
			Usuario user = null;
			return user;
		}
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorId(@PathVariable("id") long id) {
		Optional<Usuario> pac = UsuarioService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(pac.get(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorIdMedico(@PathVariable("id") long id) {
		Usuario pac = UsuarioService.listarPorIdMedico(id);
		if (pac == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(pac, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/paciente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorIdPaciente(@PathVariable("id") long id) {
		Usuario pac = UsuarioService.listarPorIdPaciente(id);
		if (pac == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(pac, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTodos() {
		List<Usuario> usuario = UsuarioService.findAll();
		if (usuario.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
		if (UsuarioService.isExiste(usuario)) {
			try {
				UsuarioService.save(usuario);
			} catch (Exception e) {
				return new ResponseEntity<ErrorDTO>(
						new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + usuario.getId()),
						HttpStatus.CONFLICT);
			}
		}
		UsuarioService.save(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarFrecuencia(@PathVariable("id") long id) {
		Optional<Usuario> pac = UsuarioService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		UsuarioService.delete(id);
		return new ResponseEntity<Usuario>(pac.get(), HttpStatus.OK);
	}

}
