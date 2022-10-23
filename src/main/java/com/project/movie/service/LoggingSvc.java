package com.project.movie.service;

import java.util.HashMap;

public interface LoggingSvc {

    void createLog(HashMap<String, Object> map, String type);
}

