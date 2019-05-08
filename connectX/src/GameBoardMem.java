

import java.lang.String;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;



/**
 * Correspondence: columns = colNum
 *                 rows = rowNum
 *                 consecutive tokens to win = winNum
 *
 * @invariant:            0 <= colNum <= 100
 * @invariant             0 <= rowNum <= 100
 * @invariant             0 <= winNum <= 25
 */
public class GameBoardMem implements IGameBoard {

    private Map<Integer, List<Character>> board;
    private int colNum, rowNum, winNum;

    /**
     * @pre (3 <= r <= 100) && (3 <= c <= 100) && (3 <= w <= 25)
     * @post rowNum = r && colNum = c && winNum = w
     * @post board is empty, contains nothing
     * @param r number of columns for board to use
     * @param c number of rows for board to use
     * @param w number in a row to win
     */
    public GameBoardMem(int c, int r, int w){
      board = new HashMap<>();
      rowNum = r;
      colNum = c;
      winNum = w;
      for(int i = 0; i < c; i++){
          LinkedList<Character> thislist = new LinkedList<>();
          board.put(i, thislist);
      };
    };

    //add char to list
    public void placeToken(char p, int c){
          List<Character> curr = board.get(c);
          curr.add(p);

    }

    //return blank if row is greater than the indexes of column, otherwise return appropriate token
    public char whatsAtPos(int r, int c) {
        List<Character> curr = board.get(c);
        if((curr.size() - 1) < r){
          return ' ';
        };
        char val = curr.get(r);
        return val;
    }


    public int getNumRows() {
        return rowNum;
    }
    public int getNumColumns() {
        return colNum;
    }
    public int getNumToWin() {
        return winNum;
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
                Theboard = Theboard +  whatsAtPos(i, j) + " " + "|";
            }
            Theboard = Theboard + "\n";
        }
        return Theboard;
    }
}