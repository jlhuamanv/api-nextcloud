package pe.org.jhsystem.cloud.api.nextcloud.service;

import java.util.Collection;
import java.util.List;

public interface IGroupService {
    public boolean createGroup(String systemId, String groupId);    
    public Collection<String> getGroups(String systemId);    
    public Collection<String> getGroups(String systemId, String userId, int limit, int offset);    
    public boolean addUserToGroup(String systemId, String userId, String groupId);    
    public List<String> getMembersOfGroup(String systemId, String groupId);    
    public boolean promoteToSubadmin(String systemId, String userId, String groupId);    
    public List<String> getSubadminGroupsOfUser(String systemId, String userId);    
    public List<String> getSubadminsOfGroup(String systemId, String groupId);    
    public boolean demoteSubadmin(String systemId, String userId, String groupId);    
    public boolean removeUserFromGroup(String systemId, String userId, String groupId);    
    public boolean deleteGroup(String systemId, String groupId);
}
