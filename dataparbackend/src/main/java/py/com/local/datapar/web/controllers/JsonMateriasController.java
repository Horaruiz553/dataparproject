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

import py.com.local.datapar.core.model.JsonMaterias;
import py.com.local.datapar.core.model.Matriculaciones;
import py.com.local.datapar.core.services.JsonMateriasService;
import py.com.local.datapar.core.services.MatriculacionService;
import py.com.local.datapar.util.ErrorDTO;


@RestController
@RequestMapping("/jsonmaterias")
@CrossOrigin
public class JsonMateriasController {

	@Autowired
	private JsonMateriasService jsonMateriaService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarFrecuencias() {
		List<JsonMaterias> frecu = jsonMateriaService.findAll();
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<JsonMaterias>>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable("id") long id) {
		Optional<JsonMaterias> frecu = jsonMateriaService.findById(id);
		if (!frecu.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin matriculaciones con id " + id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<JsonMaterias>(frecu.get(), HttpStatus.OK);
		}
	}

}
