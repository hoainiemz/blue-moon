package org.example.hellofx.controller.profilecontroller;

import org.example.hellofx.ui.JavaFxApplication;
import org.springframework.stereotype.Component;

@Component
public class DefaultProfileController implements ProfileController {
    private Profile profile;
    public void logIn (Profile profile) {
        this.profile = profile;
        System.out.println("Logged in with profile: " + profile);
        JavaFxApplication.showHome();
    }
    public String getProfileName() {
        return profile.username();
    }
}
