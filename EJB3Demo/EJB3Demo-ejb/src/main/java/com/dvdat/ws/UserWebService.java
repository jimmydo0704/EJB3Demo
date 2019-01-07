/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdat.ws;

import com.dvdat.UserServiceLocal;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author dvdat
 */
@WebService(serviceName = "UserWebService")
@Stateless()
public class UserWebService {

    @EJB
    private UserServiceLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "deleteUser")
    public boolean deleteUser(@WebParam(name = "name") String name) {
        return ejbRef.deleteUser(name);
    }
    
}
