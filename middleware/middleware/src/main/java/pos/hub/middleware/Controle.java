/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;

import javax.ws.rs.core.Response;

/**
 *
 * @author vmvini
 */
public interface Controle {
    
    Response ligar();
    
    Response desligar();
    
    Response alterar(int valor);
    
    Response getIntensidade();
    
    Response getStatus();
    
}
