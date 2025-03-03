package org.example.hellofx.handler.databasehandler;

import org.example.hellofx.controller.profilecontroller.Profile;
import org.springframework.stereotype.Component;

@Component
public class DefaultDatabaseHandler implements DataBaseHandler {
    public Profile getLoginState(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new Profile(0, "admin", "admin");
        }
        return null;
    }

    public void passwordChangeQuery(Profile profile, String newPassword) {
        System.out.println("Password of " + profile + " changed to " + newPassword);
    }
}
