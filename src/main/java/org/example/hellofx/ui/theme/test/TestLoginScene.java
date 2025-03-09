package org.example.hellofx.ui.theme.test;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.hellofx.controller.LoginController;
import org.example.hellofx.ui.JavaFxApplication;
import org.example.hellofx.ui.theme.LoginScene;
import org.example.hellofx.ui.utils.Effects;
import org.example.hellofx.ui.utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

interface MyFun {
    public HBox func(String a, String b, boolean c);
};

@Primary
@Component
public class TestLoginScene implements LoginScene {
    @Autowired
    private LoginController loginController;

    @Override
    public Scene getLoginScene(){

        Scene scene = JavaFxApplication.getCurrentScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/themes/test/login/login.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        }
        catch (IOException e) {
            return null;
        }
        scene.getStylesheets().add("/themes/test/login/login.css");
        VBox leftFrame = (VBox) scene.lookup("#leftFrame");
        VBox rightFrame = (VBox) scene.lookup("#rightFrame");
        leftFrame.setPrefWidth(ScreenUtils.getScreenWidth() * 0.3);
        leftFrame.setAlignment(Pos.TOP_CENTER);
        rightFrame.setPrefWidth(ScreenUtils.getScreenWidth() * 0.7);

        HBox huster = (HBox) scene.lookup("#huster");
        VBox bluemoon = (VBox) scene.lookup("#bluemoon");
        VBox loginForm = (VBox) scene.lookup("#loginForm");
        VBox statusContainer = (VBox) scene.lookup("#statusContainer");
        HBox buttonContainer = (HBox) scene.lookup("#buttonContainer");
        huster.setPrefHeight(ScreenUtils.getScreenHeight() * 0.1);
        bluemoon.setPrefHeight(ScreenUtils.getScreenHeight() * 0.3);
//        loginForm.setPrefHeight(ScreenUtils.getScreenHeight() * 0.4);
        buttonContainer.setPrefHeight(ScreenUtils.getScreenHeight() * 0.1);
//        leftFrame.setSpacing(10);
        leftFrame.setOnMouseClicked(event -> {
            leftFrame.requestFocus(); // Move focus to the VBox
        });
        rightFrame.setOnMouseClicked(mouseEvent -> {
            rightFrame.requestFocus();
        });
        rightFrame.requestFocus();

        Text logoLabel = new Text("Hanoi University of Science and Technology");
        Image image = new Image("images/logo.png");
        ImageView logo = new ImageView(image);
        logo.setPreserveRatio(true);
        logo.setFitHeight(huster.getPrefHeight() * 0.6);
//        huster.getChildren().addAll(logo, logoLabel);
        huster.setPadding(new Insets(0, 50, 0, 50));
        huster.setSpacing(10);
        huster.setAlignment(Pos.CENTER_LEFT);

        Text bluemoonLabel = new Text("Management Service");
        Image bluermoonLogoImage = new Image("images/blue-moon-cropped-logo.png");
        ImageView bluemoonLogo = new ImageView(bluermoonLogoImage);
        bluemoonLogo.setPreserveRatio(true);
        bluemoonLogo.setFitHeight(bluemoon.getPrefHeight() * 0.6);
        bluemoon.getChildren().addAll(bluemoonLogo, bluemoonLabel);
        bluemoon.setAlignment(Pos.TOP_CENTER);

        MyFun iconTextFieldMaker = new MyFun() {
            public HBox func(String imgUrl, String Prompt, boolean password) {
                HBox textFieldContainer = new HBox();
                textFieldContainer.getStyleClass().add("login-text-field");
                textFieldContainer.setPrefHeight(loginForm.getPrefHeight() * 0.15);
                textFieldContainer.setAlignment(Pos.CENTER);
                VBox.setMargin(textFieldContainer, new Insets(0, leftFrame.getPrefWidth() * 0.2, 0, leftFrame.getPrefWidth() * 0.2));
                textFieldContainer.setPadding(new Insets(5, 10, 5, 10));
                Image img = new Image(imgUrl);
                ImageView icon = new ImageView(img);
                icon.setPreserveRatio(true);
                icon.setFitHeight(textFieldContainer.getPrefHeight() * 0.6);
                textFieldContainer.getChildren().addAll(icon);
                if (!password) {
                    TextField field = new TextField();
                    field.setPromptText(Prompt);
                    field.setMinWidth(leftFrame.getPrefWidth() * 0.8);
                    field.setPrefWidth(leftFrame.getPrefWidth() * 0.8);
                    field.setMaxWidth(leftFrame.getPrefWidth() * 0.8);
                    textFieldContainer.getChildren().addAll(field);
                }
                else {
                    PasswordField field = new PasswordField();
                    field.setPromptText(Prompt);
                    field.setMinWidth(leftFrame.getPrefWidth() * 0.8);
                    field.setPrefWidth(leftFrame.getPrefWidth() * 0.8);
                    field.setMaxWidth(leftFrame.getPrefWidth() * 0.8);
                    textFieldContainer.getChildren().addAll(field);
                }
                return textFieldContainer;
            }
        };
        loginForm.setSpacing(20);
        loginForm.getChildren().add(iconTextFieldMaker.func("images/user-icon-grey.png", "Enter your username", false));
        loginForm.getChildren().add(iconTextFieldMaker.func("images/password-icon.png", "Enter your password", true));

        Text status = new Text("Invalid username or password!");
        status.setVisible(false);
        statusContainer.getChildren().add(status);
        VBox.setMargin(statusContainer, new Insets(0, leftFrame.getPrefWidth() * 0.2, 0, leftFrame.getPrefWidth() * 0.2));

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-button");
        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("register-button");
        buttonContainer.getChildren().addAll(loginButton, registerButton);
        VBox.setMargin(buttonContainer, new Insets(0, leftFrame.getPrefWidth() * 0.2, 0, leftFrame.getPrefWidth() * 0.2));
        loginButton.setPrefWidth(leftFrame.getPrefWidth() * 0.4);
        registerButton.setPrefWidth(leftFrame.getPrefWidth() * 0.4);
        registerButton.setPrefHeight(buttonContainer.getPrefHeight() * 0.5);
        buttonContainer.setSpacing(20);
//        VBox.setMargin();
//        HBox.setMargin(buttonContainer, new Insets(200, 0, 0, 0));
//        leftFrame.setSpacing(20);

        loginButton.setOnAction(event -> {
            String response = loginController.loginButtonClicked(((TextField)loginForm.getChildren().get(0)).getText(), ((PasswordField)loginForm.getChildren().get(1)).getText());
            if (response == null) {
                status.setVisible(true);
            }
        });
        registerButton.setOnMouseClicked(event -> {
            loginController.signUpButtonClicked();
        });
        registerButton.setOnMouseEntered(event -> {
            registerButton.setEffect(Effects.glowEffect);
        });
        registerButton.setOnMouseExited(event -> {
            registerButton.setEffect(null);
        });
        return scene;
    }
}

