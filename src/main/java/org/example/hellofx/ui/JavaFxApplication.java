package org.example.hellofx.ui;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hellofx.SpringBootFxApplication;
import org.example.hellofx.ui.theme.Theme;
import org.example.hellofx.ui.utils.ScreenUtils;

public class JavaFxApplication extends Application {
    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        currentStage.setTitle("Hello World");
        Theme theme = SpringBootFxApplication.context.getBean(Theme.class);
        showLoginScene();
        currentStage.setWidth(ScreenUtils.getScreenWidth());
        currentStage.setHeight(ScreenUtils.getScreenHeight());
        currentStage.show();
    }

    public static void showHomeScene(){
        Theme theme = SpringBootFxApplication.context.getBean(Theme.class);
        currentStage.setScene(theme.getHomeScene());
    }

    public static void showLoginScene(){
        Theme theme = SpringBootFxApplication.context.getBean(Theme.class);
        currentStage.setScene(theme.getLoginScene());
    }
}
