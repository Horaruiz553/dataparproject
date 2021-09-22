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
import py.com.local.datapar.core.model.Registro;
import py.com.local.datapar.core.services.RegistroService;
import py.com.local.datapar.util.ErrorDTO;

@RestController
@RequestMapping("/registro")
@CrossOrigin
public class RegistroRestController {

	@Autowired
	private RegistroService RegistroService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorId(@PathVariable("id") long id) {
		Optional<Registro> pac = RegistroService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Registro>(pac.get(), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorIdMedico(@PathVariable("id") long id) {
		Registro pac = RegistroService.listarPorIdMedico(id);
		if (pac == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Registro>(pac, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/paciente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorIdPaciente(@PathVariable("id") long id) {
		Registro pac = RegistroService.listarPorIdPaciente(id);
		if (pac == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin detalles"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Registro>(pac, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTodos() {
		List<Registro> Registro = RegistroService.findAll();
		if (Registro.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Registro>>(Registro, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody Registro Registro) {
		if (RegistroService.isExiste(Registro)) {
			try {
				RegistroService.save(Registro);
			} catch (Exception e) {
				return new ResponseEntity<ErrorDTO>(
						new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + Registro.getId()),
						HttpStatus.CONFLICT);
			}
		}
		RegistroService.save(Registro);
		return new ResponseEntity<Registro>(Registro, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminar(@PathVariable("id") long id) {
		Optional<Registro> pac = RegistroService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		RegistroService.delete(id);
		return new ResponseEntity<Registro>(pac.get(), HttpStatus.OK);
	}

}
