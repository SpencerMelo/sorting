module com.ds.sorting {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ds.sorting to javafx.fxml;
    exports com.ds.sorting;
}
