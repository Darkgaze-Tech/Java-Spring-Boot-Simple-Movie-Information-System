package com.project.movie.domain.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;

@Data
@Document("logging")
public class LoggingModel implements Serializable {
    private static final long serialVersionUID = -2735289910888223636L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private HashMap<String, Object> map;
    private String type;
}

