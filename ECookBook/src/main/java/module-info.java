module com.example.favorifilmlerim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ecookbook to javafx.fxml;
    exports com.example.ecookbook;
}