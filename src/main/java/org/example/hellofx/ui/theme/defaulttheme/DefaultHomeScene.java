package org.example.hellofx.ui.theme.defaulttheme;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import org.example.hellofx.controller.HomeController;
import org.example.hellofx.controller.ProfileController;
import org.example.hellofx.ui.theme.HomeScene;
import org.example.hellofx.ui.utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class DefaultHomeScene implements HomeScene {
    @Autowired
    HomeController homeController;
    @Autowired
    ProfileController profileController;

    private List menuButtonList;

    private void resetMenuBar(Scene scene) {
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
//        final Scene scene = JavaFxApplication.getCurrentScene();
        final Scene scene;
        menuButtonList = new ArrayList<RadioButton>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/themes/default-theme/fxml/home.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        }
        catch (IOException exception) {
            return null;
        }
        scene.getStylesheets().add("/themes/default-theme/stylesheet/home.css");

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
        Text welcome = new Text("Chào mừng bạn, " + profileController.getProfileNameRequest());
        Image userIcon = new Image("images/user-icon.jpg");
        ImageView userImg = new ImageView(userIcon);
        userImg.setFitHeight(topBar.getPrefHeight() * 0.5);
        userImg.setFitWidth(userImg.getFitHeight());
        profileContainer.getChildren().addAll(userImg, welcome);
        topProfile.setSpacing(20);
        profileContainer.setSpacing(20);
        profileContainer.setPadding(new Insets(0, 40, 0, 40));
        VBox profileDropDownContent = new VBox();
        profileDropDownContent.getStyleClass().add("profile-drop-down");
        profileDropDownContent.setAlignment(Pos.CENTER);
        profileDropDownContent.setPadding(new Insets(10, 0, 10, 0));
        profileDropDownContent.getChildren().addAll(
                new TextFlow(new Text(profileController.getProfileNameRequest())),
                new TextFlow(new Text("Đổi mật khẩu")),
                new TextFlow(new Text("Đăng xuất"))
        );

        Popup popup = new Popup();
//        popup.setAutoHide(true);
        popup.getContent().add(profileDropDownContent);
        popup.setWidth(profileContainer.getPrefWidth());
        profileDropDownContent.setPrefWidth(profileContainer.getPrefWidth());
        profileContainer.setOnMouseClicked(event -> {
            if (popup.isShowing()) {
                popup.hide();
            }
            else {
                double x = profileContainer.localToScreen(profileContainer.getBoundsInLocal()).getMinX();
                double y = profileContainer.localToScreen(profileContainer.getBoundsInLocal()).getMaxY() - 5;
                popup.show(scene.getWindow(), x, y);
            }
        });
        profileDropDownContent.getChildren().get(2).setOnMouseClicked(event -> {
            popup.hide();
            homeController.logoutButtonClicked();
        });
        profileDropDownContent.getChildren().get(1).setOnMouseClicked(event -> {
            popup.hide();
            homeController.passwordChangeButtonClicked();
        });
        profileDropDownContent.setPrefWidth(ScreenUtils.getScreenWidth() * 0.2);
//        System.out.println(profileContainer.getLayoutBounds().getWidth());

        // container
        VBox menuContainer = (VBox) scene.lookup("#menuContainer");
        VBox content = (VBox) scene.lookup("#content");
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
        doiMatKhau.setOnAction(event -> {
            homeController.passwordChangeButtonClicked();
        });
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
                resetMenuBar(scene);
            });
            curr.setPrefWidth(menuContainer.getPrefWidth());
            for (int j = 0; j < curr.getChildren().size(); j++) {
                Button curButton = (Button) curr.getChildren().get(j);
                curButton.setPrefWidth(curr.getPrefWidth());
            }
        }
        RadioButton tmp = (RadioButton) menuButtonList.get(0);
        tmp.setSelected(true);
        resetMenuBar(scene);

//        menuContainer.setSpacing(10);
        return scene;
    }
}
