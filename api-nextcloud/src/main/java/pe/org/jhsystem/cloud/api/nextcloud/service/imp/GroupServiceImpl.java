package pe.org.jhsystem.cloud.api.nextcloud.service.imp;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.org.jhsystem.cloud.api.nextcloud.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService{
	
	@Autowired
	private Map<String, NextcloudConnector> nextcloudConnectorMap;

	@Override
	public boolean createGroup(String systemId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).createGroup(idGroup);
	}

	@Override
	public Collection<String> getGroups(String systemId) {
		return nextcloudConnectorMap.get(systemId).getGroups();
	}

	@Override
	public Collection<String> getGroups(String systemId, String userId, int limit, int offset) {
		return nextcloudConnectorMap.get(systemId).getGroups(userId, limit, offset);
	}

	@Override
	public boolean addUserToGroup(String systemId, String userId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).addUserToGroup(userId, idGroup);
	}

	@Override
	public List<String> getMembersOfGroup(String systemId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).getMembersOfGroup(idGroup);
	}

	@Override
	public boolean promoteToSubadmin(String systemId, String userId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).promoteToSubadmin(userId, idGroup);
	}

	@Override
	public List<String> getSubadminGroupsOfUser(String systemId, String userId) {
		return nextcloudConnectorMap.get(systemId).getSubadminGroupsOfUser(userId);
	}

	@Override
	public List<String> getSubadminsOfGroup(String systemId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).getSubadminsOfGroup(idGroup);
	}

	@Override
	public boolean demoteSubadmin(String systemId, String userId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).demoteSubadmin(userId, idGroup);
	}

	@Override
	public boolean removeUserFromGroup(String systemId, String userId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).removeUserFromGroup(userId, idGroup);
	}

	@Override
	public boolean deleteGroup(String systemId, String idGroup) {
		return nextcloudConnectorMap.get(systemId).deleteGroup(idGroup);
	}

}
