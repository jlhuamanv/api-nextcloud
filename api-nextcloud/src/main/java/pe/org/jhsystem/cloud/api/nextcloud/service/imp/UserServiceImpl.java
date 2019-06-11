package pe.org.jhsystem.cloud.api.nextcloud.service.imp;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.aarboard.nextcloud.api.NextcloudConnector;
import org.aarboard.nextcloud.api.provisioning.User;
import org.aarboard.nextcloud.api.provisioning.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.org.jhsystem.cloud.api.nextcloud.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private Map<String, NextcloudConnector> nextcloudConnectorMap;

	@Override
	public boolean createUser(String systemId, String userId, String password) {
		return nextcloudConnectorMap.get(systemId).createUser(userId, password);
	}

	@Override
	public Collection<String> getUsers(String systemId) {
		return nextcloudConnectorMap.get(systemId).getUsers();
	}

	@Override
	public Collection<String> getUsers(String systemId, String userId, int limit, int offset) {
		return nextcloudConnectorMap.get(systemId).getUsers(userId, limit, offset);
	}

	@Override
	public boolean editUser(String systemId, String userId, UserData field, String value) {
		return nextcloudConnectorMap.get(systemId).editUser(userId, field, value);
	}

	@Override
	public boolean disableUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).disableUser(userId);
	}

	@Override
	public User getUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).getUser(userId);
	}

	@Override
	public boolean enableUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).enableUser(userId);
	}

	@Override
	public List<String> getGroupsOfUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).getGroupsOfUser(userId);
	}

	@Override
	public boolean deleteUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).deleteUser(userId);
	}
}
