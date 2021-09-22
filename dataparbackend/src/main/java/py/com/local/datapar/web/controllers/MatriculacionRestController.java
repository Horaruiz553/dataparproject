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

import py.com.local.datapar.core.model.Matriculaciones;
import py.com.local.datapar.core.services.MatriculacionService;
import py.com.local.datapar.util.ErrorDTO;


@RestController
@RequestMapping("/matriculaciones")
@CrossOrigin
public class MatriculacionRestController {

	@Autowired
	private MatriculacionService matriculacionService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> listarFrecuencias(@PathVariable("codigo") String codigo) {
		List<Matriculaciones> frecu = matriculacionService.findAll(codigo);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Matriculaciones>>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable("id") long id) {
		Optional<Matriculaciones> frecu = matriculacionService.findById(id);
		if (!frecu.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Sin matriculaciones con id " + id), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Matriculaciones>(frecu.get(), HttpStatus.OK);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/pendientes/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> pendientes(@PathVariable("codigo") String codigo) {
		List<Matriculaciones> frecu = matriculacionService.listarPendientes(codigo);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Matriculaciones>>(frecu, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{idusuario}/{idmateria}/{periodo}", method = RequestMethod.GET)
	public ResponseEntity<?> listrarExistencia(@PathVariable("idusuario") long idUsuario, @PathVariable("idmateria") long idmateria,
			@PathVariable("periodo") String periodo) {
		Boolean frecu = matriculacionService.listrarExistencia(idUsuario, idmateria, periodo);
		return new ResponseEntity<Boolean>(frecu, HttpStatus.OK);
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/matriculacionesByIdUsuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> showUsuario(@PathVariable("id") long id) {
		List<Matriculaciones> frecu = matriculacionService.recuperarPorIdUsuario(id);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Matriculaciones>>(frecu, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/matriculacionesByIdUsuarioEstado/{id}/{estado}", method = RequestMethod.GET)
	public ResponseEntity<?> show(@PathVariable("id") long id, @PathVariable("estado") boolean estado) {
		List<Matriculaciones> frecu = matriculacionService.recuperarPorIdUsuarioEstado(id, estado);
		if (frecu.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Matriculaciones>>(frecu, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/quitarMatriculacion/{id}/{idmateria}", method = RequestMethod.GET)
	public ResponseEntity<?> quitarMatriculacion(@PathVariable("id") long id, @PathVariable("idmateria") long idmateria) {
		matriculacionService.quitarMatriculacion(id, idmateria);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearFrecuencia(@RequestBody Matriculaciones frecu) {
		if (matriculacionService.isExisteFrecuencia(frecu)) {
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("Inserción Fallida. Ya existe un registro con ID: " + frecu.getId()),
					HttpStatus.CONFLICT);
		}
		matriculacionService.save(frecu);
		return new ResponseEntity<Matriculaciones>(frecu, HttpStatus.OK);
	}

	// ================ ACTUALIZAMOS LOS DATOS DE UNA Frecuencia ================
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarFrecuencia(@RequestBody Matriculaciones frecu) {
		Optional<Matriculaciones> u = matriculacionService.findById(frecu.getId());
		if (u == null) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualización fallida. No existe ID: " + frecu.getId()),
					HttpStatus.NOT_FOUND);
		}
		Matriculaciones frecuBD = u.get();
		frecuBD.setIdusuario(frecu.getIdusuario());
		frecuBD.setEstado(frecu.getEstado());
		frecuBD.setProcesado(frecu.getProcesado());
		matriculacionService.save(frecuBD);
		return new ResponseEntity<Matriculaciones>(frecuBD, HttpStatus.OK);
	}

	// ================ ELIMINAMOS UNAnFrecuencia ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarFrecuencia(@PathVariable("id") long id) {
		Optional<Matriculaciones> frecu = matriculacionService.findById(id);
		if (!frecu.isPresent()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el ID: " + id), HttpStatus.NOT_FOUND);
		}
		matriculacionService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
