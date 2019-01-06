/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import javax.ejb.Stateless;

/**
 *
 * @author dvdat
 */
@Stateless
public class RoleService implements RoleServiceRemote, RoleServiceLocal {

    @Override
    public String findRole(String user) {
        return "Found role for user = " + user;
    }

    @Override
    public boolean createRole(String user, String role) {
        System.out.println("Role = " + role + " has created for user = " + user);
        return true;
    }
}
