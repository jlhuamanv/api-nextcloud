package pe.org.jhsystem.cloud.api.nextcloud.service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.org.jhsystem.cloud.api.nextcloud.service.IFileService;

@Service
public class FileServiceImpl implements IFileService{

	@Autowired
	private Map<String, NextcloudConnector> nextcloudConnectorMap;
	
	@Override
	public void uploadFile(String systemId, InputStream inputStream, String path) {
		nextcloudConnectorMap.get(systemId).uploadFile(inputStream, path);
	}

	@Override
	public boolean fileExists(String systemId, String path) {
		return nextcloudConnectorMap.get(systemId).fileExists(path);
	}

	@Override
	public void removeFile(String systemId, String path) {
		nextcloudConnectorMap.get(systemId).removeFile(path);
	}
        @Override
        public void downloadFile(String systemId, String remotepath, String downloadpath) throws IOException {
                nextcloudConnectorMap.get(systemId).downloadFile(remotepath, downloadpath);
}

}