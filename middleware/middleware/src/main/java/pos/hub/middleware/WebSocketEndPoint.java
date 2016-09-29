/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 *
 * @author vmvini
 */

@ServerEndpoint(value="/websocket")
public class WebSocketEndPoint {
    
    private static Set<Session> sessions = new HashSet<>();
    
    @OnOpen
    public void onOpen(final Session session){
        System.out.println("Novo cliente conectado");
        sessions.add(session);
        
    }
    
    public void onMqttMessage(@Observes @MqttMessageQualifier String msg){
        Iterator<Session> it = sessions.iterator();
        while(it.hasNext()){
            try{
                it.next().getBasicRemote().sendText(msg);
                System.out.println("enviou mensagem por websocket");
            }
            catch(IOException e){
                System.out.println("Erro ao enviar mensagem com websocket");
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    @OnMessage
    public void onMessage(final String message, final Session client){
        System.out.println("mensagem recebida: " + message);
    }
    
    @OnClose
    public void onClose(final Session session){
        sessions.remove(session);
    }
    
}
