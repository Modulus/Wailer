package com.rubblesnask.api;

import com.rubblesnask.jdbi.WailDAO;
import com.rubblesnask.types.Wail;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Iterator;

/**
 * Created by Modulus on 26.03.2015.
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class WailResource {
    private WailDAO wailDAO;
    public WailResource(WailDAO dao) {
        wailDAO = dao;
    }

        @Path("wail")
        @GET
        public Wail getWail(@QueryParam("id")Integer id){
            return wailDAO.findWailById(id);
        }

        @Path("wails")
        @GET
        public Iterator<WailDAO> getWails(){
            return wailDAO.findAll();
        }

        @Path("wail/upvote")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @PUT
        public Response upVote(Wail wail){
            if( wail == null){
                return Response.serverError().build();
            }

            Wail existingWail = wailDAO.findWailById(wail.getId());

           existingWail.increaseUpVotes();

            wailDAO.updateUpVotes(existingWail.getId(), existingWail.getUpVotes());

            return Response.ok().build();
        }

        @Path("wail/downvote")
        @PUT
        public Response downVote(Wail wail){

            if(wail == null){
                return Response.serverError().build();
            }

            Wail existingWail = wailDAO.findWailById(wail.getId());
            existingWail.increateDownVotes();

            wailDAO.updateDownVotes(existingWail.getId(), existingWail.getDownVotes());

            return Response.ok().build();
        }


        @Path("wail")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response insert(Wail wail){

            Timestamp time = new Timestamp(System.currentTimeMillis());
            wailDAO.insert(wail.getPseudonym(), wail.getMessage(), time);
            return Response.ok().build();
        }




}
