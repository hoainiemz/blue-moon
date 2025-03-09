package org.example.hellofx.ui.theme.defaulttheme;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.image.ImageView;
import org.example.hellofx.controller.LoginController;
import org.example.hellofx.ui.JavaFxApplication;
import org.example.hellofx.ui.theme.LoginScene;
import org.example.hellofx.ui.utils.Effects;
import org.example.hellofx.ui.utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultLoginSceneScene implements LoginScene {
    @Autowired
    private LoginController loginController;

    public Scene getLoginScene(){
        Scene scene = JavaFxApplication.getCurrentScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/hellofx/default-theme/fxml/login.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        }
        catch (IOException e) {
            return null;
        }
        scene.getStylesheets().add("/org/example/hellofx/default-theme/stylesheet/login.css");
        // The Frame
        VBox frame = (VBox) scene.lookup("#frame");
        frame.setMaxWidth(ScreenUtils.getScreenWidth() * 0.8);
        frame.setMaxHeight(ScreenUtils.getScreenHeight() * 0.5);
        HBox logoContainer = (HBox) scene.lookup("#logoContainer");
        logoContainer.setPrefHeight(frame.getMaxHeight() * 0.3);
        HBox mainContainer = (HBox) scene.lookup("#mainContainer");
        mainContainer.setPrefHeight(frame.getMaxHeight() * 0.7);
        frame.requestFocus();
        frame.setOnMouseClicked(event -> {
            frame.requestFocus(); // Move focus to the VBox
        });

        //The logo
        logoContainer.setAlignment(Pos.CENTER_LEFT);
        Text logoLabel = new Text("Hanoi University of Science and Technology");
        Image image = new Image("images/logo.png");
        ImageView logo = new ImageView(image);
        logo.setPreserveRatio(true);
        logo.setFitHeight(logoContainer.getPrefHeight() * 0.5);
        logoContainer.getChildren().addAll(logo, logoLabel);
        logoContainer.setPadding(new Insets(0, 50, 0, 50));
        logoContainer.setSpacing(10);
        // The SubFrame
        VBox subframe = (VBox) scene.lookup("#subframe");
        subframe.setPrefWidth(frame.getMaxWidth() * 0.6);
        subframe.setPadding(new Insets(20, 20, 50, 20));
        Text label1 = (Text) scene.lookup("#label1");
        label1.setText("Login");
        Text label2 = (Text) scene.lookup("#label2");
        label2.setText("with your BlueMoon Account to continue. This account will be available to use this application.");

        // The Login Form
        VBox loginForm = (VBox) scene.lookup("#loginForm");
        loginForm.setPrefWidth(frame.getMaxWidth() * 0.4);
        loginForm.setSpacing(20);
        loginForm.setPadding(new Insets(20, 50, 50, 50));
        TextField usernameField = (TextField) scene.lookup("#usernameField");
        usernameField.setPrefHeight(50);
        usernameField.setPromptText("Enter your username");
        VBox passwordFieldContainer = (VBox) scene.lookup("#passwordFieldContainer");
        passwordFieldContainer.setSpacing(10);
        PasswordField passwordField = (PasswordField) scene.lookup("#passwordField");
        passwordField.setPrefHeight(50);
        passwordField.setPromptText("Enter your password");
        HBox buttonContainer = (HBox) scene.lookup("#buttonContainer");
        Button logInButton = (Button) scene.lookup("#logInButton");
        Button signUpButton = (Button) scene.lookup("#signUpButton");
        logInButton.setText("Sign In");
        signUpButton.setText("Create Account");
        buttonContainer.setAlignment(Pos.BASELINE_LEFT);
        buttonContainer.setSpacing(30);
        TextFlow logInState = (TextFlow) scene.lookup("#logInState");
        Text loginFailednotification = (Text) scene.lookup("#loginFailednotification");
        loginFailednotification.setText("Invalid username or password");
//        logInState.getStyleClass().add("hidden");
        logInState.setVisible(false);
        logInButton.setOnAction(event -> {
            String response = loginController.loginButtonClicked(usernameField.getText(), passwordField.getText());
            if (response == null) {
                logInState.setVisible(true);
            }
        });
        signUpButton.setOnMouseClicked(event -> {
            loginController.signUpButtonClicked();
        });
        logInButton.setOnMouseEntered(event -> {
            logInButton.setEffect(Effects.glowEffect);
        });
        logInButton.setOnMouseExited(event -> {
            logInButton.setEffect(null);
        });
        signUpButton.setOnMouseEntered(event -> {
            signUpButton.setEffect(Effects.bloomEffect);
            signUpButton.setStyle("-fx-background-color: rgba(51,51,51,255);");
        });
        signUpButton.setOnMouseExited(event -> {
            signUpButton.setEffect(null);
            signUpButton.setStyle("-fx-background-color: transparent;");
        });
        frame.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                logInButton.fire();
            }
        });

        // Auto login
        usernameField.setText("admin");
        passwordField.setText("admin");

        return scene;
    }
}