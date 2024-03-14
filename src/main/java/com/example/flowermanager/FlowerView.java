package com.example.flowermanager;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class FlowerView {

    public static GridPane createFlowerView(String flowerName, String imageUrl, double price, ShoppingCart cart) {
        GridPane flowerGrid = new GridPane();
        flowerGrid.setHgap(10);
        flowerGrid.setVgap(10);
        flowerGrid.setPadding(new Insets(10));

        //for adding photos
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label flowerLabel = new Label("Flower: " + flowerName + "\n" + "Price: " + price + " Lei" );
        flowerLabel.setGraphic(imageView);

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e -> {
            // adding flowers to the cart
            cart.addItem(new ShoppingCart.Item(flowerName,price,imageUrl,"-"));
            FlowerDashboard.showAlert("Successfully", flowerName + " added to the cart!");
        });

        Button goToCart = new Button("Go to Cart");
        goToCart.setOnAction(actionEvent -> cart.displayCart());


        flowerGrid.add(flowerLabel, 0, 0);
        flowerGrid.add(addToCartButton, 0, 1);
        flowerGrid.add(goToCart, 1, 1);

        return flowerGrid;
    }

}
