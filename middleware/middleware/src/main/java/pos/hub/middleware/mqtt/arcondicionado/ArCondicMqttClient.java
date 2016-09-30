/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.hub.middleware.mqtt.arcondicionado;

import javax.enterprise.context.ApplicationScoped;
import pos.hub.middleware.mqtt.MyAbstractMqttClient;

/**
 *
 * @author vmvini
 */

@ApplicationScoped
@ArCondiMqttClientQualifier
public class ArCondicMqttClient extends MyAbstractMqttClient {

    @Override
    protected String getId() {
        return "arcondicionadoProducer";
    }

    @Override
    protected String getTopic() {
        return "info/arcondicionado/#";
    }
    
}
