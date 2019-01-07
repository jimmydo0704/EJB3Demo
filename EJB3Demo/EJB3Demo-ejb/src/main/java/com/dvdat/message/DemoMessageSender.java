/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat.message;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author dvdat
 */
@Stateless
@LocalBean
public class DemoMessageSender {
    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")            
    JMSContext context;

    @Resource(mappedName = "java:/jms/DemoQueue")
    private Queue demoQueue;
    
    public void send(String message) throws JMSException {
        JMSProducer producer = context.createProducer();
        
        // Set time to live to 30 days
        producer.setTimeToLive(2592000000L);
        ObjectMessage objectMessage = context.createObjectMessage();
        objectMessage.setStringProperty("message", message);
        
        producer.send(demoQueue, objectMessage);
    }
}
