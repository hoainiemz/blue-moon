package org.example.hellofx.controller.defaultcontroller;

import org.example.hellofx.controller.LoginController;
import org.example.hellofx.controller.UserAccount;
import org.example.hellofx.controller.ProfileController;
import org.example.hellofx.handler.DataBaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultLoginController implements LoginController {
    @Autowired
    DataBaseHandler dataBaseHandler;
    @Autowired
    ProfileController profileController;

    public String loginButtonClicked(String username, String password) {
        UserAccount response = dataBaseHandler.getLoginState(username, password);
        if (response != null) {
            profileController.logInRequest(response);
        }
        return response != null ? response.toString() : null;
    }

    public void signUpButtonClicked() {
        System.out.println("Sign up button clicked");
    }
}
