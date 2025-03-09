package org.example.hellofx.handler;

import org.example.hellofx.controller.UserAccount;


public interface DataBaseHandler {

    /**
     * getLoginState: User login and DataBaseHandler check if the information mathch or not, return a Profile instance
     * @param username
     * @param password
     * @return a profile of the matched account
     */
    public UserAccount getLoginState(String username, String password);

    /**
     * change the profile's password
     * @param profile
     * @param newPassword
     */
    public void passwordChangeQuery(UserAccount profile, String newPassword);
}
