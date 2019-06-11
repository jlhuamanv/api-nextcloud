package pe.org.jhsystem.cloud.api.nextcloud.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pe.org.jhsystem.cloud.api.nextcloud.service.IFolderService;

@RestController
@RequestMapping("/folder/v1.0")
@Api(tags = {"Folder NextCloud"})
@SwaggerDefinition(tags = {
    @Tag(name = "Folder NextCloud", description = "API NextCloud para gestion de carpetas")
})
public class FolderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private IFolderService folderService;
	
	@ApiOperation(value = "Crear una carpeta.",response = String.class)
	@PostMapping(value="/createFolder", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> createFolder(@RequestParam String systemId, @RequestParam String path) throws JsonProcessingException{
		folderService.createFolder(systemId, path);
		logger.debug("Se ha creado el folder : " + path);
		ObjectMapper om = new ObjectMapper();
	    return new ResponseEntity<String>(om.writeValueAsString(path), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Verificar la existencia de una carpeta.",response = Boolean.class)
	@PostMapping(value="/folderExists", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> folderExists(@RequestParam String systemId, @RequestParam String path) {
		boolean existe = folderService.folderExists(systemId, path);
		logger.debug("El folder existe ? : " + existe);
		return new ResponseEntity<Boolean>(existe, HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar de una carpeta.",response = String.class)
	@PostMapping(value="/deleteFolder", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> deleteFolder(@RequestParam String systemId, @RequestParam String path) throws JsonProcessingException {
		folderService.deleteFolder(systemId, path);
		logger.debug("Se ha eliminado el folder : " + path);
		ObjectMapper om = new ObjectMapper();
		return new ResponseEntity<String>(om.writeValueAsString(path), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar el contenido de una carpeta.",response = List.class)
	@PostMapping(value="/listFolderContent", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> listFolderContent(@RequestParam String systemId, @RequestParam String path) {
		List<String> lista = folderService.listFolderContent(systemId, path);
		logger.debug("La carpeta contiene : " + lista);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar el contenido de una carpeta filtrado por una condición.",response = List.class)
	@PostMapping(value="/listFolderContent/depth", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> listFolderContent(@RequestParam String systemId, @RequestParam String path, @RequestParam int depth) {
		List<String> lista =  folderService.listFolderContent(systemId, path, depth);
		logger.debug("La carpeta contiene : " + lista);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}
}
