package com.example.flowermanager;

import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FlowerDashboard {


        public static Scene createDashboardScene(ShoppingCart cart) {
            GridPane flowerGrid = new GridPane();
            flowerGrid.setStyle("-fx-background-color: #C1B5A4; -fx-padding: 10px;");
            flowerGrid.setHgap(10);
            flowerGrid.setVgap(10);


            flowerGrid.add(createFlowerView("Rose", "https://i.pinimg.com/originals/33/2b/df/332bdf3167b312f71227e52b0f27fd91.jpg", 30.0, cart), 0, 0);
            flowerGrid.add(createFlowerView("Pink Tulip", "https://i.pinimg.com/originals/58/cd/f4/58cdf476e26d8ac7163d24e7a8244a3e.jpg", 45.0, cart), 1, 0);
            flowerGrid.add(createFlowerView("Daisy", "https://i.pinimg.com/736x/5b/39/0e/5b390eaa3ef34dca11791a077b362088.jpg", 25.0, cart), 0, 1);
            flowerGrid.add(createFlowerView("Bellflower", "https://auntiedogmasgardenspot.files.wordpress.com/2013/06/canterburybells.jpg", 35, cart), 1, 1);

            return new Scene(flowerGrid, 700, 400);
        }

    private static GridPane createFlowerView(String flowerName, String imageUrl, double price, ShoppingCart cart) {
        GridPane flowerGrid = FlowerView.createFlowerView(flowerName, imageUrl, price, cart);

        return flowerGrid;
    }


    public static void showDashboard(Stage primaryStage, ShoppingCart cart) {
            primaryStage.setTitle("Flower Management Flower Dashboard");
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

