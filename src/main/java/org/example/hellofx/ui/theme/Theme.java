package org.example.hellofx.ui.theme;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface Theme {
    /**
     * @return the login scene of your application
     */
    public Scene getLoginScene();

    /**
     * @return the home scene of your application
     */
    public Scene getHomeScene();

    /**
     * @return the password change scene of your application
     */
    public Scene getPasswordChangeScene();
    public void show();
}
