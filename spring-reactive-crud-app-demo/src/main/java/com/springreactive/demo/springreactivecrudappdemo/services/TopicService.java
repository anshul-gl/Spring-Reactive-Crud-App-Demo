package com.springreactive.demo.springreactivecrudappdemo.services;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springreactive.demo.springreactivecrudappdemo.model.Topic;
import com.springreactive.demo.springreactivecrudappdemo.repository.TopicRepository;
import com.springreactive.demo.springreactivecrudappdemo.wrapper.CoursesWrapperClass;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public Flux<Topic> getAllTopics() {		
		return topicRepository.findAll();
	}

	public Mono<Topic> saveTopic(Mono<Topic> topicMonoObj) {
		return topicMonoObj.flatMap(topicRepository::save);
	}

	public Mono<Topic> getTopicById(int id) {
		return topicRepository.findById(id);
	}

	public Mono<Void> deleteTopicById(int id) {
		return topicRepository.deleteById(id);
	}
	
	public Mono<Topic> updateTopicById(int id, Mono<CoursesWrapperClass> courseWrapperObj)
	{
//		courseWrapperObj.flatMap(wrapper->System.out.println(wrapper.getIds));
		
		return courseWrapperObj.flatMap(wrapper->
		{
			return topicRepository.findById(id).flatMap(topic->{
			System.out.println(wrapper.getIds());
			topic.setCourseIds(wrapper.getIds());
			return topicRepository.save(topic);
			});
		});		
	}
	

}
