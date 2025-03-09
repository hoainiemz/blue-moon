package org.example.hellofx.controller.defaultcontroller;

import org.example.hellofx.controller.ProfileController;
import org.example.hellofx.controller.UserAccount;
import org.example.hellofx.handler.DataBaseHandler;
import org.example.hellofx.ui.JavaFxApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultProfileController implements ProfileController {
    @Autowired
    private DataBaseHandler dataBaseHandler;
    private UserAccount profile;

//    public DefaultProfileController(DataBaseHandler dataBaseHandler) {
//        this.dataBaseHandler = dataBaseHandler;
//    }

    public void logInRequest (UserAccount profile) {
        this.profile = profile;
        System.out.println("Logged in with profile: " + profile);
        JavaFxApplication.showHomeScene();
    }

    public String getProfileNameRequest() {
        if (profile != null) {
            return profile.username();
        }
        return null;
    }

    public void logOutRequest() {
        this.profile = null;
        System.out.println("Logged out succesfully!");
        JavaFxApplication.showLoginScene();
    }

    public String passwordChangeRequest(String currentPassword, String newPassword, String confirmPassword) {
        assert isLoggedIn() == true;
        if (newPassword.equals(confirmPassword) == false) {
            return "New password do not match!";
        }
        if (getCurrentPassword().equals(currentPassword) == false) {
            return "Current password do not match!";
        }
        dataBaseHandler.passwordChangeQuery(profile, newPassword);
        return "Password changed successfully!";
    }

    public boolean isLoggedIn() {
        return profile != null;
    }

    private String getCurrentPassword() {
        return profile.password();
    }
}
