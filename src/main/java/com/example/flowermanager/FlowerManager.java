package com.example.flowermanager;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
    public class FlowerManager extends Application {

        private Stage primaryStage;



        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

            this.primaryStage = primaryStage;

            showLoginScreen();
        }

        private void showLoginScreen() {
            Label usernameLabel = new Label("Username:");
            TextField usernameField = new TextField();

            Label passwordLabel = new Label("Password:");
            PasswordField passwordField = new PasswordField();

            Button loginButton = new Button("Login");
            loginButton.setOnAction(e -> {
                if (authenticate(usernameField.getText(), passwordField.getText())) {
                    showSelectionScreen();
                } else {
                    showAlert("Invalid credentials", "Please enter valid username and password.");
                }
            });

            // login when press enter
            usernameField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    passwordField.requestFocus();
                }
            });

            passwordField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    if (authenticate(usernameField.getText(), passwordField.getText())) {
                        showSelectionScreen();
                    } else {
                        showAlert("Invalid credentials", "Please enter valid username and password.");
                    }
                }
            });

            VBox loginLayout = new VBox(30);
            loginLayout.setPadding(new Insets(30));
            loginLayout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

            Scene loginScene = new Scene(loginLayout, 300, 300);
            primaryStage.setTitle("Login");
            primaryStage.setScene(loginScene);
            primaryStage.show();
        }


        private void showSelectionScreen() {
            Label selectionLabel = new Label("Select an option:");

            ToggleGroup toggleGroup = new ToggleGroup();

            RadioButton flowerRadioButton = new RadioButton("Flower");
            flowerRadioButton.setToggleGroup(toggleGroup);

            RadioButton bouquetRadioButton = new RadioButton("Bouquet");
            bouquetRadioButton.setToggleGroup(toggleGroup);

            Button selectButton = new Button("Select");
            selectButton.setOnAction(e -> {
                if (flowerRadioButton.isSelected()) {
                    showFlowerDashboard();
                } else if (bouquetRadioButton.isSelected()) {
                    showBouquetDashboard();
                } else {
                    showAlert("Selection Error", "Please choose either Flower or Bouquet.");
                }
            });

            VBox selectionLayout = new VBox(10);
            selectionLayout.setPadding(new Insets(20));
            selectionLayout.getChildren().addAll(selectionLabel, flowerRadioButton, bouquetRadioButton, selectButton);

            Scene selectionScene = new Scene(selectionLayout, 300, 200);
            primaryStage.setTitle("Selection");
            primaryStage.setScene(selectionScene);
            primaryStage.show();
        }
        private void showFlowerDashboard() {
            FlowerDashboard.showDashboard(primaryStage,new ShoppingCart());
        }

        private void showBouquetDashboard() {
            BouquetDashboard.showDashboard(primaryStage,new ShoppingCart());
        }

        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }

        private boolean authenticate(String username, String password) {
            // checking the system login information if the entered value correct
            return "beros".equals(username) && "030904".equals(password);
        }
    }



