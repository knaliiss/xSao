module dev.knalis.xsao {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires static lombok;
    requires com.github.kwhat.jnativehook;
    requires com.sun.jna.platform;
    requires com.sun.jna;

    opens dev.knalis.xsao to javafx.fxml;
    opens dev.knalis.xsao.controllers to javafx.fxml; // <-- Добавлено для корректной работы FXML

    exports dev.knalis.xsao;
}
