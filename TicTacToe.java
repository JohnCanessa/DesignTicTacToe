import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Design Tic-Tac-Toe
 * https://leetcode.com/problems/design-tic-tac-toe/
 * 
 * Runtime: 3 ms, faster than 99.96% of Java online submissions.
 * Memory Usage: 42.1 MB, less than 48.10% of Java online submissions.
 */
public class TicTacToe {

    // **** class members ****
    public int      n = 0;
    public int[][]  board = null;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        this.n      = n;
        this.board  = new int[n][n];
    }

    /**
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // **** loop once per row ****
        for (int i = 0; i < n; i++) {

            // **** display row ****
            sb.append(Arrays.toString(board[i]));

            // **** append \n (if needed) ****
            if (i < n - 1)
                sb.append("\n");
        }

        // **** return string representation ****
        return sb.toString();
    }

    /**
     * Determine if we have a winner.
     * Execution time: O(4n)
     */
    private int haveWinner(int row, int col, int player) {

        // **** initialization ****
        int winner = player;

        // **** check row O(n) ****
        for (int c = 0; c < this.n; c++) {
            if (board[row][c] != player) {
                winner = 0;
                break;
            }
        }

        // **** check if we have a winner ****
        if (winner == player)
            return winner;

        // **** check column O(n) ****
        winner = player;
        for (int r = 0; r < this.n; r++) {
            if (this.board[r][col] != player) {
                winner = 0;
                break;
            }
        }

        // **** check if we have a winner ****
        if (winner == player)
            return winner;

        // **** check first diagonal O(n) ****
        winner = player;
        for (int d = 0; d < this.n; d++) {
            if (this.board[d][d] != player) {
                winner = 0;
                break;
            }
        }

        // **** check if we have a winner ****
        if (winner == player)
            return winner;

        // **** check second diagonal O(n) ****
        winner = player;
        for (int d = n - 1; d >= 0; d--) {
            if (this.board[d][n - d - 1] != player) {
                winner = 0;
                break;
            }
        }

        // **** ****
        return winner;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * @param row The row of the board.
     * @param col The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     *          0: No one wins.
     *          1: Player 1 wins.
     *          2: Player 2 wins.
     */
    public int move(int row, int col, int player) {

        // **** initialization ****
        int winner = 0;

        // **** flag move on board ****
        board[row][col] = player;

        // **** check if we have a winner ****
        winner = haveWinner(row, col, player);
        
        // **** return winner ****
        return winner;
    }

    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** initialization ****
        int winner      = 0;
        TicTacToe obj   = null;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // ***** loop until there is a winner ****
        while(winner == 0) {

            // **** read next input line ****
            String[] strArr = br.readLine().trim().split(", ");

            // **** initialize or move ****
            if (strArr.length == 1) {
                obj = new TicTacToe(Integer.parseInt(strArr[0]));
            } else {

                // **** ****
                int r = Integer.parseInt(strArr[0]);
                int c = Integer.parseInt(strArr[1]);
                int p = Integer.parseInt(strArr[2]);
              
                // **** player makes move ****
                winner = obj.move(r, c, p);

                // **** signal winner (if needed) ****
                if (winner != 0) {
                    System.out.println("main <<< board:");
                    System.out.println(obj.toString());
                    System.out.println("main <<< player: " + p +
                                        " winner winner chicken dinner !!!");
                }
            }
        }

        // **** close the buffered reader ****
        br.close();
    }
}