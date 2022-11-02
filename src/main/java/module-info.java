module com.example.mediatrackerprojectparttwo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.fakemoncreator to javafx.fxml;
    exports com.example.fakemoncreator;
}