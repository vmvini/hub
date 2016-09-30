/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware.mqtt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author vmvini
 */
public abstract class MyAbstractMqttClient implements MqttCallback {
    private final String brokerUrl = "tcp://broker:1883";
    //private final String myId = "middleware";
    private MqttClient client;
    private Throwable e;
    
    
    protected abstract String getId();
    protected abstract String getTopic();
    
    @Inject
    @MqttMessageQualifier
    Event<String> cdiEvent;
    
    @PostConstruct
    private void init( @Observes @Initialized(ApplicationScoped.class) Object init ){
        connect();
    }
    
    private void connect(){
        try{
            client = new MqttClient(brokerUrl, getId());
            client.connect();
            client.setCallback(this);
            client.subscribe(getTopic());
            
        }
        catch(MqttException e){
            System.out.println(e.getMessage());
            this.e = e;
        }
        
    }
    
    public void sendMessage(String topic, String msg) throws Throwable{
        if(e != null){
            throw e;
        }
       
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic, message);
        //client.disconnect();
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        e = thrwbl;
        connect();
    }

    @Override
    public void messageArrived(String topic, MqttMessage mm) throws Exception {
        
        String msg = topic + "::" + new String(mm.getPayload());
        
        System.out.println("enviar para o cliente por meio de websocket");
        System.out.println("mensagem:" + new String(mm.getPayload()));
        System.out.println("topico: " + topic);
        
        cdiEvent.fire(msg);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        System.out.println("deliveryComplete");
    }
}
