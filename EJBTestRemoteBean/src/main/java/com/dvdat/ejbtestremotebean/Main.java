/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat.ejbtestremotebean;

import com.dvdat.AccessRightServiceRemote;
import com.dvdat.UserServiceRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author dvdat
 */
public class Main {
    private final Properties props = new Properties();
    private Context context;
    public static void main(String[] args) {
        Main app = new Main();
        
        UserServiceRemote userService = (UserServiceRemote) app.getUserServiceRemoteBean("UserService", false);
        System.out.printf(">>>> %s\n", userService.findUser("Dat Do"));
        userService.createUser("Dat", "Do");
        
        AccessRightServiceRemote accesRightService = (AccessRightServiceRemote) app.getUserServiceRemoteBean("AccessRightService", true);
        System.out.printf(">>>> %s\n", accesRightService.findAccessRight("Dat"));
    }
    
    private Object getUserServiceRemoteBean(String serviceName, boolean stateful) {
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            if (context == null) {
                context = new InitialContext(props);
            }
            return context.lookup(buildRemoteJndiName(serviceName, stateful));
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static String buildRemoteJndiName(String serviceName, boolean stateful) {
        String state = "";
        if(stateful) {
            state = "?stateful";
        }
        return String.format("ejb:EJB3Demo-ear-1.0-SNAPSHOT/EJB3Demo-ejb-1.0-SNAPSHOT/%s!com.dvdat.%sRemote%s", 
                serviceName, serviceName, state);
    }
}
