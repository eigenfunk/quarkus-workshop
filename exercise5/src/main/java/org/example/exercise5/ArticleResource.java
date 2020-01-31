package org.example.exercise5;


import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {

  @OPTIONS
  public Response opt() {
    return Response.ok().build();
  }

  @GET
  public List<Article> getAll() {
    return Article.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getOne(@PathParam("id") Long id) {
    Article entity = Article.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    return Response
      .status(Response.Status.OK)
      .entity(entity)
      .build();
  }

  @POST
  @Transactional
  public Response create(Article article) {
    article.persist();
    return Response.status(Response.Status.CREATED).entity(article).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(Article article, @PathParam("id") Long id) {
    Article entity = Article.findById(id);
    entity.asciidocsource = article.asciidocsource;
    entity.coverImage = article.coverImage;
    entity.linktotweet = article.linktotweet;
    entity.subtitle = article.subtitle;
    entity.title = article.title;
    entity.likes = article.likes;
    entity.persist();
    return Response.ok(entity).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public Response deleteOne(@PathParam("id") Long id) {
    Article entity = Article.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    entity.delete();
    return Response.noContent().build();
  }
}
