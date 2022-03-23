package org.schapm.tictactoe;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

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

        if (checkForWin(buttonValuesToList())) {
            // Current turn is winner
            gameView.setWinner(turn);
            isFinished = true;
            return;
        }

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

    private boolean checkForWin(List<String> values) {
        for (String string : values) {
            if (string.trim().equals(player0.getName() + player0.getName() + player0.getName())) {
                return true;
            }

            if (string.trim().equals(playerX.getName() + playerX.getName() + playerX.getName())) {
                return true;
            }
        }

        return false;
    }

    private List<String> buttonValuesToList() {
        List<String> allValues = new ArrayList<>();

        // Columns
        int buttonIndex = 0;
        for (int x = 1; x <= 3; x++) {
            StringBuilder colSum = new StringBuilder();
            for (int y = 1; y <= 3; y++) {
                colSum.append(buttons[buttonIndex].getText());
                buttonIndex++;
            }

            allValues.add(colSum.toString());
        }

        // Rows
        String[] row = {"", "", ""};
        int index = 0;
        while (index <= row.length - 1) {
            row[index] += String.valueOf(allValues.get(0).charAt(index)) + String.valueOf(allValues.get(1).charAt(index))
                    + String.valueOf(allValues.get(2).charAt(index));
            allValues.add(row[index]);
            index++;
        }

        String[] diagonals = {"", ""};
        // Left diagonal
        diagonals[0] = String.valueOf(allValues.get(0).charAt(0)) + String.valueOf(allValues.get(1).charAt(1))
                + String.valueOf(allValues.get(2).charAt(2));
        allValues.add(diagonals[0]);

        // Right diagonal
        diagonals[1] = String.valueOf(allValues.get(0).charAt(2)) + String.valueOf(allValues.get(1).charAt(1))
                + String.valueOf(allValues.get(2).charAt(0));
        allValues.add(diagonals[1]);

        return allValues;
    }

}