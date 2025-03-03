package org.example.hellofx.controller.profilecontroller;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ProfileController {
    /**
     * user logged in succesfull, tell application to log in
     * @param profile
     */
    public void logInRequest(Profile profile);

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
     * user want to change the password, help him
     */
    public void passwordChangeClickButtonRequest();

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
