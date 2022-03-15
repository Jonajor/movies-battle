
package com.letscome.cardgame.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    private String id;
    private String name;
    private Long releaseYear;
    private String certificate;
    private String runtime;
    private String genre;
    private Double imdbRating;
    private String metaScore;
    private String director;
}
