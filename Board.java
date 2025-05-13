import java.util.*;
public class Board{
    int[][] board;
    int startingValue = 2;
    //Determines if a move can be made
    int count;

    public Board(){
        count = 0;
        board = new int[4][4];
        board[1][0]=2;
        board[1][1]=2;
        board[1][2]=2;
        board[1][3]=2;

        /*int c = 0;
        for(int row = 0;row<4;row++){
            for(int col = 0; col<4;col++){
                c++;
                board[row][col]=c;
            }
        }*/
        board[0][0] = 0;
        for(int i = 0;i<100;i++){
            addNumber();
            printBoard();
            while(!moveTraverse()){
                System.out.println("invalid");
            }
            //System.out.println(count);
            //printBoard();
            System.out.println("--------------------------------------------");
        }
    }


    //Adds a random position on the board to the startingVallue
    public void addNumber(){
        int row = (int) (Math.random()*board.length);
        int col = (int) (Math.random()*board.length);
        if(board[row][col]==0){
            board[ row ][ col ] = startingValue;
            //System.out.println("NUMBER ADDED AT "+ row + ", "+ col);
            checkLoss();
        }
        else{
            if(!checkLoss()){
                //System.out.println("RELOCATING");
                addNumber();
            }
        }
            
    }

    //Checks each spot to see if there is an adjacent empty spot or identity
    public boolean checkLoss(){
        //System.out.println(1);
        for(int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                if(row != board.length-1){
                    if(board[row+1][col] == 0 || board[row+1][col] == board[row][col] ){
                        //System.out.println(2+ " - "+row+", "+col);
                        return false;
                    }
                }
                if(row != 0){
                    if(board[row-1][col] == 0 || board[row-1][col] == board[row][col] ){
                        //System.out.println(3);
                        return false;
                    }
                }
                if(col != board[0].length-1){
                    //System.out.println(4);
                    if(board[row][col+1] == 0 || board[row][col+1] == board[row][col] ){
                        return false;
                    }
                }
                if(col != 0){
                    //System.out.println(5);
                    if(board[row][col-1] == 0 || board[row][col-1] == board[row][col] ){
                        return false;
                    }
                }
            }
        }
        System.out.println("You Lose");
        return true;
    }

    //Prints Board
    public void printBoard(){
        for(int[] row : board){
            for(int num : row){
                if(num == 0){
                    System.out.print(" \t");
                }else
                System.out.print(num+"\t");
            }
            System.out.println();
        }
    }

    //Takes user input and traverses accordingly, returns if valid move is made
    public boolean moveTraverse(){
        count=0;
        Scanner s = new Scanner(System.in);
        String m = s.next();
        //s.close();

        //Up - Row Major Forwards starting on 1
        if(m.equals("w")){
            for(int row = 1; row<board.length;row++){
                for(int col = 0; col<board[0].length;col++){
                    moveVertically(row,col,-1);

                }
            }
        }
        //Down Row Major Backwards starting on length-2
        if(m.equals("s")){
            for(int row = board.length-2;row>=0;row--){
                for(int col = board[0].length-1; col>=0;col--){
                    moveVertically(row, col, 1);
                }
            }
        }
        //Left - Column Major Forwards starting on 1
        if(m.equals("a")){
            for(int col = 1; col<board[0].length; col++){
                for(int row = 0; row<board.length;row++){
                    moveHorizontally(row, col, -1);
                }
            }
        }
        //Right - Column Major Backwards starting on length -2
        if(m.equals("d")){
            for(int col = board[0].length-2; col>=0; col--){
                for(int row = board.length-1; row>=0;row--){
                    moveHorizontally(row, col, 1);
                }
            }
        }
        if(count==0){
            return false;
        }else{
            return true;
        }
    }

    //Row, Col, Direction says where the next spot is checked, Up -1, Down 1
    //Condition statement - If we are going up then row shouldn't equal 0, If we are going down, row shouldn't equal the length and don't move empty spots
    public void moveVertically(int row, int col, int direction){
        
        if(row != -1-direction && row!= board.length-direction&& board[row][col]!=0){
            
            //If selected spot is 0
            if(board[row+direction][col] == 0){
                //Set above spot to value and delete original spot
                board[row+direction][col] = board[row][col];
                board[row][col] = 0;
                count++;
                //System.out.println(row+", "+col);
                moveVertically(row+direction,col,direction);
            }
            //If selected spot is equal
            else if(board[row+direction][col] == board[row][col]){
                //Set selected spot to value times 2 and delete original spot
                board[row+direction][col] = board[row][col]*2;
                board[row][col] = 0;
                count++;
                //System.out.println(row+", "+col);
            }
        }
    }

    public void moveHorizontally(int row, int col, int direction){
        //System.out.println(row +", "+col);
        if(col != -1-direction && col!= board[0].length-direction && board[row][col]!=0){
            
            //If selected spot is 0
            if(board[row][col+direction] == 0){
                //Set above spot to value and delete original spot
                board[row][col+direction] = board[row][col];
                board[row][col] = 0;
                count++;
                //aSystem.out.println(row+", "+col);
                moveHorizontally(row,col+direction,direction);
            }
            //If selected spot is equal
            else if(board[row][col+direction] == board[row][col]){
                //Set selected spot to value times 2 and delete original spot
                board[row][col+direction] = board[row][col]*2;
                board[row][col] = 0;
                count++;
                //System.out.println(row+", "+col);
            }
        
        }
    }
}