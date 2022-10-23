package com.project.movie.repository;

import com.project.movie.domain.dao.LoggingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<LoggingModel, String> {
}
