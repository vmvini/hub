/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import pos.hub.middleware.mqtt.MyAbstractMqttClient;
import pos.hub.middleware.mqtt.lampada.LampadaMqttClientQualifier;

/**
 *
 * @author vmvini
 */
@Path("lampada")
public class LampadaService implements Controle {

    @Inject
    @LampadaMqttClientQualifier
    private MyAbstractMqttClient mqtt;
    
    private final String topic = "lampada/";
    
    private Response sendMessage(Comandos subtopic, String message){
        
        try{
            mqtt.sendMessage(this.topic + subtopic.getTopic(), message);
            JSONObject json = new JSONObject();
            json.put("success", "true");
            return Response.status(200).entity(""+json).build();
        }
        catch(Throwable e){
            JSONObject json = new JSONObject();
            json.put("msg", "Erro ao conectar ao mqtt broker");
            return Response.status(500).entity(""+json).build();
        }
    }
    
    @PUT
    @Override
    @Produces("application/json")
    @Path("ligar")
    public Response ligar() {
        return sendMessage(Comandos.LIGAR, "ligar lampada");
    }

    @PUT
    @Path("desligar")
    @Override
    @Produces("application/json")
    public Response desligar() {
        return sendMessage(Comandos.DESLIGAR, "desligar lampada");
    }

    @PUT
    @Path("alterar/{valor}")
    @Override
    @Produces("application/json")
    public Response alterar(@PathParam("valor") int valor) {
        return sendMessage(Comandos.ALTERAR, Integer.toString(valor));
    }

    @GET
    @Path("intensidade")
    @Override
    @Produces("application/json")
    public Response getIntensidade() {
        return sendMessage(Comandos.INTENSIDADE, "get brilho");
    }

    @GET
    @Path("status")
    @Override
    @Produces("application/json")
    public Response getStatus() {
        return sendMessage(Comandos.STATUS, "get status");
    }
    
    
}
