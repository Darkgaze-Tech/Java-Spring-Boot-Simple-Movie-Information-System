package com.project.movie.domain.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.movie.domain.common.BaseDao;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
@SQLDelete(sql = "UPDATE movie SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Movie extends BaseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "movie_plot", nullable = false)
    private String moviePlot;

    @Column(name = "movie_language", nullable = false)
    private String movieLanguage;

    @Column(name = "movie_release_year", nullable = false)
    private Integer movieReleaseYear;

    @ManyToOne
    private Genre movieGenre;

    @ManyToOne
    private User movieUser;

}
