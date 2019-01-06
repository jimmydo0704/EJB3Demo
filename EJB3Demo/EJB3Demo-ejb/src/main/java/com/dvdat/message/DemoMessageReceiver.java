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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/DemoQueue")
    ,
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DemoMessageReceiver implements MessageListener {
    
    public DemoMessageReceiver() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(">>>> Received message = " + message.getStringProperty("message") + " from DemoQueue");
        } catch (JMSException ex) {
            Logger.getLogger(DemoMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
