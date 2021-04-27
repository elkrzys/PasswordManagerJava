module pl.lipiec.passwordmanagerfx {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens pl.lipiec.Controllers to javafx.fxml;
    opens pl.lipiec.Models to javafx.fxml;
    exports pl.lipiec.passwordmanagerfx;
    exports pl.lipiec.Controllers;
    exports pl.lipiec.Models;
    requires json.simple;
}

