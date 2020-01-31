package org.example.exercise5;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Article extends PanacheEntity {

  @NotBlank
  public String title;
  
  public String subtitle;
  
  public String linktotweet;
  
  public String coverImage;
  
  public int likes;
  
  @NotBlank
  public String asciidocsource;

  @Override
  public String toString() {
    return title + ":" + subtitle + ":" + linktotweet + ":" + coverImage + ":" + likes + ":" + asciidocsource;
  }
}
