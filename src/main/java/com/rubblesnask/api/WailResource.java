package com.rubblesnask.api;

import com.rubblesnask.jdbi.WailDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * Created by Modulus on 26.03.2015.
 */
@Path("/wail")
@Produces(MediaType.APPLICATION_JSON)
public class WailResource {
    private WailDAO wailDAO;
    public WailResource(WailDAO dao) {
        wailDAO = dao;
    }

    @GET
        public WailDAO getWail(@QueryParam("id")Integer id){
            return wailDAO.findWailById(id);
        }

        @Path("/all")
        @GET
        public List<WailDAO> getWails(){
            return wailDAO.findAll();
        }




}
