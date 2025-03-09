package org.example.hellofx.handler.databasehandler;

import org.example.hellofx.controller.UserAccount;
import org.example.hellofx.handler.DataBaseHandler;
import org.springframework.stereotype.Component;

@Component
public class DefaultDatabaseHandler implements DataBaseHandler {
    public UserAccount getLoginState(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new UserAccount(0, "admin", "admin");
        }
        return null;
    }

    public void passwordChangeQuery(UserAccount profile, String newPassword) {
        System.out.println("Password of " + profile + " changed to " + newPassword);
    }
}
