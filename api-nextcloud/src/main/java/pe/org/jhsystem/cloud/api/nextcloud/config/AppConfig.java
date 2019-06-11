package pe.org.jhsystem.cloud.api.nextcloud.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan({"pe.org.jhsystem.cloud.api.nextcloud.*"})
@PropertySources({
    @PropertySource("classpath:api-nextcloud.properties"),
    @PropertySource(value = "file:{root_api}/api-nextcloud/data/config/api-nextcloud.properties", ignoreResourceNotFound = true)
})
@ConfigurationProperties(prefix = "next.cloud")
public class AppConfig {
	private List<String> serverName;
	private List<Integer> serverPort;
	private List<String> userName;
	private List<String> password;
	private List<Boolean> https;
	private List<String> ids;

    @Bean
    @Primary    
    public Map<String, NextcloudConnector> nextcloudConnectorMap() {
    	return IntStream.range(0,serverName.size()).boxed().collect(
    		Collectors.toMap(cont -> ids.get(cont),
    		cont -> new NextcloudConnector(serverName.get(cont), https.get(cont), serverPort.get(cont), userName.get(cont), password.get(cont))));
    }

	public List<String> getServerName() {
		return serverName;
	}

	public void setServerName(List<String> serverName) {
		this.serverName = serverName;
	}

	public List<Integer> getServerPort() {
		return serverPort;
	}

	public void setServerPort(List<Integer> serverPort) {
		this.serverPort = serverPort;
	}

	public List<String> getUserName() {
		return userName;
	}

	public void setUserName(List<String> userName) {
		this.userName = userName;
	}

	public List<String> getPassword() {
		return password;
	}

	public void setPassword(List<String> password) {
		this.password = password;
	}

	public List<Boolean> getHttps() {
		return https;
	}

	public void setHttps(List<Boolean> https) {
		this.https = https;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
