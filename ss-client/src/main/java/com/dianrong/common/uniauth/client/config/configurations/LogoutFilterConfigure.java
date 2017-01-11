package com.dianrong.common.uniauth.client.config.configurations;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.dianrong.common.uniauth.client.config.Configure;

/**
 *  configure new LogoutFilter
 * @author wanglin
 */
@Component
public class LogoutFilterConfigure implements Configure<LogoutFilter>{
	
	private static final String DEFAULT_FILTER_PROCESS_URL = "/logout/cas";
	
	@Resource(name="uniauthConfig")
	private Map<String, String> uniauthConfig;
	
	@Resource(name="securityContextLogoutHandler")
	private SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Override
	public LogoutFilter create() {
		LogoutFilter logoutFilter = new LogoutFilter(getCasLogoutUrl(), securityContextLogoutHandler);
		logoutFilter.setFilterProcessesUrl(DEFAULT_FILTER_PROCESS_URL);
		return logoutFilter;
	}

	@Override
	public boolean isSupport(Class<?> cls) {
		return LogoutFilter.class.equals(cls);
	}
	
	private String getCasLogoutUrl() {
		return uniauthConfig.get("cas_server")+"/logout";
	}
}
