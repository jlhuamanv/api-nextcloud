package pe.org.jhsystem.cloud.api.nextcloud.rest;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pe.org.jhsystem.cloud.api.nextcloud.service.IFileService;

@RestController
@RequestMapping("/file/v1.0")
@Api(tags = {"File NextCloud"})
@SwaggerDefinition(tags = {
    @Tag(name = "File NextCloud", description = "API NextCloud para gestion de archivos")
})
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
        private IFileService fileService;
	
	@ApiOperation(value = "Subir un archivo.",response = String.class)
	@PostMapping(value="/uploadFile", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> uploadFile(@RequestParam String systemId, @RequestParam("uploadingFiles") MultipartFile inputStream, @RequestParam  String path) throws JsonProcessingException {
		try {
			fileService.uploadFile(systemId, inputStream.getInputStream(), path);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}		
		logger.debug("Se ha subido el archivo : " + path);
		ObjectMapper om = new ObjectMapper();
		return new ResponseEntity<String>(om.writeValueAsString(path), HttpStatus.OK);
	}

	@ApiOperation(value = "Verificar la existencia de un archivo.",response = Boolean.class)
	@PostMapping(value="/fileExists", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> fileExists(@RequestParam String systemId, @RequestParam String path) {
		boolean existe = fileService.fileExists(systemId, path);
		logger.debug("El archivo existe ? : " + existe);
		return new ResponseEntity<Boolean>(existe, HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar un archivo.",response = String.class)
	@PostMapping(value="/removeFile", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> removeFile(@RequestParam String systemId, @RequestParam String path) throws JsonProcessingException {
		fileService.removeFile(systemId, path);
		logger.debug("Se ha removido el archivo : " + path);
		ObjectMapper om = new ObjectMapper();
		return new ResponseEntity<String>(om.writeValueAsString(path), HttpStatus.OK);
	}
        
        @ApiOperation(value = "Realiza la decarga de un archivo de la nube.",response = String.class)
    	@PostMapping(value="/downloadFile", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> downloadFile(@RequestParam String systemId, @RequestParam  String remotepath, @RequestParam  String downloadpath) throws JsonProcessingException {
		try {
			fileService.downloadFile(systemId, remotepath, downloadpath);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		logger.debug("Se ha descargado el archivo : " + remotepath);
		ObjectMapper om = new ObjectMapper();
		return new ResponseEntity<String>(om.writeValueAsString(remotepath), HttpStatus.OK);
		
	}


}
