import javax.swing.JButton;

/**
 * TicTacToeTile represents a button in the Tic Tac Toe grid.
 * It holds its row and column position to identify itself.
 */
public class TicTacToeTile extends JButton {
    private int row;
    private int col;

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.setText(" "); // Start with an empty tile
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
