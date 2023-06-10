module com.example.netgame2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.netgame2 to javafx.fxml;
    exports com.example.netgame2;
    exports com.example.netgame2.packets;
    opens com.example.netgame2.packets to javafx.fxml;
}