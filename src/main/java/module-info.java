module com.example.projectone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectone to javafx.fxml;
    exports com.example.projectone;
}