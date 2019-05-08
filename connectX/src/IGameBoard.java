



/***
 *
 * A Gameboard containing characters representing players. Gameboard is a 2d grid
 * of maximum width 100 and height 100. Length and width is determined by user.
 * Players need to connect Y number of their tokens in a line vertically, horizontally, or diagonally
 * to win. Y (number of consecutive tokens to win) is determined by player with a maximum value of 25.
 *
 * Initialization ensures the GameBoard of X columns and Y rows is empty. i.e. all slots are ' '.
 *
 * Defines: rows:Y
 *          columns:X
 *          number of consecutive tokens to win:W
 * Constraints: 3 <= rows <= 100
 *              3 <= columns <= 100
 *              3 <= win number <= 25
 *
 *  @invariant          3 <= rows <= 100
 *  @invariant          3 <= columns <= 100
 *  @invariant          3 <= win number <= 25
 */
public interface IGameBoard {

    /**
     * @pre c < columns && c >= 0
     * @post checkIfFree == true IFF (whatsAtPos(rows - 1, c) == '_')
     * @param c the column to check if free
     * @return true if the column is not yet filled.
     */
    default public boolean checkIfFree(int c){
            return (whatsAtPos((getNumRows() - 1), c) == ' ');
    }

    /**
     * @pre (0 <= c < columns) && (p == a char)
     * @post (whatsAtPos(last unfilled row, c) == p)
     * @param p the player's token to place at column c
     * @param c the column to place token in
     */
    public void placeToken(char p, int c);

    /**
     * @pre (0 <= r < rows) && (0 <= c < columns)
     * @post whatsAtPos == token at row r, column c
     * @param r row
     * @param c column
     * @return  token at row r, column c
     */
    char whatsAtPos(int r, int c);

    /**
     * @pre !checkForWin
     * @post checkTie == true IFF all columns are filled
     * @return true if tie
     */
    default boolean checkTie(){
        int col = getNumColumns();
        boolean tie = true;
        for(int i = 0; i < col; i++)  {
            if(checkIfFree(i)){
                tie = false;
            }
        }
        return tie;
    }



    /**
     * @pre 0 <= c < columns
     * @post checkForWin = true IFF player with top token in c has won either horizontally, diagonally, or vertically
     * @param c column last placed in
     * @return true if last player won
     */
     default public boolean checkForWin(int c) {
        int i = 0;
        boolean win = false;
        while(i < (getNumRows() - 1) && whatsAtPos(i, c) != ' '){
            i++;
        }
        if(whatsAtPos(i, c) == ' '){
            i--;
        }
        // check the various win conditions
        char p = whatsAtPos(i, c);
        win = checkHorizWin(i, c, p);
        if(!win){
            win = checkVertWin(i, c, p);
        }
        if(!win){
            win = checkDiagWin(i, c, p);
        }
        return win;
    }

    /**
     * @pre 0 <= c < columns
     * @pre 0 <= r < rows
     * @pre p = a char
     * @post checkHorizWin = true IFF player with token p has 4 consecutive tokens at row r
     * @param c column last placed in
     * @param r row last placed in
     * @param p last token placed
     * @return true if last player won horizontally
     */
    default public boolean checkHorizWin(int r, int c, char p){
        int i;
        int count = 0;
        boolean win = false;
        for(i = 0; i < getNumColumns(); i++){
            if(whatsAtPos(r, i) == p){
                count++;
                if(count == getNumToWin()){
                    win = true;
                }
            } else {
                count = 0;
            }
        }
        return win;
    }

    /**
     * @pre 0 <= c < columns
     * @pre 0 <= r < rows
     * @pre p = a char
     * @post checkVertWin = true IFF player with token p has 4 or more consecutive tokens in column c
     * @param c column last placed in
     * @param r row last placed in
     * @param p last token placed
     * @return true if last player won vertically
     */
    default public boolean checkVertWin(int r, int c, char p){
        int i;
        int count = 0;
        boolean win = false;
        for(i = 0; i < getNumRows(); i++){
            if(whatsAtPos(i, c) == p){
                count++;
                if(count == getNumToWin()){
                    win = true;
                }
            } else {
                count = 0;
            }
        }
        return win;
    }

    /**
     * @pre 0 <= c < columns
     * @pre 0 <= r < rows
     * @pre p = a char
     * @post checkDiagWin = true IFF player with token p has 4 consecutive tokens diagonally from position (r, c)
     * @param c column last placed in
     * @param r row last placed in
     * @param p last token placed
     * @return true if last player won diagonally
     */
    default boolean checkDiagWin(int r, int c, char p){
        int i = c;
        int n = r;
        int z = r;
        int count = 0;
        int count2 = 0;
        boolean win = false;
        // move back to first column, out in x shape.
        while(i > 0){
            i--;
            n++;
            z--;
        }

        for(i = 0; i < getNumColumns(); i++){
            // this check moves diagonally up
            if(0 <= n && n < getNumRows()){
                if(whatsAtPos(n, i) == p){
                    count++;
                    if(count == getNumToWin()){
                        win = true;
                    };
                } else {
                    count = 0;
                }
            }
            n--;
            //this check moves diagonally down
            if(0 <= z && z < getNumRows()){
                if(whatsAtPos(z, i) == p){
                    count2++;
                    if(count2 == getNumToWin()){
                        win = true;
                    };
                } else {
                    count2 = 0;
                }
            }
            z++;
        }
        return win;

    }

    /**
     * @post getNumRows() = Y
     * @return number of rows in board.
     */
    int getNumRows();

    /**
     * @post getNumColumns() = X
     * @return number of columns in board
     */
    int getNumColumns();


    /**
     * @post getNumToWin() = W
     * @return number of consecutive tokens needed to win
     */
    int getNumToWin();



}
