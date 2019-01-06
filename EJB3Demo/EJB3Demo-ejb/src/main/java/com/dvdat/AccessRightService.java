/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

/**
 *
 * @author dvdat
 */
@Stateful
public class AccessRightService implements AccessRightServiceRemote, AccessRightServiceLocal {

    @Override
    public String findAccessRight(String user) {
        System.out.println(">>> Finding access right for user = " + user);
        return "Found access right for user = " + user;
    }

    @Override
    public boolean createAccessRight(String user) {
        System.out.println(">>>> Create access right for user = " + user);
        return true;
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("Destroying");
    }
}
