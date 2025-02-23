import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TicTacToeFrame sets up the GUI for the Tic Tac Toe game.
 * It handles button clicks and game logic interactions.
 */
public class TicTacToeFrame extends JFrame {
    private TicTacToe game;
    private TicTacToeTile[][] buttons;

    public TicTacToeFrame() {
        game = new TicTacToe();
        buttons = new TicTacToeTile[3][3];

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        ButtonHandler handler = new ButtonHandler();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeTile(row, col);
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].addActionListener(handler);
                boardPanel.add(buttons[row][col]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile tile = (TicTacToeTile) e.getSource();
            int row = tile.getRow();
            int col = tile.getCol();

            if (game.isValidMove(row, col)) {
                game.makeMove(row, col);
                tile.setText(game.getCurrentPlayer());

                if (game.isWin()) {
                    showGameOverMessage("Player " + game.getCurrentPlayer() + " Wins!");
                } else if (game.isTie()) {
                    showGameOverMessage("It's a Tie!");
                } else {
                    game.switchPlayer();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
            }
        }
    }

    private void showGameOverMessage(String message) {
        int response = JOptionPane.showConfirmDialog(null, message + "\nDo you want to play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    private void resetBoard() {
        game.resetGame();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
            }
        }
    }
}
