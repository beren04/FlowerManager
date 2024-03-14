package com.example.flowermanager;

import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BouquetDashboard {

    private static Stage primaryStage;

    public static Scene createDashboardScene(ShoppingCart cart) {
        GridPane bouquetGrid = new GridPane();
        bouquetGrid.setStyle("-fx-background-color: #C1B5A4; -fx-padding: 10px;");
        bouquetGrid.setHgap(20);
        bouquetGrid.setVgap(10);

        // listing bouquet
        bouquetGrid.add(createBouquetView(" White Bouquet ", "https://i.pinimg.com/736x/75/e3/3c/75e33c473883e5afd66e2f687e3f0f8c.jpg", 30.0,cart),0,0);
        bouquetGrid.add(createBouquetView(" Pink-Purple Bouquet", "https://i.pinimg.com/736x/6f/32/31/6f3231e2f49df0ee223bb95538d30301.jpg", 55.0,cart), 1,0);
        bouquetGrid.add(createBouquetView(" Daisy Bouquet", "https://i.pinimg.com/736x/5b/39/0e/5b390eaa3ef34dca11791a077b362088.jpg", 25.0,cart), 0, 1);
        bouquetGrid.add(createBouquetView(" Pink Bouquet", "https://i.pinimg.com/736x/64/1e/82/641e824cddcda0f228f2c7ecb7139601--material-shades.jpg", 65,cart),1,1);

        return new Scene(bouquetGrid, 850, 550);
    }
    private static GridPane createBouquetView(String bouquetName, String imageUrl, double price, ShoppingCart cart) {
        GridPane bouquetView = BouquetView.createBouquetView(bouquetName, imageUrl, price,cart);

        return bouquetView;
    }

    public static void showDashboard(Stage primaryStage,ShoppingCart cart) {
        primaryStage.setTitle("Flower Management Bouquet Dashboard");
        Scene dashboardScene = createDashboardScene(cart);
        primaryStage.setScene(dashboardScene);
        primaryStage.show();

    }

    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}