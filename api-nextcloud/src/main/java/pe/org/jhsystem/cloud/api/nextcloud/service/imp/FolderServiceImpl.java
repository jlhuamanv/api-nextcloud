package pe.org.jhsystem.cloud.api.nextcloud.service.imp;

import java.util.List;
import java.util.Map;
import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.org.jhsystem.cloud.api.nextcloud.service.IFolderService;

@Service
public class FolderServiceImpl implements IFolderService{

	@Autowired
	private Map<String, NextcloudConnector> nextcloudConnectorMap;
	
	@Override
	public void createFolder(String systemId, String path) {
		nextcloudConnectorMap.get(systemId).createFolder(path);
	}
	
	@Override
	public boolean folderExists(String systemId, String path) {
		return nextcloudConnectorMap.get(systemId).folderExists(path);
	}

	@Override
	public void deleteFolder(String systemId, String path) {
		nextcloudConnectorMap.get(systemId).deleteFolder(path);
	}

	@Override
	public List<String> listFolderContent(String systemId, String path) {
		return nextcloudConnectorMap.get(systemId).listFolderContent(path);
	}

	@Override
	public List<String> listFolderContent(String systemId, String path, int depth) {
		return nextcloudConnectorMap.get(systemId).listFolderContent(path, depth);
	}

}
