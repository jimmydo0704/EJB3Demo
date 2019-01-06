/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import com.dvdat.db.User;
import com.dvdat.db.UserFacade;
import com.dvdat.db.UserFacadeLocal;
import com.dvdat.message.DemoMessagePublisher;
import com.dvdat.message.DemoMessageSender;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;

/**
 *
 * @author dvdat
 */
@Stateless
public class UserService implements UserServiceRemote, UserServiceLocal {

    @EJB
    private RoleServiceLocal roleService;
    
    @EJB
    private DemoMessagePublisher demoPublisher;
    
    @EJB
    private DemoMessageSender messageSender;
    
    @EJB
    private UserFacadeLocal userFacade;
    
    @Override
    public String findUser(String name) {
        System.out.println(">>> Finding user = " + name);
        return "Found user = " + name;
    }

    @Override
    public boolean createUser(String firstname, String lastname) {
        System.out.println(">>> create user with firstname = " + firstname + ", lastname=" + lastname);
        roleService.createRole(firstname, "role 1");
        try {
            User user = new User();
            user.setName(firstname + " " + lastname);
            userFacade.create(user);
            demoPublisher.publish("Created user = " + firstname);
            messageSender.send("Created user = " + firstname);
        } catch (JMSException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean deleteUser(String name) {
        System.out.printf(">>> delete user = %s", name);
        return true;
    }
}
