package com.springreactive.demo.springreactivecrudappdemo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.springreactive.demo.springreactivecrudappdemo.model.Topic;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "topic")
public interface TopicRepository extends ReactiveCouchbaseRepository<Topic,Integer> {

}
