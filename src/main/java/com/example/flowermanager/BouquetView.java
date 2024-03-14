package com.example.flowermanager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class BouquetView {

        public static GridPane createBouquetView(String bouquetName, String imageUrl, double price, ShoppingCart cart) {
                GridPane bouquetGrid = new GridPane();
                bouquetGrid.setHgap(10);
                bouquetGrid.setVgap(10);
                bouquetGrid.setPadding(new Insets(10));

                //for adding photos
                Image image = new Image(imageUrl);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Label bouquetLabel = new Label("Bouquet: " + bouquetName + "\n" + "Price: " + price + " Lei");
                bouquetLabel.setGraphic(imageView);

                Button addToCartButton = new Button("Add to Cart");
                addToCartButton.setOnAction(e -> {
                        //adding to cart
                        cart.addItem(new ShoppingCart.Item(bouquetName, price, imageUrl, null));
                        BouquetDashboard.showAlert("Successfully", bouquetName + " added to the cart!");
                });

                Button goToCart = new Button("Go to Cart");
                goToCart.setOnAction(actionEvent -> cart.displayCart());


               bouquetGrid.add(bouquetLabel,0,0);
               bouquetGrid.add(addToCartButton,0,1);
               bouquetGrid.add(goToCart,1,1);

               return bouquetGrid;
        }
}
