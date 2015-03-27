package com.rubblesnask.api;

import com.rubblesnask.jdbi.WailDAO;
import com.subblesnask.types.Wail;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

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


        @Path("wail")
        @POST
        @Consumes(value = {MediaType.APPLICATION_JSON})
        @Produces(MediaType.APPLICATION_JSON)
        public Response insert(Wail wail){

            Timestamp time = new Timestamp(System.currentTimeMillis());
            wailDAO.insert(wail.getPseudonym(), wail.getMessage(), time);
            return Response.ok().build();
        }




}
