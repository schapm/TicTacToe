module org.schapm.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.schapm.tictactoe to javafx.fxml;
    exports org.schapm.tictactoe;
}