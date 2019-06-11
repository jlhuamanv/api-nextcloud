package pe.org.jhsystem.cloud.api.nextcloud.service;

import java.util.Collection;
import java.util.List;
import org.aarboard.nextcloud.api.provisioning.User;
import org.aarboard.nextcloud.api.provisioning.UserData;

public interface IUserService {
    public boolean createUser(String systemId, String userId, String password) ;
    public Collection<String> getUsers(String systemId);
    public Collection<String> getUsers(String systemId, String userId, int limit, int offset);    
    public boolean editUser(String systemId, String userId, UserData field, String value);    
    public boolean disableUser(String systemId, String userId);    
    public User getUser(String systemId, String userId);    
    public boolean enableUser(String systemId, String userId);    
    public List<String> getGroupsOfUser(String systemId, String userId);    
    public boolean deleteUser(String systemId, String userId);    
}
