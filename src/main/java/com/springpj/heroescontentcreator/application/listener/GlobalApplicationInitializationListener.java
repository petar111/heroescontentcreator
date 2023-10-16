package com.springpj.heroescontentcreator.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class GlobalApplicationInitializationListener {

	private static final Logger log = LoggerFactory.getLogger(GlobalApplicationInitializationListener.class);
	
	
	private final RequestMappingHandlerMapping requestMapping;
	
	@Autowired
	public GlobalApplicationInitializationListener(@Qualifier("controllerEndpointHandlerMapping") RequestMappingHandlerMapping requestMapping) {
		super();
		this.requestMapping = requestMapping;
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("info");
		
	}

}
