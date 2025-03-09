package org.example.hellofx.controller;

import org.springframework.stereotype.Component;

@Component
public interface ProfileController {
    /**
     * user logged in succesfull, tell application to log in
     * @param profile
     */
    public void logInRequest(UserAccount profile);

    /**
     *
     * @return current username
     */
    public String getProfileNameRequest();

    /**
     * user logged out, get back to the login page
     */
    public void logOutRequest();

    /**
     * check if logged in or not
     */
    public boolean isLoggedIn();

    /**
     * user filled the password change form, send the request to the database
     * @return a string, state of the request
     */
    public String passwordChangeRequest(String currentPassword, String newPassword, String confirmPassword);
}
