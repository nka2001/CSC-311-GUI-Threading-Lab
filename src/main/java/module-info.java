module com.mycompany.threadinglab {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.threadinglab to javafx.fxml;
    exports com.mycompany.threadinglab;
}
