package org.example.hellofx.ui;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hellofx.SpringBootFxApplication;
import org.example.hellofx.ui.theme.HomeScene;
import org.example.hellofx.ui.theme.LoginScene;
import org.example.hellofx.ui.theme.PasswordChangeScene;
import org.example.hellofx.ui.utils.ScreenUtils;

public class JavaFxApplication extends Application {
    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static Scene getCurrentScene() {
        return currentStage.getScene();
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        currentStage.setTitle("Hello World");
        LoginScene theme = SpringBootFxApplication.context.getBean(LoginScene.class);
        showLoginScene();
        currentStage.setWidth(ScreenUtils.getScreenWidth());
        currentStage.setHeight(ScreenUtils.getScreenHeight());
        currentStage.show();
    }

    public static void showHomeScene(){
        HomeScene theme = SpringBootFxApplication.context.getBean(HomeScene.class);
        currentStage.setScene(theme.getHomeScene());
    }

    public static void showLoginScene(){
        LoginScene theme = SpringBootFxApplication.context.getBean(LoginScene.class);
        currentStage.setScene(theme.getLoginScene());
    }

    public static void showPasswordChangeScene(){
        PasswordChangeScene theme = SpringBootFxApplication.context.getBean(PasswordChangeScene.class);
        currentStage.setScene(theme.getPasswordChangeScene());
    }
}
