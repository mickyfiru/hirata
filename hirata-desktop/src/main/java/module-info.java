module com.hirata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.hirata to javafx.fxml;
    opens com.hirata.controller to javafx.fxml;
    opens com.hirata.model to javafx.base;

    exports com.hirata;
    exports com.hirata.controller;
    exports com.hirata.model;
}
