package pe.org.jhsystem.cloud.api.nextcloud.rest;

import java.util.Collection;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pe.org.jhsystem.cloud.api.nextcloud.service.IGroupService;

@RestController
@RequestMapping("/group/v1.0")
@Api(tags = {"Group NextCloud"})
@SwaggerDefinition(tags = {
    @Tag(name = "Group NextCloud", description = "API NextCloud para gestion de grupos")
})
public class GroupController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private IGroupService groupService;
	
	@ApiOperation(value = "Crear un grupo.",response = Boolean.class)
	@PostMapping(value="/createGroup/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> createGroup(@RequestParam String systemId, @RequestParam String groupId) {
		boolean creado = groupService.createGroup(systemId, groupId);
		logger.debug("Se ha creado?:"+creado);
		return new ResponseEntity<Boolean>(creado, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar de grupos existentes.",response = Collection.class)
	@PostMapping(value="/getGroups", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Collection<String>> getGroups(@RequestParam String systemId) {
		Collection<String> lista = groupService.getGroups(systemId);
		return new ResponseEntity<Collection<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar grupos filtrado por una condición.",response = Collection.class)
	@PostMapping(value="/getGroups/{groupId}/{limit}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Collection<String>> getGroups(@RequestParam String systemId, @RequestParam String groupId, @RequestParam int limit) {
		int offset = -1;
		Collection<String> lista = groupService.getGroups(systemId, groupId, limit, offset);
		return new ResponseEntity<Collection<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Agregar un usuario a un grupo.",response = Boolean.class)
	@PostMapping(value="/addUserToGroup/{userId}/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> addUserToGroup(@RequestParam String systemId, @RequestParam String userId, @RequestParam String groupId) {
		boolean agregado = groupService.addUserToGroup(systemId, userId, groupId);
		return new ResponseEntity<Boolean>(agregado, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar miembros de un grupo.",response = List.class)
	@PostMapping(value="/getMembersOfGroup/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> getMembersOfGroup(@RequestParam String systemId, @RequestParam String groupId) {
		List<String> lista = groupService.getMembersOfGroup(systemId, groupId);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Promover un sub admin.",response = Boolean.class)
	@PostMapping(value="/getMembersOfGroup/{userId}/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> promoteToSubadmin(@RequestParam String systemId, @RequestParam String userId, @RequestParam String groupId) {
		boolean promote = groupService.promoteToSubadmin(systemId, userId, groupId);
		return new ResponseEntity<Boolean>(promote, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar grupos sub admin de un usuario.",response = List.class)
	@PostMapping(value="/getSubadminGroupsOfUser/{userId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> getSubadminGroupsOfUser(@RequestParam String systemId, @RequestParam String userId) {
		List<String> lista = groupService.getSubadminGroupsOfUser(systemId, userId);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar sub admin de un grupo.",response = List.class)
	@PostMapping(value="/getSubadminsOfGroup/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<String>> getSubadminsOfGroup(@RequestParam String systemId, @RequestParam String groupId) {
		List<String> lista = groupService.getSubadminsOfGroup(systemId, groupId);
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Degradar un sub admin.",response = Boolean.class)
	@PostMapping(value="/demoteSubadmin/{userId}/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> demoteSubadmin(@RequestParam String systemId, @RequestParam String userId, @RequestParam String groupId) {
		boolean degradado = groupService.demoteSubadmin(systemId, userId, groupId);
		return new ResponseEntity<Boolean>(degradado, HttpStatus.OK);
	}

	@ApiOperation(value = "Remover usuario de un grupo.",response = Boolean.class)
	@PostMapping(value="/removeUserFromGroup/{userId}/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> removeUserFromGroup(@RequestParam String systemId, @RequestParam String userId, @RequestParam String groupId) {
		boolean removido = groupService.removeUserFromGroup(systemId, userId, groupId);
		return new ResponseEntity<Boolean>(removido, HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar un grupo.",response = Boolean.class)
	@PostMapping(value="/deleteGroup/{groupId}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Boolean> deleteGroup(@RequestParam String systemId, @RequestParam String groupId) {
		boolean eliminado = groupService.deleteGroup(systemId, groupId);
		return new ResponseEntity<Boolean>(eliminado, HttpStatus.OK);
	}
}
