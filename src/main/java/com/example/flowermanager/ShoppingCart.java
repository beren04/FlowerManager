package com.example.flowermanager;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final List<Item> items;



    public ShoppingCart() {
        this.items = new ArrayList<>();

    }


    public void addItem(Item item) {
        // If the same item is in the cart , increase the quantity
        for (Item existingItem : items) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
                return;
            }
        }

        // if it's not add the item
        items.add(item);
    }

    public void displayCart() {
        if (items.isEmpty()) {
            showAlert("Empty Cart", "Your shopping cart is empty.");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Shopping Cart");
            alert.setHeaderText(" Your Cart");

            ListView<String> cartListView = createCartListView();

            // "Remove Item" button
            Button removeItemButton = new Button("Remove Item");
            removeItemButton.setOnAction(e -> removeItem(cartListView.getSelectionModel().getSelectedIndex()));

            // HBox to hold the ListView and Remove Item button
            HBox hbox = new HBox(cartListView, removeItemButton);

            alert.getDialogPane().setContent(hbox);

            // "Exit" button instead of "OK"
            ButtonType exitButton = new ButtonType("Exit", ButtonType.OK.getButtonData());
            alert.getButtonTypes().setAll(exitButton);

            // "Purchase" button
            ButtonType purchaseButton = new ButtonType("Purchase");
            alert.getButtonTypes().add(purchaseButton);

            // "Go Back" button instead of "Cancel"
            ButtonType goBackButton = new ButtonType("Go Back", ButtonType.CANCEL.getButtonData());
            alert.getButtonTypes().add(goBackButton);

            // "Add Note" button
            ButtonType addNoteButton = new ButtonType("Add Note");
            alert.getButtonTypes().add(addNoteButton);

            // Wait for user's action
            Optional<ButtonType> result = alert.showAndWait();

            // Handle the result
            if (result.isPresent()) {
                if (result.get() == purchaseButton) {
                    //If customer chooses to purchase
                    purchase();
                } else if (result.get() == addNoteButton) {
                    //If customer wants to add note
                    addNote();
                } else if (result.get() == exitButton) {
                    // If customer want to exit
                    clearCart(); // clearing the cart
                    Platform.exit(); // closing the program
                }
            }
        }
    }




    // removing items from the cart
    private void removeItem(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < items.size()) {
            Item selectedItem = items.get(selectedIndex);

            // If quantity equals 1, delete the item.
            if (selectedItem.getQuantity() == 1) {
                items.remove(selectedIndex);
            } else {
                // If the quantity is more than 1 decrease the quantity
                selectedItem.setQuantity(selectedItem.getQuantity() - 1);
            }

            displayCart(); // update the cart and show it.
        } else {
            showAlert("Invalid Selection", "Please select an item to remove.");
        }
    }


    private void addNote() {
        TextArea noteTextArea = new TextArea();
        noteTextArea.setPromptText("Enter note for all items...");

        Alert addNoteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        addNoteAlert.setTitle("Add Note");
        addNoteAlert.setHeaderText("Add note for all");
        addNoteAlert.getDialogPane().setContent(noteTextArea);

        Optional<ButtonType> addNoteResult = addNoteAlert.showAndWait();

        if (addNoteResult.isPresent() && addNoteResult.get() == ButtonType.OK) {
            String note = noteTextArea.getText();

            // Set the same note for all items in the cart
            for (Item item : items) {
                item.setNote(note);
            }

            // Show a confirmation alert for adding the note
            showAlert("Note Added", "Note has been added!!");

            // Display the shopping cart again after adding the note
            displayCart();
        }
    }


    private double calculateTotal() {
        double total = 0.0;

        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }

        return total;
    }


    private void purchase() {
        showAlert("Purchase Successful", "Thank you for your purchase!");

        clearCart();
        //closing the program
        Platform.exit();
    }


    public ListView<String> createCartListView() {
        ListView<String> cartListView = new ListView<>();
        for (Item item : items) {
            cartListView.getItems().add(item.getName() + " - " + item.getPrice()* item.quantity + " Lei - Quantity: " + item.getQuantity() + " - Note: " + item.getNote());
        }

        // add total price
        cartListView.getItems().add("Total: " + calculateTotal() + " Lei");

        return cartListView;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Change the default behavior of the OK button
        ButtonType exitButton = new ButtonType("Exit", ButtonType.OK.getButtonData());
        alert.getButtonTypes().setAll(exitButton);

        alert.showAndWait();
    }

    private void clearCart() {
        items.clear();
    }



    public static class Item {
        private final String name;
        private final double price;
        private final String imageUrl;
        private String note;

        private int quantity=1;

        public Item(String name, double price, String imageUrl, String note) {
            this.name = name;
            this.price = price;
            this.imageUrl = imageUrl;
            this.note = note;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getNote() {
            return note;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
