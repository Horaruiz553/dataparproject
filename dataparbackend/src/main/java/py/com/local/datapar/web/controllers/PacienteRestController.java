package py.com.local.datapar.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.local.datapar.core.model.Paciente;
import py.com.local.datapar.core.services.PacienteService;
import py.com.local.datapar.util.ErrorDTO;

@RestController
@RequestMapping("/paciente")
@CrossOrigin
public class PacienteRestController {

	@Autowired
	private PacienteService PacienteService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTodos() {
		List<Paciente> paciente = PacienteService.findAll();
		if (paciente.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Paciente>>(paciente, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody Paciente paciente) {
		if (PacienteService.isExiste(paciente)) {
			try {
				PacienteService.save(paciente);
			} catch (Exception e) {
				return new ResponseEntity<ErrorDTO>(
						new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + paciente.getId()),
						HttpStatus.CONFLICT);
			}
		}
		PacienteService.save(paciente);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminar(@PathVariable("id") long id) {
		Optional<Paciente> pac = PacienteService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		PacienteService.delete(id);
		return new ResponseEntity<Paciente>(pac.get(), HttpStatus.OK);
	}

}
