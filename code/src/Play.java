import java.util.Scanner;

public class Play {

    Board board = new Board();

    public void newGame(char player, int turn, int depth) {
        boolean end = false;
        char cpu;
        if (player == 'X') {
            cpu = 'O';
        } else {
            cpu = 'X';
        }

        while (end) {
            if (board.isTerminal()) {
                board.print();
                board.End();
                break;
            }
            if (turn % 2 == 0) {
                cpuTurn(cpu, turn, depth);
                turn++;
            } else {
                playerTurn(player, turn);
                turn++;
            }
        }
    }

    private void playerTurn(char player, int turn) {
        int letter;
        if (player == 'O') {
            letter = Board.O;
        } else {
            letter = Board.X;

        }
        board.print();
        int y = 0;
        int j = 0;
        Scanner reader = new Scanner(System.in);
        while (true) {
            try {
               // System.out.println("Enter row and col if you dont have moves type 100 100");
                System.out.print("Round " + turn + "! Player turn.\nEnter row for " + player + ": ");
                y = reader.nextInt();
                System.out.print("Enter column for " + player + ": ");
                j = reader.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong Input");
            }
           // if(y==100 && j==100){
                //break;
            //}
            //else
                if (board.isValidMove(y, j)) {
                board.makeMove(y, j, letter);
                board.change(y, j);
                break;
            }
        }
    }

    private void cpuTurn(char cpu, int turn, int depth) {
    	
        int letter;
        if (cpu == 'O') {
            letter = Board.O;
        } else {
            letter = Board.X;
        }
        board.print();
        System.out.println("Round " + turn + "\nCPU's turn\nThinking.....");
        Computer pc = new Computer(depth, cpu);
        while (true) {

        	Move move = pc.MiniMax(board);
        	//if(move.getRow()==100 && move.getCol()==100){
        	    //System.out.println("i cant play anything");
        	    //break;
            //}
            if (board.isValidMove(move.getRow(), move.getCol())) {
            	board.makeMove(move.getRow(), move.getCol(), letter);
                board.change(move.getRow(), move.getCol());
                break;
            }
        }
    }
}