/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import javax.ejb.Remote;

/**
 *
 * @author dvdat
 */
@Remote
public interface UserServiceRemote {
    String findUser(String name);
    boolean createUser(String firstname, String lastname);
}
