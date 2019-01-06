/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

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
}
