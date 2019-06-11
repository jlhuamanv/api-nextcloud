package pe.org.jhsystem.cloud.api.nextcloud.service;

import java.util.List;

public interface IFolderService {
    public void createFolder(String systemId, String path);
    public boolean folderExists(String systemId, String path);
    public void deleteFolder(String systemId, String path);
    public List<String> listFolderContent(String systemId, String path);    
    public List<String> listFolderContent(String systemId, String path, int depth);
}
