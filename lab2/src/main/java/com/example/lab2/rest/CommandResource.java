package com.example.lab2.rest;

import com.example.lab2.entity.Command;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Stateless
@Path("/commands")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Command> list() {
        return em.createQuery("SELECT c FROM Command c", Command.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Command get(@PathParam("id") Long id) {
        Command c = em.find(Command.class, id);
        if (c == null) {
            throw new NotFoundException();
        }
        return c;
    }

    @POST
    @Transactional
    public Response create(Command cmd, @Context UriInfo uriInfo) {
        em.persist(cmd);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(cmd.getId().toString())
                .build();
        return Response.created(uri)
                .entity(cmd)
                .build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Command updated) {
        Command existing = em.find(Command.class, id);
        if (existing == null) {
            throw new NotFoundException();
        }

        existing.setName(updated.getName());
        return Response.ok(existing).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Command c = em.find(Command.class, id);
        if (c == null) {
            throw new NotFoundException();
        }
        em.remove(c);
    }
}
