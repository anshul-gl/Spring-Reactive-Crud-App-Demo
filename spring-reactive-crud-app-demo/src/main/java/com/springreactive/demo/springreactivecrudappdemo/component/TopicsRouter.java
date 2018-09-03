package com.springreactive.demo.springreactivecrudappdemo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.netty.handler.ssl.ApplicationProtocolNames;

@Configuration
public class TopicsRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routePath(TopicsHandler topicsHandler)
	{
		return RouterFunctions
				.route(RequestPredicates.GET("/topics"), topicsHandler::getAllTopics)
				.andRoute(RequestPredicates.POST("/topics/addTopic"), topicsHandler::saveTopic)
				.andRoute(RequestPredicates.GET("/topics/{id}"), topicsHandler::getTopicById)
				.andRoute(RequestPredicates.DELETE("/topics/{id}"), topicsHandler::deleteTopicById)
				.andRoute(RequestPredicates.PUT("/topics/{id}"), topicsHandler::updateTopicById);
		
	}

}
