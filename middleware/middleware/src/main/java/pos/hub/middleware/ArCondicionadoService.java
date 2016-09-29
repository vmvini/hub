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
@Path("arcondicionado")
public class ArCondicionadoService implements Controle {

    @PUT
    @Override
    @Path("ligar")
    @Produces("application/json")
    public Response ligar() {
        JSONObject json = new JSONObject();
        json.put("arcondicionado ligar", "true");
        return Response.status(200).entity(""+json).build();
    }

    @PUT
    @Path("desligar")
    @Override
    @Produces("application/json")
    public Response desligar() {
        JSONObject json = new JSONObject();
        json.put("desligar ligar", "true");
        return Response.status(200).entity(""+json).build();
    }

    @PUT
    @Path("aumentar/{valor}")
    @Override
    @Produces("application/json")
    public Response aumentar(@PathParam("valor") int valor) {
        
        JSONObject json = new JSONObject();
        json.put("arcondicionado aumentar", valor);
        
        return Response.status(200).entity(""+json).build();
    }

    @PUT
    @Path("diminuir/{valor}")
    @Override
    @Produces("application/json")
    public Response diminuir(@PathParam("valor") int valor) {
        
        JSONObject json = new JSONObject();
        json.put("arcondicionado diminuir", valor);
        
        return Response.status(200).entity(""+json).build();
    }


    @GET
    @Path("intensidade")
    @Override
    @Produces("application/json")
    public Response getIntensidade() {
        JSONObject json = new JSONObject();
        json.put("arcondicionado intensidade", 30);
        
        return Response.status(200).entity(""+json).build();
    }

    @GET
    @Path("status")
    @Override
    @Produces("application/json")
    public Response getStatus() {
        JSONObject json = new JSONObject();
        json.put("arcondicionado ligado", true);
        return Response.status(200).entity(""+json).build();
    }
    
}
