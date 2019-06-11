package pe.org.jhsystem.cloud.api.nextcloud.rest;

import java.util.Collection;
import java.util.List;
import org.aarboard.nextcloud.api.provisioning.User;
import org.aarboard.nextcloud.api.provisioning.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pe.org.jhsystem.cloud.api.nextcloud.service.IUserService;

@RestController
@RequestMapping("/user/v1.0")
@Api(tags = {"User NextCloud"})
@SwaggerDefinition(tags = {
    @Tag(name = "User NextCloud", description = "API NextCloud para gestion de usuarios")
})
public class UserController {

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private IUserService userService;
	
	@ApiOperation(value = "Crear un usuario.",response = Boolean.class)
	@PostMapping(value="/createUser", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> createUser(@RequestParam String systemId, @RequestParam String userId, @RequestParam String password) {
		boolean creado = userService.createUser(systemId, userId, password);		
		logger.debug("Se ha creado el usuario : " + userId);
	    return new ResponseEntity<Boolean>(creado, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar usuarios existentes.",response = String.class)
	@PostMapping(value="/getAllUsers", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Collection<String>> getUsers(@RequestParam String systemId) {
		Collection<String> lista = userService.getUsers(systemId);	
		logger.debug("Lista de Usuarios : " + lista.toString());
	    return new ResponseEntity<Collection<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar usuarios filtrados por una condición.",response = Collection.class)
	@PostMapping(value="/getUsers", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Collection<String>> getUsers(@RequestParam String systemId, @RequestParam String userId,  @RequestParam int limit) {
		int offset = -1;
		Collection<String> lista = userService.getUsers(systemId, userId, limit, offset);
		return new ResponseEntity<Collection<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Edidtar un usuario.",response = Boolean.class)
	@PostMapping(value="/editUser/{userId}/{field}/{value}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> editUser(@RequestParam String systemId, @RequestParam String userId, @RequestParam UserData field, @RequestParam String value) {
		boolean editado = userService.editUser(systemId, userId, field, value);
		return new ResponseEntity<Boolean>(editado, HttpStatus.OK);
	}

	@ApiOperation(value = "Inhabilitar un usuario.",response = Boolean.class)
	@PostMapping(value="/disableUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> disableUser(@RequestParam String systemId, @RequestParam String userId) {
		boolean deshabilitado = userService.disableUser(systemId, userId);
		return new ResponseEntity<Boolean>(deshabilitado, HttpStatus.OK);
	}

	@ApiOperation(value = "Obtener de los datos de un usuario: email, website, teléfono, dirección, etc",response = User.class)
	@PostMapping(value="/getUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<User> getUser(@RequestParam String systemId, @RequestParam String userId) {
		User user = userService.getUser(systemId, userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "Habilitar un usuario.",response = Boolean.class)
	@PostMapping(value="/enableUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> enableUser(@RequestParam String systemId, @RequestParam String userId) {
		boolean habilitar = userService.enableUser(systemId, userId);
		return new ResponseEntity<Boolean>(habilitar, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar grupos de un usuario.",response = List.class)
	@PostMapping(value="/getGroupsOfUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> getGroupsOfUser(@RequestParam String systemId, @RequestParam String userId) {
		List<String> lista = userService.getGroupsOfUser(systemId, userId);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar un usuario.",response = Boolean.class)
	@PostMapping(value="/deleteUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> deleteUser(@RequestParam String systemId, @RequestParam String userId) {
		boolean eliminado = userService.deleteUser(systemId, userId);
		return new ResponseEntity<Boolean>(eliminado, HttpStatus.OK);
	}
}
