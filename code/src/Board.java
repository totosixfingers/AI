import java.util.ArrayList;


public class Board {
    //Variables for the boards values
    public static final int X = 1;
    public static final int O = -1;
    public static final int EMPTY = 0;
    public int evaluation;

    //Immediate move that lead to this board
    private Move lastMove;

    /* Variable containing who played last; whose turn resulted in this board
     * Even a new board has lastLetterPlayed value; it denotes which player will play first
     */
    private int lastLetterPlayed;

    private int[][] gameboard;

    public Board(Board board) {
        lastMove = board.lastMove;
        lastLetterPlayed = board.getLastLetterPlayed();
        evaluation = 0;
        gameboard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameboard[i][j] = board.gameboard[i][j];
            }
        }
    }

    public Board() {
        lastLetterPlayed = O;
        gameboard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameboard[i][j] = EMPTY;
            }
        }
        gameboard[3][3] = O;
        gameboard[4][4] = O;
        gameboard[3][4] = X;
        gameboard[4][3] = X;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public int getLastLetterPlayed() {
        return lastLetterPlayed;
    }

    public int[][] getGameboard() {
        return gameboard;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }

    public void setLastLetterPlayed(int lastLetterPlayed) {
        this.lastLetterPlayed = lastLetterPlayed;
    }

    public void setGameboard(int[][] gameboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.gameboard[i][j] = gameboard[i][j];
            }
        }
    }

    public void makeMove(int row, int col, int letter) {
        gameboard[row][col] = letter;
        lastMove = new Move(row, col);
        lastLetterPlayed = letter;
    }
    public boolean LegalMove(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8)
            return true;
        else
            return false;

    }

    public boolean isValidMove(int row, int col) {
        if (LegalMove(row,col) && gameboard[row][col] == EMPTY){
            if(row>0 && row < 7 && col > 0 && col < 7 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row+1][col] != lastLetterPlayed && gameboard[row+1][col+1] != lastLetterPlayed
                    && gameboard[row][col-1] != lastLetterPlayed && gameboard[row-1][col] != lastLetterPlayed && gameboard[row-1][col-1] != lastLetterPlayed
                    && gameboard[row+1][col-1] != lastLetterPlayed && gameboard[row-1][col+1] != lastLetterPlayed) {
                return false;
            }
            else if(row == 0 && col > 0 && col < 7 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row+1][col] != lastLetterPlayed && gameboard[row+1][col+1] != lastLetterPlayed
                    && gameboard[row][col-1] != lastLetterPlayed && gameboard[row+1][col-1] != lastLetterPlayed) {
                return false;
            }
            else if(row == 7 && col > 0 && col < 7 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row][col-1] != lastLetterPlayed &&
                    gameboard[row-1][col] != lastLetterPlayed && gameboard[row-1][col-1] != lastLetterPlayed && gameboard[row-1][col+1] != lastLetterPlayed) {
                return false;
            }
            else if(row>0 && row < 7 && col == 0 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row+1][col] != lastLetterPlayed
                    && gameboard[row+1][col+1] != lastLetterPlayed && gameboard[row-1][col] != lastLetterPlayed && gameboard[row-1][col+1] != lastLetterPlayed) {
                return false;
            }
            else if(row>0 && row < 7 && col == 7 && gameboard[row+1][col] != lastLetterPlayed && gameboard[row][col-1] != lastLetterPlayed
                    && gameboard[row-1][col] != lastLetterPlayed && gameboard[row-1][col-1] != lastLetterPlayed && gameboard[row+1][col-1] != lastLetterPlayed){
                return false;
            }
            else if(row == 0 && col == 0 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row+1][col] != lastLetterPlayed
                    && gameboard[row+1][col+1] != lastLetterPlayed) {
                return false;
            }
            else if(row == 7 && col == 7 && gameboard[row][col-1] != lastLetterPlayed && gameboard[row-1][col] != lastLetterPlayed
                    && gameboard[row-1][col-1] != lastLetterPlayed) {
                return false;
            }
            else if(row == 0 && col == 7 && gameboard[row+1][col] != lastLetterPlayed && gameboard[row][col-1] != lastLetterPlayed
                    && gameboard[row+1][col-1] != lastLetterPlayed) {
                return false;
            }
            else if(row == 7 && col == 0 && gameboard[row][col+1] != lastLetterPlayed && gameboard[row-1][col] != lastLetterPlayed
                    && gameboard[row-1][col+1] != lastLetterPlayed) {
                return false;
            }
            else if (searchmoveR(row, col) || searchmoveL(row, col) || searchmoveUP(row, col) || searchmoveDOWN(row, col) || searchmoveDiagLDOWN(row, col)
                    || searchmoveDiagLUP(row, col) || searchmoveDiagRDOWN(row, col) || searchmoveDiagRUP(row, col)) {
                return true;

            }



        }
        return false;

    }


    public boolean isTerminal() {
        int counterX = 0;
        int counterO = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (gameboard[row][col]==X)
                {
                    counterX++;
                }
                if (gameboard[row][col]==O) {
                    counterO++;
                }
            }
        }
        if (((counterX + counterO) == 64)) {
            return true;
        }
        return false;
    }

    public ArrayList<Board> getChildren(int letter)
    {
        ArrayList<Board> children = new ArrayList<Board>();
        for(int row=0; row<8; row++)
        {
            for(int col=0; col<8; col++)
            {
                if(isValidMove(row, col))
                {
                	Board child = new Board(this);
                    child.makeMove(row, col, letter);
                    child.change(row,col);
                    children.add(child);
                }
            }
        }
        return children;
    }

    public void change(int row, int col){
        int opletter;
        int i;
        if(gameboard[row][col]==X) {
            opletter = O;
        }
        else{
            opletter= X;
        }

        if(LegalMove(row,col)) {
            i = 1;
            //search right
            while (col + i < 7 && gameboard[row][col +i] == opletter) {
                if (gameboard[row][col + i + 1] == lastLetterPlayed) {
                    for (int j = col + i; j >= col + 1; j--) {
                        gameboard[row][j] = lastLetterPlayed;
                    }
                }
                i++;
            }
            i = 1;
            //search left
            while (col - i > 0 && gameboard[row][col - i] == opletter) {
                if (gameboard[row][col - i - 1] == lastLetterPlayed) {
                    for (int j = col - i; j <= col - 1; j++) {
                        gameboard[row][j] = lastLetterPlayed;
                    }
                }
                i--;
            }
            i = 1;
            //search down
            while (row + i < 7 && gameboard[row + i][col] == opletter) {
                if (gameboard[row + i + 1][col] == lastLetterPlayed) {
                    for (int j = row + i; j >= row + 1; j--) {
                        gameboard[j][col] = lastLetterPlayed;
                    }
                }
                i++;
            }
            i = 1;
            //search up
            while (row - i> 0  && gameboard[row - i][col] == opletter) {
                if (gameboard[row -i-1][col] == lastLetterPlayed) {
                    for (int j = row - i; j <= row - 1; j++) {
                        gameboard[j][col] = lastLetterPlayed;
                    }
                }
                i--;
            }
            i = 1;
            //search right up diag
            while (col + i < 7 && row - i > 0 && gameboard[row - i][col + i] == opletter) {
                if (gameboard[row - i - 1][col + i + 1] == lastLetterPlayed) {
                    int z = i;
                    for (int j = row - i; j <= row - 1; j++) {
                        gameboard[j][col + z] = lastLetterPlayed;
                        z--;
                    }
                }
                i++;
            }
            i = 1;
            //search left up diag
            while (col - i > 0 && row - i > 0 && gameboard[row - i][col - i] == opletter) {
                if (gameboard[row - i - 1][col - i - 1] == lastLetterPlayed) {
                    int z = i;
                    for (int j = row - i; j <= row - 1; j++) {
                        gameboard[j][col - z] = lastLetterPlayed;
                        z--;
                    }
                }
                i++;
            }
            //search down left diag
            while (col - i > 0 && row + i < 7 && gameboard[row + i][col - i] == opletter) {
                if (gameboard[row + i + 1][col - i - 1] == lastLetterPlayed) {
                    int z = i;
                    for (int j = col - i; j <= col - 1; j++) {
                        gameboard[row + z][j] = lastLetterPlayed;
                        z--;
                    }
                }
                i++;

            }
            //search down right diag
            while (col + i < 7 && row + i <7 && gameboard[row + i][col + i] == opletter) {
                if (gameboard[row + i + 1][col + i + 1] == lastLetterPlayed) {
                    int z = i;
                    for (int j = row + i; j >= row + 1; j--) {
                        gameboard[j][col + z] = lastLetterPlayed;
                        z--;
                    }
                }
                i++;
            }
        }

    }

    //Prints the board
    public void print() {
        System.out.println("col     0 1 2 3 4 5 6 7 ");
        System.out.println("        _______________");
        for (int row = 0; row < 8; row++) {
            System.out.print("*" + "row " + (row) + " |");
            for (int col = 0; col < 8; col++) {
                switch (gameboard[row][col]) {
                    case X:
                        System.out.print("X ");
                        break;
                    case O:
                        System.out.print("O ");
                        break;
                    case EMPTY:
                        System.out.print("- ");
                        break;
                    default:
                        break;
                }
            }
            System.out.println(" |*");
        }
        System.out.println("        _______________");
    }

    public void End() {
        int counterX = 0;
        int counterO = 0;
        for (int row = 0; row < 8; row++) {
            for (int colum = 0; colum < 8; colum++) {
                if (gameboard[row][colum]==X) {
                    counterX++;
                }
                if (gameboard[row][colum]==O) {
                    counterO++;
                }
            }
        }
        if (counterX > counterO) {
            System.out.println("Winner is X with score " + counterX + ":" + counterO);

        } else if (counterX < counterO) {
            System.out.println("Winner is O with score " + counterO + ":" + counterX);

        } else if (counterX == counterO) {
            System.out.println("TIE " + counterO + ":" + counterX);

        }
    }

    private void Mostpieces() {
        for (int i = 0; i <8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j]==O) {
                    evaluation++;
                } else if (gameboard[i][j]==X) {
                    evaluation--;
                }
            }
        }
    }
    private void enemyadv() {

        if (gameboard[0][0] == EMPTY) {
            if (gameboard[0][1] == O) {
                evaluation += 20;
            } else if (gameboard[0][1] == X) {
                evaluation -= 20;
            }

            if (gameboard[1][1] == O) {
                evaluation += 20;
            } else if (gameboard[1][1] == X) {
                evaluation -= 20;
            }
            if (gameboard[1][0] == O) {
                evaluation += 20;
            } else if (gameboard[2][2] == X) {
                evaluation -= 20;
            }

        }
        if (gameboard[0][7] == EMPTY) {
            if (gameboard[0][6] == O) {
                evaluation += 20;
            } else if (gameboard[0][6] == X) {
                evaluation -= 20;
            }

            if (gameboard[1][6] == O) {
                evaluation += 20;
            } else if (gameboard[1][6] == X) {
                evaluation -= 20;
            }
            if (gameboard[1][7] == O) {
                evaluation += 20;
            } else if (gameboard[1][7] == X) {
                evaluation -= 20;
            }
        }
        if (gameboard[7][0] == EMPTY) {
            if (gameboard[6][0] == O) {
                evaluation += 20;
            } else if (gameboard[6][0] == X) {
                evaluation -= 20;
            }

            if (gameboard[6][1] == O) {
                evaluation += 20;
            } else if (gameboard[6][1] == X) {
                evaluation -= 20;
            }
            if (gameboard[7][1] == O) {
                evaluation += 20;
            } else if (gameboard[7][1] == X) {
                evaluation -= 20;
            }
        }
        if (gameboard[7][7] == EMPTY) {
            if (gameboard[6][6] == O) {
                evaluation += 20;
            } else if (gameboard[6][6] == X) {
                evaluation -= 20;
            }

            if (gameboard[7][6] == O) {
                evaluation += 20;
            } else if (gameboard[7][6] == X) {
                evaluation -= 20;
            }
            if (gameboard[6][7] == O) {
                evaluation += 20;
            } else if (gameboard[6][7] == X) {
                evaluation -= 20;
            }
        }
    }

    private void corners(){
        if(gameboard[0][0]==O){
            evaluation=evaluation-50;
        }
        if(gameboard[0][0]==X){
            evaluation=evaluation+50;
        }
        if(gameboard[7][7]==O){
            evaluation=evaluation-50;
        }
        if(gameboard[7][7]==X){
            evaluation=evaluation+50;
        }
        if(gameboard[1][7]==O){
            evaluation=evaluation-50;
        }
        if(gameboard[7][1]==O){
            evaluation=evaluation-50;
        }
        if(gameboard[1][7]==X){
            evaluation=evaluation+50;
        }
        if(gameboard[7][1]==X){
            evaluation=evaluation+50;
        }

    }

    public int evaluate() {
       Mostpieces();
       corners();
       enemyadv();
        return evaluation;
    }

    public boolean searchmoveR(int row, int col) {
        boolean move = false;
        int i = col;
        while (i<7 &&gameboard[row][i + 1] == lastLetterPlayed) {
            i++;

            if (i < 7) {
                if (gameboard[row][i + 1] != lastLetterPlayed && gameboard[row][i + 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveL(int row, int col) {
        boolean move = false;
        int i = col;
        while (i>0 && gameboard[row][i - 1] == lastLetterPlayed) {
            i--;


            if (i > 0) {
                if (gameboard[row][i - 1] != lastLetterPlayed && gameboard[row][i - 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveUP(int row, int col) {
        boolean move = false;
        int i = row;
        while (i>0 &&gameboard[i-1][col] == lastLetterPlayed) {
            i--;

            if (i > 0) {
                if (gameboard[i - 1][col] != lastLetterPlayed && gameboard[i - 1][col] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveDOWN(int row, int col) {
        boolean move = false;
        int i = row;
        while (i<7 && gameboard[i+1][col] == lastLetterPlayed) {
            i++;

            if (i < 7) {
                if (gameboard[i + 1][col] != lastLetterPlayed && gameboard[i + 1][col] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveDiagLUP(int row, int col) {
        boolean move = false;
        int i = row;
        int j = col;
        while (i>0 && j>0 && gameboard[i-1][j-1] == lastLetterPlayed) {
            i--;
            j--;

            if (i > 0 && j > 0) {
                if (gameboard[i - 1][j - 1] != lastLetterPlayed && gameboard[i - 1][j - 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveDiagRUP(int row, int col) {
        boolean move = false;
        int i = row;
        int j = col;
        while (i>0 && j<7 && gameboard[i-1][j+1] == lastLetterPlayed) {
            i--;
            j++;

            if (i > 0 && j < 7) {
                if (gameboard[i - 1][j + 1] != lastLetterPlayed && gameboard[i - 1][j + 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveDiagRDOWN(int row, int col) {
        boolean move = false;
        int i = row;
        int j = col;
        while (i<7 && j<7 &&gameboard[i+1][j+1] == lastLetterPlayed) {
            i++;
            j++;

            if (i < 7 && j < 7) {
                if (gameboard[i + 1][j + 1] != lastLetterPlayed && gameboard[i + 1][j + 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }

    public boolean searchmoveDiagLDOWN(int row, int col) {
        boolean move = false;
        int i = row;
        int j = col;
        while (i<7 && j>0 && gameboard[i+1][j-1] == lastLetterPlayed) {
            i++;
            j--;

            if (i < 7 && j > 0) {
                if (gameboard[i + 1][j - 1] != lastLetterPlayed && gameboard[i + 1][j - 1] != EMPTY) {
                    move = true;
                }
            }
        }
        return move;
    }
}

