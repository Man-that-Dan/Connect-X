import org.junit.Test;

import java.util.Objects;
import static org.junit.Assert.*;


public class TestIGameboard {


    private IGameBoard factory(int c, int r, int w){
        IGameBoard product = new GameBoardMem(c, r, w);
        return product;


    }

    private char[][] createarr(){
        char[][] arr = new char[100][100];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                arr[i][j] = ' ';
            }
        }
        return arr;
    };

    private String arrToString(int r, int c, char[][] arrRep){
        String stringRep;
        stringRep = "|";
        for(int n = 0; n < c; n++){
            //print numbers with space before
            if(n < 10){
                stringRep = stringRep + " " + n + "|";
            } else {
                //double digit column headings
                stringRep = stringRep + n + "|";
            }
        }
        stringRep = stringRep + "\n";

        //start from top row and move down, adding to string
        int i, j;
        for(i = r - 1; i >= 0; i--){
            stringRep = stringRep + "|";
            for(j = 0; j < c; j++){
                stringRep = stringRep +  arrRep[i][j] + " " + "|";
            }
            stringRep = stringRep + "\n";
        }
        return stringRep;
        
    };

    //average case
    @Test
    public void testconstructor_4_10_4() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 4, 4);
        int rows = testsub.getNumRows();
        int columns = testsub.getNumColumns();
        int win = testsub.getNumToWin();
        char[][] teststate = createarr();
        String exp = arrToString(4, 10, teststate);
        String act = testsub.toString();

        assertTrue((rows == 4 && columns == 10 && win == 4 && Objects.equals(exp, act)));



    }

    //edge case
    @Test
    public void testconstructor_100_100_25() {
        //gameboard constructor
        IGameBoard testsub = factory(100, 100, 25);
        int rows = testsub.getNumRows();
        int columns = testsub.getNumColumns();
        int win = testsub.getNumToWin();
        char[][] teststate = createarr();
        String exp = arrToString(100, 100, teststate);
        String act = testsub.toString();

        assertTrue((rows == 100 && columns == 100 && win == 25 && Objects.equals(exp, act)));



    }

    //edge case
    @Test
    public void testconstructor_3_3_3() {
        //gameboard constructor
        IGameBoard testsub = factory(3, 3, 3);
        int rows = testsub.getNumRows();
        int columns = testsub.getNumColumns();
        int win = testsub.getNumToWin();
        char[][] teststate = createarr();
        String exp = arrToString(3, 3, teststate);
        String act = testsub.toString();

        assertTrue((rows == 3 && columns == 3 && win == 3 && Objects.equals(exp, act)));



    }

    //edge case
    @Test
    public void testcheckiffree_empty() {
        //gameboard constructor
        IGameBoard testsub = factory(5, 5, 4);
        boolean free = testsub.checkIfFree(4);


        assertTrue(free);



    }

    //edge case, make sure checking column free not row free
    @Test
    public void testcheckiffree_row_vs_col() {
        //gameboard constructor
        IGameBoard testsub = factory(5, 5, 4);

        for(int i = 0; i < 4; i++){
            testsub.placeToken('x', 0);
        }
        for(int i = 1; i < 5; i++){
            testsub.placeToken('x', i);
        }

        boolean free = testsub.checkIfFree(0);
        assertTrue(free);



    }

    //edge case,
    @Test
    public void testcheckiffree_typicalcase() {
        //gameboard constructor
        IGameBoard testsub = factory(5, 5, 4);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 4);
        }


        boolean free = testsub.checkIfFree(4);
        assertTrue(!free);



    }

    //test for more in a row than the win condition
    @Test
    public void testcheckhorizwin_greaterthanwin() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 3);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', i);
        }


        boolean free = testsub.checkHorizWin(0, 2, 'x');
        assertTrue(free);



    }

    //test for win in the middle of the board
    @Test
    public void testcheckhorizwin_middleofboard() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 3);

        for(int i = 0; i < 10; i++){
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
        }
        for(int i = 3; i < 8; i++){
            testsub.placeToken('o', i);
        }

        boolean free = testsub.checkHorizWin(4, 5, 'o');
        assertTrue(free);



    }

    @Test
    public void testcheckhorizwin_topleft() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 5, 3);

        for(int i = 0; i < 10; i++){
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
            testsub.placeToken('x', i);
        }
        for(int i = 0; i < 3; i++){
            testsub.placeToken('o', i);
        }

        boolean free = testsub.checkHorizWin(4, 0, 'o');
        assertTrue(free);



    }
    //test last drop in middle of win streak
    @Test
    public void testcheckhorizwin_lastinmiddle() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 5, 5);

        for(int i = 0; i < 2; i++){
            testsub.placeToken('x', i);

        }
        for(int i = 3; i < 5; i++){
            testsub.placeToken('x', i);
        }
        testsub.placeToken('x', 2);

        boolean free = testsub.checkHorizWin(0, 2, 'x');
        assertTrue(free);



    }

    //make sure checkhoriz is not checking vertical win
    @Test
    public void testcheckhorizwin_notcolumncheck() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 5, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 0);

        }

        boolean free = testsub.checkHorizWin(0, 0, 'x');
        assertTrue(!free);



    }

    //check that vert win is not checking columns
    @Test
    public void testcheckvertwin_notrowcheck() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 5, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', i);

        }

        boolean free = testsub.checkVertWin(0, 0, 'x');
        assertTrue(!free);



    }

    //typical case
    @Test
    public void testcheckvertwin_column4() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 5, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 4);

        }

        boolean free = testsub.checkVertWin(4, 4, 'x');
        assertTrue(free);



    }

    //check vert when token isn't last one placed
    @Test
    public void testcheckvertwin_buriedcolumn() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 4);

        }

        testsub.placeToken('o', 4);

        boolean free = testsub.checkVertWin(5, 4, 'x');
        assertTrue(free);



    }
    //make sure vert win is only checking consecutive tokens
    @Test
    public void testcheckvertwin_checkconsecutive() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 3; i++){
            testsub.placeToken('x', 4);

        }
        testsub.placeToken('o', 4);
        testsub.placeToken('x', 4);
        testsub.placeToken('x', 4);

        boolean free = testsub.checkVertWin(5, 4, 'x');
        assertTrue(!free);



    }

    //edge case according to contract
    @Test
    public void testcheckvertwin_endposition() {
     
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 9);

        }
        for(int i = 5; i < 10; i++){
            testsub.placeToken('o', 9);

        }

        boolean free = testsub.checkVertWin(9, 9, 'o');
        assertTrue(free);



    }

    //typical case diagonally up
    @Test
    public void testcheckdiagwin_typicalcaseup() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 5; i++){
            for(int j = i; j < 5; j++) {
                testsub.placeToken('x', j);
            }
        }



        boolean free = testsub.checkDiagWin(4, 4, 'x');
        assertTrue(free);



    }

    //typical case diagonally down
    @Test
    public void testcheckdiagwin_typicalcasedown() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 5; i >= 0; i--){
            for(int j = i; j >= 0; j--) {
                testsub.placeToken('x', j);
            }
        }


        boolean free = testsub.checkDiagWin(4, 0, 'x');
        assertTrue(free);



    }

    //typical case where last one is placed in middle
    @Test
    public void testcheckdiagwin_lastinmiddle() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 6);

        for(int i = 5; i >= 4; i--){
            for(int j = i; j >= 0; j--) {
                testsub.placeToken('x', j);
            }
        }
        testsub.placeToken('x', 3);
        testsub.placeToken('x', 2);
        testsub.placeToken('x', 1);
        testsub.placeToken('x', 0);
        for(int i = 2; i >= 0; i--){
            for(int j = i; j >= 0; j--) {
                testsub.placeToken('x', j);
            }
        }

        for(int i = 0; i < 3; i++){
            testsub.placeToken('x', 3);
        }


        boolean free = testsub.checkDiagWin(2, 3, 'x');
        assertTrue(free);



    }


    //case where the check starts at a corner
    @Test
    public void testcheckdiagwin_startatcorner() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 5; i >= 0; i--){
            for(int j = i; j >= 0; j--) {
                testsub.placeToken('o', j);
            }
        }

        for(int i = 3; i >= 0; i--){
            for(int j = 5; j >= 0; j--) {
                testsub.placeToken('x', j);
            }
        }


        boolean free = testsub.checkDiagWin(9, 0, 'x');
        assertTrue(free);



    }

    //typical case where last one is placed in middle going from low to high left to right
    @Test
    public void testcheckdiagwin_lastinmiddlereverse() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 6);

        for(int i = 0; i < 2; i++){
            for(int j = i; j < 6; j++) {
                testsub.placeToken('x', j);
            }
        }

        testsub.placeToken('x', 3);
        testsub.placeToken('x', 4);
        testsub.placeToken('x', 5);
        for(int i = 3; i < 6; i++){
            for(int j = i; j < 6; j++) {
                testsub.placeToken('x', j);
            }
        }


        testsub.placeToken('x', 2);


        boolean free = testsub.checkDiagWin(2, 2, 'x');
        assertTrue(free);



    }

    //check starts at top right corner
    @Test
    public void testcheckdiagwin_rightcorner() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 4; i < 10; i++){
            for(int j = i; j < 10; j++) {
                testsub.placeToken('x', j);
            }
        }

        for(int i = 0; i < 4; i++){
            for(int j = 4; j < 10; j++) {
                testsub.placeToken('x', j);
            }
        }


        boolean free = testsub.checkDiagWin(4, 4, 'x');
        assertTrue(free);



    }

    //make sure it's checking the right token
    @Test
    public void testcheckdiagwin_wrongtoken() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 5; i >= 0; i--){
            for(int j = i; j >= 0; j--) {
                testsub.placeToken('x', j);
            }
        }


        boolean free = testsub.checkDiagWin(4, 0, 'o');
        assertTrue(!free);



    }

    //typical case in middle
    @Test
    public void testcheckdiagwin_typicalmiddleboard() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 3; i < 8; i++){
            for(int j = i; j < 8; j++) {
                testsub.placeToken('x', j);
            }
        }


        boolean free = testsub.checkDiagWin(4, 7, 'x');
        assertTrue(free);



    }

    //typical tie
    @Test
    public void testchecktie_typical() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 5; j++) {
                if(i % 2 == 0) {
                    testsub.placeToken('x', i);
                    testsub.placeToken('o', i);
                } else {
                    testsub.placeToken('f', i);
                    testsub.placeToken('p', i);
                }
            }
        }


        boolean free = testsub.checkTie();
        assertTrue(free);



    }

    //typical tie
    @Test
    public void testchecktie_typical2() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 5; j++) {
                if(i % 2 == 0) {
                    testsub.placeToken('o', i);
                    testsub.placeToken('z', i);
                } else {
                    testsub.placeToken('b', i);
                    testsub.placeToken('t', i);
                }
            }
        }


        boolean free = testsub.checkTie();
        assertTrue(free);



    }

    //empty board
    @Test
    public void testchecktie_empty() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);




        boolean free = testsub.checkTie();
        assertTrue(!free);



    }

    //one column filled
    @Test
    public void testchecktie_firstcol() {
        //gameboard constructor
        IGameBoard testsub = factory(10, 10, 5);

        for(int i = 0; i < 5; i++){
            testsub.placeToken('x', 0);
            testsub.placeToken('o', 0);

        }


        boolean free = testsub.checkTie();
        assertTrue(!free);



    }

    //random spot in empty board
    @Test
    public void testwhatsatpos_empty() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char test = testsub.whatsAtPos(2, 3);



        boolean free = (test == ' ');
        assertTrue(free);



    }

    //test c = 0; edge case
    @Test
    public void testwhatsatpos_bottomleftcorner() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        testsub.placeToken('x', 0);
        char test = testsub.whatsAtPos(0, 0);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //test c = columns - 1; edge case
    @Test
    public void testwhatsatpos_bottomrightcorner() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        testsub.placeToken('x', 9);
        char test = testsub.whatsAtPos(0, 9);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //test c = columns - 1 and r = rows - 1; edge case
    @Test
    public void testwhatsatpos_toprightcorner() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        for(int i = 0; i < 9; i++) {
            testsub.placeToken('o', 9);
        };
        testsub.placeToken('x', 9);
        char test = testsub.whatsAtPos(9, 9);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //test c = 0 and r = rows - 1; edge case
    @Test
    public void testwhatsatpos_topleftcorner() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        for(int i = 0; i < 9; i++) {
            testsub.placeToken('o', 0);
        };
        testsub.placeToken('x', 0);
        char test = testsub.whatsAtPos(9, 0);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //test getting token not on top of column
    @Test
    public void testwhatsatpos_nottop() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        for(int i = 0; i < 5; i++) {
            testsub.placeToken('o', 0);
            testsub.placeToken('x', 0);
        };

        char test = testsub.whatsAtPos(3, 0);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //test to make sure row and column aren't mixed up
    @Test
    public void testwhatsatpos_checkorder() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);
        for(int i = 0; i < 5; i++) {
            testsub.placeToken('o', 0);
            testsub.placeToken('x', 0);
        };
        testsub.placeToken('x', 2);
        char test = testsub.whatsAtPos(0, 2);



        boolean free = (test == 'x');
        assertTrue(free);



    }

    //place on an empty board
    @Test
    public void testplacetoken_empty() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char[][] teststate = createarr();
        teststate[0][2] = 'x';
        String exp = arrToString(10, 10, teststate);


        testsub.placeToken('x', 2);


        String act = testsub.toString();


        boolean free = (Objects.equals(act, exp));
        assertTrue(free);



    }

    //place on first column, edge case;
    @Test
    public void testplacetoken_first() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char[][] teststate = createarr();
        teststate[0][0] = 'x';
        String exp = arrToString(10, 10, teststate);


        testsub.placeToken('x', 0);


        String act = testsub.toString();


        boolean free = (Objects.equals(act, exp));
        assertTrue(free);



    }

    //place on last column, edge case;
    @Test
    public void testplacetoken_last() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char[][] teststate = createarr();
        teststate[0][9] = 'x';
        String exp = arrToString(10, 10, teststate);


        testsub.placeToken('x', 9);


        String act = testsub.toString();


        boolean free = (Objects.equals(act, exp));
        assertTrue(free);



    }


    //test stacking
    @Test
    public void testplacetoken_stack() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char[][] teststate = createarr();
        teststate[0][0] = 'x';
        teststate[1][0] = 'o';
        String exp = arrToString(10, 10, teststate);


        testsub.placeToken('x', 0);

        testsub.placeToken('o', 0);


        String act = testsub.toString();


        boolean free = (Objects.equals(act, exp));
        assertTrue(free);



    }

    //test filling a column
    @Test
    public void testplacetoken_fillcol() {
        //gameboard constructor

        IGameBoard testsub = factory(10, 10, 5);

        char[][] teststate = createarr();
        for(int i = 0; i < 10; i++) {
            teststate[i][4] = 'x';
            testsub.placeToken('x', 4);
        };
        String exp = arrToString(10, 10, teststate);


       
        String act = testsub.toString();


        boolean free = (Objects.equals(act, exp));
        assertTrue(free);



    }

}
