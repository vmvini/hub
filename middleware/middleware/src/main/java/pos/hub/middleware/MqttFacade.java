/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author vmvini
 */
public class MqttFacade {
    
    private final String brokerUrl = "tcp://broker:1883";
    private final String myId = "middleware";
    
    
    public void sendMessage(String topic, String msg) throws MqttException{
        
        MqttClient client = new MqttClient(brokerUrl, myId);
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic, message);
        client.disconnect();
       
        
    }
    
}
