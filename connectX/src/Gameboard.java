
import java.lang.String;



/**
 * Correspondence: columns = colNum
 *                 rows = rowNum
 *                 consecutive tokens to win = winNum
 *
 * @invariant:            0 <= colNum <= 100
 * @invariant             0 <= rowNum <= 100
 * @invariant             0 <= winNum <= 25
 */
public class Gameboard implements IGameBoard {

    //the game board. top right is [rowNum - 1][colNum - 1]
    // row major
    private static final char[][] board = new char[100][100];
    private final int rowNum, colNum, winNum;

    /**
     * @pre (3 <= x <= 100) && (3 <= y <= 100) && (3 <= w <= 25)
     * @post rowNum = y && colNum = x && winNum = w
     * @post board is empty, full of ''
     * @param x number of columns for board to use
     * @param y number of rows for board to use
     * @param w number in a row to win
     */
    public Gameboard(int x, int y, int w) {
        rowNum = y;
        colNum = x;
        winNum = w;
        int i, j;
        // initialize all spots within range to empty space
        for(i = 0; i < rowNum; i++){
            for(j = 0; j < colNum; j++){
                board[i][j] = ' ';
            }
        }
    }



    public void placeToken(char p, int c){
        int i = 0;
        //find top empty spot of column
        while(i < rowNum && board[i][c] != ' '){
            i++;
        }
        board[i][c] = p;

        }


    public char whatsAtPos(int r, int c){
        return board[r][c];
    }


    /**
     * @post toString = string representation of current state of board
     * @return string representation of board
     */
    @Override
    public String toString(){
        String Theboard;
        Theboard = "|";
        for(int n = 0; n < colNum; n++){
            //print numbers with space before
            if(n < 10){
                Theboard = Theboard + " " + n + "|";
            } else {
                //double digit column headings
                Theboard = Theboard + n + "|";
            }
        }
        Theboard = Theboard + "\n";

        //start from top row and move down, adding to string
        int i, j;
        for(i = rowNum - 1; i >= 0; i--){
            Theboard = Theboard + "|";
            for(j = 0; j < colNum; j++){
                Theboard = Theboard +  board[i][j] + " " + "|";
            }
            Theboard = Theboard + "\n";
        }
        return Theboard;
    }




    //getters

    public int getNumRows(){
        return rowNum;
    }


    public int getNumColumns(){
        return colNum;
    }



    public int getNumToWin(){
        return winNum;
    }

}
