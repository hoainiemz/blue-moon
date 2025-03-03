package org.example.hellofx.ui.theme;

import com.sun.source.tree.Tree;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.image.ImageView;
import org.example.hellofx.controller.logincontroller.LoginController;
import org.example.hellofx.controller.profilecontroller.Profile;
import org.example.hellofx.controller.profilecontroller.ProfileController;
import org.example.hellofx.ui.utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultTheme implements Theme {
    @Autowired
    private LoginController loginController;
    @Autowired
    private ProfileController profileController;

    private Glow glowEffect;
    private Bloom bloomEffect;

    private List menuButtonList;
    private Scene scene;

    public Scene getLoginScene(){
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
        glowEffect = new Glow(0.5);
        bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.5);
        logInButton.setOnMouseEntered(event -> {
            logInButton.setEffect(glowEffect);
        });
        logInButton.setOnMouseExited(event -> {
            logInButton.setEffect(null);
        });
        signUpButton.setOnMouseEntered(event -> {
            signUpButton.setEffect(bloomEffect);
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

    private void resetMenuBar() {
        VBox menuContainer = (VBox) scene.lookup("#menuContainer");
        for (int i = 0; i < menuContainer.getChildren().size(); i++) {
            VBox cur = (VBox) menuContainer.getChildren().get(i);
            RadioButton radioButton = (RadioButton) menuButtonList.get(i);
            for (int j = 1; j < cur.getChildren().size(); j++) {
                Button tmp = (Button) cur.getChildren().get(j);
                tmp.setVisible(radioButton.isSelected());
                tmp.setManaged(radioButton.isSelected());
            }
            Button tmp = (Button) cur.getChildren().get(0);
            tmp.getStyleClass().remove("selected-main-menu");
            if (radioButton.isSelected()) {
                tmp.getStyleClass().add("selected-main-menu");
            }
        }
    }

    public Scene getHomeScene() {
        menuButtonList = new ArrayList<RadioButton>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/hellofx/default-theme/fxml/home.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        }
        catch (IOException exception) {
            return null;
        }
        scene.getStylesheets().add("/org/example/hellofx/default-theme/stylesheet/home.css");

        HBox topBar = (HBox) scene.lookup("#topBar");
        HBox container = (HBox) scene.lookup("#container");
        topBar.setPrefHeight(ScreenUtils.getScreenHeight() * 0.1);
        container.setPrefHeight(ScreenUtils.getScreenHeight() * 0.9);

        // top bar
        topBar.requestFocus();
        HBox topMenu = (HBox) scene.lookup("#topMenu");
        HBox topProfile = (HBox) scene.lookup("#topProfile");
        topMenu.setPrefWidth(ScreenUtils.getScreenWidth() * 0.6);
        topProfile.setPrefWidth(ScreenUtils.getScreenWidth() * 0.4);
        topBar.setOnMouseClicked(event -> {
            topBar.requestFocus(); // Move focus to the VBox
        });

        //top menu
        HBox logoContainer = (HBox) scene.lookup("#logoContainer");
        topMenu.setAlignment(Pos.CENTER_LEFT);
        Text logoLabel = new Text("Blue Moon Resident Management Application");
        Image image = new Image("images/blue-moon-logo.png");
        ImageView logo = new ImageView(image);
        logo.setPreserveRatio(true);
        logo.setFitHeight(topBar.getPrefHeight() * 0.8);
        logoContainer.getChildren().addAll(logo, logoLabel);
        topMenu.setSpacing(20);
        logoContainer.setSpacing(10);
        logoContainer.setPadding(new Insets(0, 40, 0, 40));
        logoContainer.setAlignment(Pos.CENTER_LEFT);
        // top profile
        HBox profileContainer = (HBox) scene.lookup("#profileContainer");
        topProfile.setAlignment(Pos.CENTER_RIGHT);
        profileContainer.setAlignment(Pos.CENTER_RIGHT);
        Text welcome = new Text("Chào mừng bạn, " + profileController.getProfileName());
        Image userIcon = new Image("images/user-icon.jpg");
        ImageView userImg = new ImageView(userIcon);
        userImg.setFitHeight(topBar.getPrefHeight() * 0.5);
        userImg.setFitWidth(userImg.getFitHeight());
        profileContainer.getChildren().addAll(userImg, welcome);
        topProfile.setSpacing(20);
        profileContainer.setSpacing(20);
        profileContainer.setPadding(new Insets(0, 40, 0, 40));

        // container
        VBox menuContainer = (VBox) scene.lookup("#menuContainer");
        Region content = (Region) scene.lookup("#content");
        menuContainer.setPrefWidth(ScreenUtils.getScreenWidth() * 0.1);
        content.setPrefWidth(ScreenUtils.getScreenWidth() * 0.9);
        content.setOnMouseClicked(event -> {
            content.requestFocus(); // Move focus to the VBox
        });
        menuContainer.setOnMouseClicked(event -> {
            menuContainer.requestFocus(); // Move focus to the VBox
        });
        // left menu bar

        // dashBoard
        VBox dashBoardContainer = new VBox();
        Button dashBoard = new Button("Dashboard");
        dashBoard.getStyleClass().add("menu-main-button");
        dashBoardContainer.getChildren().addAll(dashBoard);
        // dan cu
        VBox danCuContainer = new VBox();
        Button danCu = new Button("Dân cư");
        danCu.getStyleClass().add("menu-main-button");
        Button nhom = new Button("Nhóm dân cư");
        nhom.getStyleClass().add("menu-sub-button");
        Button donDangKyThamGia = new Button("Thêm dân cư");
        donDangKyThamGia.getStyleClass().add("menu-sub-button");
        danCuContainer.getChildren().addAll(danCu, nhom, donDangKyThamGia);
        // khoan thu
        VBox khoanThuContainer = new VBox();
        Button khoanThu = new Button("Khoản thu");
        khoanThu.getStyleClass().add("menu-main-button");
        Button danhSachKhoanThu = new Button("Các khoản thu");
        danhSachKhoanThu.getStyleClass().add("menu-sub-button");
        Button taoKhoanThu = new Button("Tạo khoản thu");
        taoKhoanThu.getStyleClass().add("menu-sub-button");
        khoanThuContainer.getChildren().addAll(khoanThu, danhSachKhoanThu, taoKhoanThu);
        // my profile
        VBox myProfileContainer = new VBox();
        Button myProfile = new Button("My Profile");
        myProfile.getStyleClass().add("menu-main-button");
        Button thongTinCaNhan = new Button("Thông tin cá nhân");
        thongTinCaNhan.getStyleClass().add("menu-sub-button");
        Button doiMatKhau = new Button("Đổi mật khẩu");
        doiMatKhau.getStyleClass().add("menu-sub-button");
        myProfileContainer.getChildren().addAll(myProfile, thongTinCaNhan, doiMatKhau);

        ToggleGroup menuToggleGroup = new ToggleGroup();

        menuContainer.getChildren().addAll(dashBoardContainer, danCuContainer, khoanThuContainer, myProfileContainer);
        for (int i = 0; i < menuContainer.getChildren().size(); i++) {
            RadioButton tmp = new RadioButton();
            tmp.setToggleGroup(menuToggleGroup);
            menuButtonList.add(tmp);
            VBox curr = (VBox) menuContainer.getChildren().get(i);
            Button cur = (Button) curr.getChildrenUnmodifiable().get(0);
            cur.setOnMouseClicked(event -> {
                Node tgt = (Node) event.getSource();
                tgt = tgt.getParent();
                int idx = menuContainer.getChildren().indexOf(tgt);
                RadioButton radio = (RadioButton) menuButtonList.get(idx);
                radio.setSelected(true);
                resetMenuBar();
            });
            curr.setPrefWidth(menuContainer.getPrefWidth());
            for (int j = 0; j < curr.getChildren().size(); j++) {
                Button curButton = (Button) curr.getChildren().get(j);
                curButton.setPrefWidth(curr.getPrefWidth());
            }
        }
        RadioButton tmp = (RadioButton) menuButtonList.get(0);
        tmp.setSelected(true);
        resetMenuBar();

//        menuContainer.setSpacing(10);
        return scene;
    }

    public void show() {
        System.out.println("this is default login stage");
    }
}