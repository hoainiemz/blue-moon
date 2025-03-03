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
    public void show();
}
