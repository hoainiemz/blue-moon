package org.example.hellofx.handler.databasehandler;

import org.example.hellofx.controller.profilecontroller.Profile;


public interface DataBaseHandler {

    /**
     * getLoginState: User login and DataBaseHandler check if the information mathch or not, return a Profile instance
     * @param username
     * @param password
     * @return a profile of the matched account
     */
    public Profile getLoginState(String username, String password);
}
