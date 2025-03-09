package org.example.hellofx.controller.defaultcontroller;

import org.example.hellofx.controller.HomeController;
import org.example.hellofx.controller.ProfileController;
import org.example.hellofx.ui.JavaFxApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultHomeController implements HomeController {
    @Autowired
    private ProfileController profileController;

    public void logoutButtonClicked() {
        profileController.logOutRequest();
    }

    public void passwordChangeButtonClicked() {
        assert profileController.isLoggedIn();
        JavaFxApplication.showPasswordChangeScene();
    }

}
