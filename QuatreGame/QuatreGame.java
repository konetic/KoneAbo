package QuatreGame;
// This file contains a class specification and some core methods useful in implementing the game of Quatre.

import java.util.*;
/**
 * @author Tesfaye 
 * @version 3
 */

public class QuatreGame {
    // This instance variable specifies the board size
    public static final int SIZE = 10;

    //prints the overview of the game and read a series of inputs from a Scanner.
    public static void main(String[] args) {
        System.out.println("I'm an agent that plays (and wins!)the game of Quatre");
        System.out.println();
        Scanner console = new Scanner(System.in); //reads the inputs from the user/keyboard

        //This calls a method that introduces and explains the game-and maybe then ASKS the User 
        //  if they want to play before you start.
        intro();
        //testing the board as the player moves
        do {
            char[][] board = initializeBoard();
            int moves = 0; //moves initialized to zero
            boolean userTurn = yesTo("Do you want to go first?", console);
            boolean done = false;
            printBoard(board);
            //changed !done
            while (!done && moves < SIZE * SIZE) {
                moves++;
                if (userTurn) {
                    doUserMove(console, board);
                } else {
                    done = doComputerMove(board);
                }
                userTurn = !userTurn;
                printBoard(board);
            }
            if (moves == SIZE * SIZE) {
                System.out.println("The game is a DRAW!");
            }
            System.out.println();
        } while (yesTo("Want to play again?", console));
    }

    /**
     * get the user input and makes thier move
     * @param console for getting the user input
     * @param board the current state of the game
     *
     */
    public static void doUserMove(Scanner console, char[][] board) {
        int rowNumber = -1;  // set value initially to a number to exclude
        int columnNumber = -1;  // set value initially to a number  to exclude
        boolean validMove = false; // set value initially to a number  to test false
        do {
            boolean validRow = false;
            do {
                System.out.print("Enter row number (0-9): ");
                while(!console.hasNextInt()){
                    System.out.println("Sorry; the input you entered wasn't in the range of the size, please try again!");
                    System.out.print("Enter row number (0-9): ");
                    console.nextLine();
                }
                rowNumber = console.nextInt();
                if (rowNumber < 0 || rowNumber >= SIZE) {  //handle the condition
                    System.out.println("Sorry that wasn't a valid row, please try again!");

                }
                else{
                    validRow = true;
                }
                console.nextLine();
            }while(!validRow);
            boolean validColumn = false;
            do{

                System.out.print("Enter column number (0-9): "); // prompting the user to enter an input of 0-9

                while(!console.hasNextInt()){
                    System.out.println("Sorry; the input you entered wasn't in the range of the size, please try again!");
                    System.out.print("Enter column number (0-9): ");
                    console.nextLine();
                }
                columnNumber = console.nextInt();

                if (columnNumber < 0 || columnNumber >= SIZE) {
                    System.out.println("Sorry what you entered were not the right input, please try again! ");

                }
                else{
                    validColumn = true;
                }
                console.nextLine();
            }while (!validColumn);
            //loop and return true, if row number and column number are of not spaces and putting "P" 
            if (board [rowNumber] [columnNumber] == ' '){
                board [rowNumber] [columnNumber] = 'P';
                validMove = true;
            }
            else {
                System.out.println("Sorry what entered is not what supposed to be, please try again!");
            }
        }while (!validMove);
        // board[i-1][j-1] = '*''
    }

    /**
     * do the Computer Move /let the omputer take its trun
     * @param board two dimensional array represents the current state of the game
     * @return true if the game is over or false if the game is not over    
     */
    public static boolean doComputerMove(char[][] board) {
        //check to see if agent has 3 in row
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <=SIZE-3; j++){// j is the column
                if (board[i][j] == 'A' && board[i][j+1] == 'A' && board[i][j+2] == 'A'){

                    if (j >0 && board[i][j-1] == ' ' ){
                        board[i][j-1] = 'A';
                        return checkBoard(board);
                    }
                    if (j+3 < SIZE && board[i][j+3] == ' '){
                        board[i][j+3] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if agent has 3 in column
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <=SIZE-3; j++){ //j is the row
                if (board[j][i] == 'A' && board[j+1][i] == 'A' && board[j+2][i] == 'A'){

                    if (j >0 && board[j-1][i] == ' ' ){
                        board[j-1][i] = 'A';
                        return checkBoard(board);
                    }
                    if (j+3 < SIZE && board[j+3][i] == ' '){
                        board[j+3][i] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if player has 3 in row
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <=SIZE-3; j++){// j is the column
                if (board[i][j] == 'P' && board[i][j+1] == 'P' && board[i][j+2] == 'P'){

                    if (j >0 && board[i][j-1] == ' ' ){
                        board[i][j-1] = 'A';
                        return checkBoard(board);
                    }
                    if (j+3 < SIZE && board[i][j+3] == ' '){
                        board[i][j+3] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if player has 3 in column
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <=SIZE-3; j++){ //j is the row
                if (board[j][i] == 'P' && board[j+1][i] == 'P' && board[j+2][i] == 'P'){

                    if (j >0 && board[j-1][i] == ' ' ){
                        board[j-1][i] = 'A';
                        return checkBoard(board);
                    }
                    if (j+3 < SIZE && board[j+3][i] == ' '){
                        board[j+3][i] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }

        //check to see if player has 2 in row
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <=SIZE-2; j++){// j is the column
                if (board[i][j] == 'P' && board[i][j+1] == 'P'){

                    if (j >0 && board[i][j-1] == ' ' ){
                        board[i][j-1] = 'A';
                        return checkBoard(board);
                    }
                    if (j+2 < SIZE && board[i][j+2] == ' '){
                        board[i][j+2] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if player has 2 in column
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <=SIZE-2; j++){ //j is the row
                if (board[j][i] == 'P' && board[j+1][i] == 'P'){

                    if (j >0 && board[j-1][i] == ' ' ){
                        board[j-1][i] = 'A';
                        return checkBoard(board);
                    }
                    if (j+2 < SIZE && board[j+2][i] == ' '){
                        board[j+2][i] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }

        //check to see if agent has 2 in row
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <=SIZE-2; j++){// j is the column
                if (board[i][j] == 'A' && board[i][j+1] == 'A'){

                    if (j >0 && board[i][j-1] == ' ' ){
                        board[i][j-1] = 'A';
                        return checkBoard(board);
                    }
                    if (j+2 < SIZE && board[i][j+2] == ' '){
                        board[i][j+2] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if agent has 2 in column
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <=SIZE-2; j++){ //j is the row
                if (board[j][i] == 'A' && board[j+1][i] == 'A'){

                    if (j >0 && board[j-1][i] == ' ' ){
                        board[j-1][i] = 'A';
                        return checkBoard(board);
                    }
                    if (j+2 < SIZE && board[j+2][i] == ' '){
                        board[j+2][i] = 'A'; 
                        return checkBoard(board);
                    }
                }
            }
        }
        //check to see if agent has 2 in column
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <SIZE; j++){ //j is the column
                if (board[i][j] == 'A'){
                    //check to see if there is something on the left
                    if (j >0 && board[i][j-1] == ' ' ){
                        board[i][j-1] = 'A';
                        return checkBoard(board);
                    }
                    if (j+1 < SIZE && board[i][j+1] == ' '){
                        board[i][j+1] = 'A'; 
                        return checkBoard(board);

                    }
                    if (i>0 && board[i-1][j] == ' '){
                        board[i-1][j] = 'A';
                        return checkBoard(board);
                    }
                    if (i+1 < SIZE && board[i+1][j] == ' '){
                        board[i+1][j] = 'A'; 
                        return checkBoard(board);

                    }
                }
            }
        }
        //
        boolean validMove = false; //testing the validMove
        Random r = new Random(); // constructing random
        do{

            int row = r.nextInt(SIZE);
            int column = r.nextInt(SIZE);
            if (board [row] [column] == ' '){
                board [row] [column] = 'A';
                validMove = true;
            }
        }

        while (!validMove);  

        return checkBoard(board);
    }
/**
 * do the check board
 * @param board do row test if the player or the agent wins
 * @return true when the player wins
 * 
 */

    public static boolean checkBoard(char[][] board) {
//checl for rows
    //in a for loop with a control variable that takes on the values 0 to 9
    //check the board if the game is over. while the the row remains constant, the user adds one column more each time he/she plays
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                if (board[i][j]!= ' ' && (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3])){
                    if (board[i][j] == 'P'){
                        System.out.println("You win!!");
                    }
                    else{
                        System.out.println("The Agent wins!");
                    }
                    return true;

                }

            }
        }
        //in a for loop with a control variable that takes on the values 0 to 9
        //check the board if the game is over. while the the column remains constant, the user adds one row more each time when he/she plays for four consecutive
        //rows
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                if (board[j][i]!= ' ' && (board[j][i] == board[j+1][i] && board[j][i] == board[j+2][i] && board[j][i] == board[j+3][i])){
                    if (board[j][i] == 'P'){
                        System.out.println("You win!!");
                    }
                    else{
                        System.out.println("The Agent wins!");
                    }
                    return true;

                }

            }
        }
        //check the board if the game is over while either of the players get 4 straight grids of same letter diagonally like second and fourth quadrant in the x y axis
        for (int i = 0; i <= SIZE-4; i++){
            for (int j =0; j <=SIZE-4; j++){
                if (board[i][j]!= ' ' && (board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3])){
                    if (board[i][j] == 'P'){
                        System.out.println("You win!!");
                    }
                    else{
                        System.out.println("The Agent wins!");
                    }
                    return true;

                }

            }
        }

        //check the board if the game is over while either of the players get 4 straight grids of same letter diagonally like first and third quadrant in the x y axis
        for (int i = 0; i <= SIZE-4; i++){
            for (int j =3; j <=SIZE-1; j++){
                if (board[i][j]!= ' ' && (board[i][j] == board[i+1][j-1] && board[i][j] == board[i+2][j-2] && board[i][j] == board[i+3][j-3])){
                    if (board[i][j] == 'P'){
                        System.out.println("You win!!");
                    }
                    else{
                        System.out.println("The Agent wins!");
                    }
                    return true;

                }
            }
        }
        //test if the game is over ahead of all spaces/rows are filled. In other words while two spaces aren't filled
        boolean gameOver = true;
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                char c = ' ';
                if (board[i][j] != ' '){
                    c = board[i][j];
                }

                if (board[i][j+1] != ' ' && board[i][j+1] != c && c != ' '){
                    continue;
                }
                else if (board[i][j+1] !=  ' '){
                    c =  board[i][j+1]; 
                }
                if (board[i][j+2] != ' ' && board[i][j+2] != c && c != ' '){
                    continue;
                }
                else if (board[i][j+2] !=  ' '){
                    c =  board[i][j+2]; 
                }
                if (board[i][j+3] != ' ' && board[i][j+3] != c && c != ' '){
                    continue;
                }
                gameOver = false;
                break;
            }
            if (!gameOver){
                break;
            }
        }
        //test if the game is over ahead of all spaces/columns are filled. In other words while two spaces aren't filled
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                char c = ' ';
                if (board[j][i] != ' '){
                    c = board[j][i];
                }

                if (board[j+1][i] != ' ' && board[j+1][i] != c && c != ' '){
                    continue;
                }
                else if (board[j+1][i] !=  ' '){
                    c =  board[j+1][i]; 
                }
                if (board[j+2][i] != ' ' && board[j+2][i] != c && c != ' '){
                    continue;
                }
                else if (board[j+2][i] !=  ' '){
                    c =  board[j+2][i]; 
                }
                if (board[j+3][i] != ' ' && board[j+3][i] != c && c != ' '){
                    continue;
                }
                gameOver = false;
                break;
            }
            if (!gameOver){
                break;
            }
        }
        //test if the game is over ahead of all spaces diagonally are not filled on the second and fourth quadrant of x/y axis. In other words while two spaces aren't filled
                for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                char c = ' ';
                if (board[i][j] != ' '){
                    c = board[i][j];
                }

                if (board[i+1][j+1] != ' ' && board[i+1][j+1] != c && c != ' '){
                    continue;
                }
                else if (board[i+1][j+1] !=  ' '){
                    c =  board[i+1][j+1]; 
                }
                if (board[i+2][j+2] != ' ' && board[i+2][j+2] != c && c != ' '){
                    continue;
                }
                else if (board[i+2][j+2] !=  ' '){
                    c =  board[i+2][j+2]; 
                }
                if (board[i+3][j+3] != ' ' && board[i+3][j+3] != c && c != ' '){
                    continue;
                }
                gameOver = false;
                break;
            }
            if (!gameOver){
                break;
            }
        }
        
        //test if the game is over ahead of all spaces diagonally are not filled on the first and third quadrant of x/y axis. In other words while two spaces aren't filled
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                char c = ' ';
                if (board[j][i] != ' '){
                    c = board[j][i];
                }

                if (board[j-1][i-1] != ' ' && board[j-1][i-1] != c && c != ' '){
                    continue;
                }
                else if (board[j-1][i-1] !=  ' '){
                    c =  board[j-1][i-1]; 
                }
                if (board[j+2][i] != ' ' && board[j+2][i] != c && c != ' '){
                    continue;
                }
                else if (board[j-2][i-2] !=  ' '){
                    c =  board[j-2][i-2]; 
                }
                if (board[j-3][i-3] != ' ' && board[j-3][i-3] != c && c != ' '){
                    continue;
                }
                gameOver = false;
                break;
            }
            if (!gameOver){
                break;
            }
        }

        System.out.println("game over is " + gameOver);
        return gameOver ;

    }

    /**
     * makes a new board, fills the board with spaces and returns the board
     * 
     * @return a board of two dimentional arrays of charactors
     * 
     */

    public static char[][] initializeBoard() {
        char[][] result = new char[SIZE][SIZE]; //construct an array of char SIZE
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = ' ';
            }
        }
        return result; //returns a reference to the array that it constructs
    }

    /**
     * draws a line of dashes
     * 
     */
    public static void drawLine() {
        for (int i = 0; i <= 4 * SIZE; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * prints the board
     * @param board the board to print
     * 
     *     
     */
    public static void printBoard(char[][] board) {
        drawLine();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            drawLine();
        }
        System.out.println();
    }
    //this is an introduction call to let the player know that the nature of the game
    public static void intro(){
        System.out.println( "This game is tough, do you want to start first?");
    }

    /**
     * make yes to move to the next
     * @param prompt to construct a String for each input and to write a separate method for processing a data lower case:
     * @param console finds the next inputs
     * @return y the response 
     */
    public static boolean yesTo(String prompt, Scanner console) {
        System.out.print(prompt + " ");
        String response = console.next().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " ");
            response = console.next().toLowerCase();
        }
        return response.equals("y");
    }

}