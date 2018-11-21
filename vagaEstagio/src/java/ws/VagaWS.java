/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import estagio.dao.CategoriaDAO;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author yumi
 */
@Path("vaga")
public class VagaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VagaWS
     */
    public VagaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.VagaWS
     * @return an instance of java.lang.String
     * - Uma API que pega uma dessas vagas.
    - Uma API que cria uma dessas vagas.
    - Uma API que lista todas as vagas criadas até aquele momento.
     */
    @GET
    @Path("Vaga/GET")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "yumi";
    }
    /*
    @GET
    @Path("List vagas")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "yumi";
    }*/

    /**
     * PUT method for updating or creating an instance of VagaWS
     * @param content representation for the resource
     */
    @PUT
    //@Path("Vaga/PUT")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
