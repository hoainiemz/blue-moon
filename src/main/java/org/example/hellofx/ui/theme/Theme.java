package org.example.hellofx.ui.theme;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface Theme {
    public Scene getLoginScene();
    public Scene getHomeScene();
    public void show();
}
