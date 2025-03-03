package org.example.hellofx.controller.profilecontroller;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ProfileController {
    /**
     * user logged in succesfull, tell application to log in
     * @param profile
     */
    public void logIn(Profile profile);

    /**
     *
     * @return current username
     */
    public String getProfileName();

    /**
     * user logged out, get back to the login page
     */
    public void logOut();

    /**
     * user want to change the password, help him
     */
    public void changePassword();
}
