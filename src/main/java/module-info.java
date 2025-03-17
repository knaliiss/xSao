module dev.knalis.xsao {
    // JavaFX Core
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    // Ensure JavaFX dependencies are correctly added to the module path or build system
    requires javafx.media;

    // 3rd Party
    requires com.github.kwhat.jnativehook;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires static lombok;
    requires com.google.gson;
    requires java.logging;

    // Opens
    opens dev.knalis.xsao to javafx.fxml;
    opens dev.knalis.xsao.utils.impl to com.google.gson;
    opens dev.knalis.xsao.controllers to javafx.fxml;
    opens dev.knalis.xsao.model to com.google.gson;
    opens dev.knalis.xsao.utils to com.google.gson;

    // Exports
    exports dev.knalis.xsao;
}