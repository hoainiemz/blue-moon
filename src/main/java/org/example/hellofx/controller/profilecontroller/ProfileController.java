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

    public String getProfileName();
}
