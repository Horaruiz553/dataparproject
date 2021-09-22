package py.com.local.datapar.web.controllers;

import java.util.Date;
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

import py.com.local.datapar.core.model.Periodo;
import py.com.local.datapar.core.services.PeriodoService;
import py.com.local.datapar.util.ErrorDTO;

@RestController
@RequestMapping("/periodo")
@CrossOrigin
public class PeriodoRestController {

	@Autowired
	private PeriodoService PeriodoService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarFrecuencias() {
		List<Periodo> frecu = PeriodoService.findAll();
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Periodo>>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ultimo", method = RequestMethod.GET)
	public ResponseEntity<?> show() {
		Periodo frecu = PeriodoService.findUltimo();
		return new ResponseEntity<Periodo>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/PeriodoByIdUsuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> showUsuario(@PathVariable("id") long id) {
		List<Periodo> frecu = PeriodoService.recuperarPorIdUsuario(id);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Periodo>>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/PeriodoByIdUsuarioEstado/{id}/{estado}", method = RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable("id") long id, @PathVariable("estado") boolean estado) {
		List<Periodo> frecu = PeriodoService.recuperarPorIdUsuarioEstado(id, estado);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Periodo>>(frecu, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearFrecuencia(@RequestBody Periodo frecu) {
		if (PeriodoService.isExisteFrecuencia(frecu)) {
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + frecu.getId()),
					HttpStatus.CONFLICT);
		}
		frecu.setFecha(new Date());
		PeriodoService.save(frecu);
		return new ResponseEntity<Periodo>(frecu, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarFrecuencia(@PathVariable("id") long id) {
		Optional<Periodo> frecu = PeriodoService.findById(id);
		if (!frecu.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		PeriodoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
