package com.springreactive.demo.springreactivecrudappdemo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import com.springreactive.demo.springreactivecrudappdemo.model.Topic;
import com.springreactive.demo.springreactivecrudappdemo.services.TopicService;
import com.springreactive.demo.springreactivecrudappdemo.wrapper.CoursesWrapperClass;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TopicsHandler {
	
	@Autowired
	TopicService topicService;
	
	public Mono<ServerResponse> getAllTopics(ServerRequest request)
	{
		Flux<Topic> fluxTopics = topicService.getAllTopics();
		return ServerResponse.ok().body(fluxTopics,Topic.class);
				
	}
	
	public Mono<ServerResponse> saveTopic(ServerRequest request){
		return ServerResponse.ok()
				.body(topicService.saveTopic(request.bodyToMono(Topic.class)),Topic.class);
	}
	
	public Mono<ServerResponse> getTopicById(ServerRequest request)
	{
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<Topic> topicMonoObj = topicService.getTopicById(id);
		return ServerResponse.ok().body(topicMonoObj,Topic.class);
	}
	
	public Mono<ServerResponse> deleteTopicById(ServerRequest request)
	{
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<Void> deleteTopic = topicService.deleteTopicById(id);
		return ServerResponse.ok().body(deleteTopic,Void.class);
		
	}
	
	public Mono<ServerResponse> updateTopicById(ServerRequest request)
	{
		System.out.println(request);
		int id = Integer.valueOf(request.pathVariable("id"));
		Mono<CoursesWrapperClass> courseWrapperObj = request.bodyToMono(CoursesWrapperClass.class);
		Mono<Topic> updateMonoTopicObj = topicService.updateTopicById(id,courseWrapperObj);
		return ServerResponse.ok().body(updateMonoTopicObj,Topic.class);
		
	}
}
