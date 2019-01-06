/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import com.dvdat.db.User;
import com.dvdat.db.UserFacadeLocal;
import com.dvdat.dto.UserDTO;
import com.dvdat.message.DemoMessagePublisher;
import com.dvdat.message.DemoMessageSender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public List<UserDTO> findUser(String name) {
        System.out.println(">>> Finding user = " + name);
                    
        List<User> allUsers = userFacade.findUserByName(name);
        if (allUsers == null || allUsers.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        
        List<UserDTO> users = new ArrayList<>();
        for(int i = 0; i < allUsers.size(); i++) {
            users.add(new UserDTO(allUsers.get(i).getId(), allUsers.get(i).getName()));
        }
        
        return users;
    }

    @Override
    public boolean createUser(String name) {
        System.out.println(">>> Creating user with name = " + name);
        System.out.println(this);
        try {
            User user = new User();
            user.setName(name);
            userFacade.create(user);

            roleService.createRole(name, "role 1");

            demoPublisher.publish(">>>> Created user = " + name);
            messageSender.send(">>>> Created user = " + name);
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
