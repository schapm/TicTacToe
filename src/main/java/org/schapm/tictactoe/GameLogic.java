package org.schapm.tictactoe;

import javafx.scene.control.Button;

/**
 *
 * @author schapm
 */

public class GameLogic {

    private final GameView gameView;
    private boolean isFinished;
    private final Button[] buttons;
    private final Player playerX;
    private final Player player0;
    private Player turn;

    public GameLogic(GameView gameView, Button[] buttons) {
        this.gameView = gameView;
        this.isFinished = false;
        this.buttons = buttons;
        this.playerX = new Player("X");
        this.player0 = new Player("O");
        this.turn = playerX;
    }

    public void updateGame(Button button) {
        // Check if game is finished
        if (isFinished) {
            return;
        }

        // Check if button is filled already
        if (!button.getText().trim().isEmpty()) {
            return;
        }

        button.setText(turn.getName());

        // Check for stalemate
        if (areButtonsFilled()) {
            gameView.setDraw();
            return;
        }

        updateTurn();
    }

    public boolean areButtonsFilled() {
        int sum = 0;
        for (Button button : buttons) {
            if (!button.getText().trim().isEmpty()) {
                sum++;
            }
        }

        return sum >= buttons.length;
    }

    public Player getTurn() {
        return this.turn;
    }

    private void updateTurn() {
        if (turn.equals(playerX)) {
            turn = player0;
        } else {
            turn = playerX;
        }

        gameView.setTurn(turn);
    }

}