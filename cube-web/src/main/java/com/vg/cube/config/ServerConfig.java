package com.vg.cube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ServerConfig {

	@Value("${app.global.message}")
	private String globalMessage;

	@Value("${app.local.message}")
    private String localMessage;

	public String getGlobalMessage() {
		return globalMessage;
	}

	public void setGlobalMessage(String globalMessage) {
		this.globalMessage = globalMessage;
	}

	public String getLocalMessage() {
		return localMessage;
	}

	public void setLocalMessage(String localMessage) {
		this.localMessage = localMessage;
	}
	
}
