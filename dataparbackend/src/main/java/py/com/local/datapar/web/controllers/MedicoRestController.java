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

import py.com.local.datapar.core.model.Medico;
import py.com.local.datapar.core.model.Paciente;
import py.com.local.datapar.core.services.MedicoService;
import py.com.local.datapar.util.ErrorDTO;

@RestController
@RequestMapping("/medico")
@CrossOrigin
public class MedicoRestController {

	@Autowired
	private MedicoService MedicoService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTodos() {
		List<Medico> Medico = MedicoService.findAll();
		if (Medico.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Medico>>(Medico, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody Medico Medico) {
		if (MedicoService.isExiste(Medico)) {
			try {
				MedicoService.save(Medico);
			} catch (Exception e) {
				return new ResponseEntity<ErrorDTO>(
						new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + Medico.getId()),
						HttpStatus.CONFLICT);
			}
		}
		MedicoService.save(Medico);
		return new ResponseEntity<Medico>(Medico, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminar(@PathVariable("id") long id) {
		Optional<Medico> pac = MedicoService.findById(id);
		if (!pac.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		MedicoService.delete(id);
		return new ResponseEntity<Medico>(pac.get(), HttpStatus.OK);
	}

}
