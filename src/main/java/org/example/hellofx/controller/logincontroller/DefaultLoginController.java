package org.example.hellofx.controller.logincontroller;

import org.example.hellofx.controller.profilecontroller.Profile;
import org.example.hellofx.controller.profilecontroller.ProfileController;
import org.example.hellofx.handler.databasehandler.DataBaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultLoginController implements LoginController {
    @Autowired
    DataBaseHandler dataBaseHandler;
    @Autowired
    ProfileController profileController;

    public String loginButtonClickedRequest(String username, String password) {
        Profile response = dataBaseHandler.getLoginState(username, password);
        if (response != null) {
            profileController.logInRequest(response);
        }
        return response != null ? response.toString() : null;
    }

    public void signUpButtonClickedRequest() {
        System.out.println("Sign up button clicked");
    }
}
