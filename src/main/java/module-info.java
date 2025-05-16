module com.project.project_oop_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.project_oop_java to javafx.fxml;
    opens com.project.project_oop_java.controller to javafx.fxml;
    exports com.project.project_oop_java;
    exports com.project.project_oop_java.controller;
}