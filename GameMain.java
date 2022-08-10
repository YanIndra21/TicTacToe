import java.util.Scanner;

public class GameMain {
   private Board board;
   private State currentState;
   private Seed  currentPlayer;

   private static Scanner in = new Scanner(System.in);

   public GameMain() {
      initGame();
      newGame();
      do {
         stepGame();
         board.paint();
         if (currentState == State.CROSS_WON) {
            System.out.println("Congratulations, 'X' won!");
         } else if (currentState == State.NOUGHT_WON) {
            System.out.println("Congratulations 'O' won!");
         } else if (currentState == State.DRAW) {
            System.out.println("It's Draw!");
         }
         currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
      } while (currentState == State.PLAYING); 
   }

   public void initGame() {
      board = new Board();  
   }

   public void newGame() {
      board.newGame(); 
      currentPlayer = Seed.CROSS; 
      currentState = State.PLAYING; 
   }

   public void stepGame() {
      boolean validInput = false; 
      do {
         String icon = currentPlayer.getIcon();
         System.out.print("Player '" + icon + "', enter your move (row[1-3] column[1-3]): ");
         int row = in.nextInt() - 1;  
         int col = in.nextInt() - 1;
         if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
               && board.cells[row][col].content == Seed.NO_SEED) {
            currentState = board.stepGame(currentPlayer, row, col);
            validInput = true; 
         } else {
            System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                  + ") is not valid. Try again...");
         }
      } while (!validInput);   
   }

   public static void main(String[] args) {
      new GameMain(); 
   }
}