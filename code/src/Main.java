
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    	
        Scanner keyboard = new Scanner(System.in);
        char begin;
        int depth=0;
        
        System.out.println("Welcome to Reversi game!");
        do {
        	System.out.println("Please choose 'X' to play first, 'O' to play second or Z to exit the game: ");
        	
        	begin = keyboard.nextLine().charAt(0);
        	begin = Character.toUpperCase(begin);
        	
        	if(begin=='Z') {
        		System.out.println("Thank you for playing Reversi!\n exiting....");
        		System.exit(0);
        	}
        	else if(begin=='X' || begin=='O') {
        		System.out.println("Now Please insert the Max Depth:");
        		
        		while(true) {
            		try {
            			depth = keyboard.nextInt();
            			break;
            		} catch (Exception e) {
            			System.out.println("Wrong input.");
            			keyboard.next();//waits for the next INPUT
            		}
            	}
            	Play startGame = new Play();
            	startGame.newGame(begin, (begin=='X')?1:2, depth);
        	}
        	else {
        		System.out.println("Wrong input!");
        	}
        	
        }while(begin!='Z');
        keyboard.close();
	}  
}