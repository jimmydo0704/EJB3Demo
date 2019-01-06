/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat.message;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author dvdat
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/DemoTopic")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class DemoMessageSubsriber implements MessageListener {
    
    public DemoMessageSubsriber() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(">>>> Received message = " + message.getStringProperty("message") + " from DemoTopic");
        } catch (JMSException ex) {
            Logger.getLogger(DemoMessageSubsriber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
