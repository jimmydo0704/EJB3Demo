/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat;

import javax.ejb.Local;

/**
 *
 * @author dvdat
 */
@Local
public interface AccessRightServiceLocal {
    boolean createAccessRight(String user);
}
