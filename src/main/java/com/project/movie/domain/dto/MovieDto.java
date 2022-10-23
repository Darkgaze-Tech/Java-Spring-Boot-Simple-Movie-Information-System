package com.project.movie.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.movie.domain.dao.Genre;
import com.project.movie.domain.dao.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieDto {
    
    private Long id;
    
    private String movieTitle;

    private String moviePlot;

    private String movieLanguage;

    private Integer movieReleaseYear;

    private Genre movieGenre;

    private User movieUser;

}

