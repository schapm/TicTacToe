package org.schapm.tictactoe;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author schapm
 */

public class GameView {

    final Button[] buttonArray = new Button[9];
    private final Label status;
    private GameLogic gameLogic;

    public GameView() {
        // Initialise buttons
        for (int i = 0; i <= buttonArray.length - 1; i++) {
            buttonArray[i] = new Button(" ");
        }

        // Set buttons font and action
        for (Button button : buttonArray) {
            button.setFont(Font.font("MONOSPACED", 50));
            button.setOnAction((event) -> gameLogic.updateGame(button));
        }

        this.status = new Label();
        this.gameLogic = new GameLogic(this, buttonArray);
    }

    // Create game button view
    public Parent getButtonView() {
        GridPane layout = new GridPane();

        int buttonToAddToView = 0;
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                layout.add(buttonArray[buttonToAddToView], x, y);
                buttonToAddToView++;
            }
        }

        return layout;
    }

    // Create game turn view
    public Parent getTurnView() {
        setTurn(gameLogic.getTurn());
        this.status.setFont(Font.font("MONOSPACED", 30));

        return this.status;
    }

    public void setTurn(Player turn) {
        this.status.setText("Turn: " + turn.getName());
    }

    public void setWinner(Player winner) {
        this.status.setText(winner.getName() + " Wins!");
    }

    public void setDraw() {
        this.status.setText("Draw!");
    }
    
}
