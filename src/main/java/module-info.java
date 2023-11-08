module caiofurlan.serverdistributedsystems {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires jjwt.api;
    requires org.apache.commons.codec;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires jbcrypt;

    opens caiofurlan.serverdistributedsystems to javafx.fxml;
    opens caiofurlan.serverdistributedsystems.controllers to javafx.fxml;
    exports caiofurlan.serverdistributedsystems;
    exports caiofurlan.serverdistributedsystems.controllers;
    exports caiofurlan.serverdistributedsystems.models;
    exports caiofurlan.serverdistributedsystems.system;
    exports caiofurlan.serverdistributedsystems.system.utilities;
    exports caiofurlan.serverdistributedsystems.views;
    exports caiofurlan.serverdistributedsystems.system.connection;
    exports caiofurlan.serverdistributedsystems.system.connection.send;
}