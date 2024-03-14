module com.example.flowermanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flowermanager to javafx.fxml;
    exports com.example.flowermanager;
}