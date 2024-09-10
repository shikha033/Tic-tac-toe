import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean playerXTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("")) {
            if (playerXTurn) {
                source.setText("X");
            } else {
                source.setText("O");
            }
            playerXTurn = !playerXTurn;
            checkForWinner();
        }
    }

    private void checkForWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            board[row][col] = buttons[i].getText();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                announceWinner(board[i][0]);
                return;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                announceWinner(board[0][i]);
                return;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            announceWinner(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            announceWinner(board[0][2]);
            return;
        }

        // Check for a tie
        boolean tie = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetGame();
        }
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(this, winner + " wins!");
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
