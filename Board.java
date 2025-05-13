import java.util.*;
public class Board{
    int[][] board;
    int startingValue = 2;

    public Board(){
        board = new int[4][4];
        board[2][1]=2;
        board[1][1]=2;
        addNumber();
        printBoard();
        move();
        printBoard();
    }


    //Adds a random position on the board to the startingVallue
    public void addNumber(){
        board[ (int) (Math.random()*board.length) ][ (int) (Math.random()*board[0].length) ] = startingValue;
    }

    //Prints Board
    public void printBoard(){
        for(int[] row : board){
            for(int num : row){
                System.out.print(num+"\t");
            }
            System.out.println();
        }
    }

    //Takes user input and traverses accordingly
    public void move(){
        Scanner s = new Scanner(System.in);
        String m = s.next();
        if(m.equals("w")){
            for(int row = 1; row<board.length;row++){
                for(int col = 0; col<board[0].length;col++){
                    moveUp(row,col);

                }
            }
        }
    }

    public void moveUp(int row, int col){
        if(row != 0){
            //If above spot is 0
            if(board[row-1][col] == 0){
                //Set above spot to value and delete original spot
                board[row-1][col] = board[row][col];
                board[row][col] = 0;
                moveUp(row-1,col);
            }
            //If above spot is equal
            else if(board[row-1][col] == board[row][col]){
                //Set above spot to value times 2 and delete original spot
                board[row-1][col] = board[row][col]*2;
                board[row][col] = 0;
            }
        }
    }
}