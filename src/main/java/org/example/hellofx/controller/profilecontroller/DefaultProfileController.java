package org.example.hellofx.controller.profilecontroller;

import org.example.hellofx.ui.JavaFxApplication;
import org.springframework.stereotype.Component;

@Component
public class DefaultProfileController implements ProfileController {
    private Profile profile;

    public void logIn (Profile profile) {
        this.profile = profile;
        System.out.println("Logged in with profile: " + profile);
        JavaFxApplication.showHomeScene();
    }

    public String getProfileName() {
        if (profile != null) {
            return profile.username();
        }
        return null;
    }

    public void logOut() {
        this.profile = null;
        System.out.println("Logged out succesfully!");
        JavaFxApplication.showLoginScene();
    }

    public void changePassword() {
        assert profile != null;
        JavaFxApplication.showLoginScene();
    }
}
