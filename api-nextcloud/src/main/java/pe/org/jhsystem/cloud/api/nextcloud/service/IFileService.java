package pe.org.jhsystem.cloud.api.nextcloud.service;

import java.io.IOException;
import java.io.InputStream;

public interface IFileService {
    public void uploadFile(String systemId, InputStream inputStream, String path);
    public boolean fileExists(String systemId, String path);    
    public void removeFile(String systemId, String path);
    public void downloadFile(String systemId, String remotepath, String downloadpath) throws IOException;
}
