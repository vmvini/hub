/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author vmvini
 */
@Path("lampada")
public class LampadaService implements Controle {

    @PUT
    @Override
    @Produces("application/json")
    @Path("ligar")
    public Response ligar() {
        JSONObject json = new JSONObject();
        json.put("lampada ligar", "true");
        return Response.status(200).entity(""+json).build();
    }

    @PUT
    @Path("desligar")
    @Override
    @Produces("application/json")
    public Response desligar() {
        JSONObject json = new JSONObject();
        json.put("lampada desligar", "true");
        return Response.status(200).entity(""+json).build();
    }

    @PUT
    @Path("alterar/{valor}")
    @Override
    @Produces("application/json")
    public Response alterar(@PathParam("valor") int valor) {
        
        JSONObject json = new JSONObject();
        json.put("lampada aumentar", valor);
        
        return Response.status(200).entity(""+json).build();
    }

    @GET
    @Path("intensidade")
    @Override
    @Produces("application/json")
    public Response getIntensidade() {
        JSONObject json = new JSONObject();
        json.put("lampada intensidade", 30);
        
        return Response.status(200).entity(""+json).build();
    }

    @GET
    @Path("status")
    @Override
    @Produces("application/json")
    public Response getStatus() {
        JSONObject json = new JSONObject();
        json.put("lampada ligado", true);
        return Response.status(200).entity(""+json).build();
    }
    
    
}
