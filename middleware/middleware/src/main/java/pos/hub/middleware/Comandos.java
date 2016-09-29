/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;

/**
 *
 * @author vmvini
 */
public enum Comandos {
    
    LIGAR("ligar"),
    DESLIGAR("desligar"),
    STATUS("status"),
    INTENSIDADE("intensidade"),
    ALTERAR("alterar");
    
    private String topic;
    
    Comandos(String topic){
        this.topic = topic;
    }
    
    public String getTopic(){
        return topic;
    }
    
}
