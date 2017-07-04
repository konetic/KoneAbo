package extremeScarface;

import java.util.*;
/**
 * This program allows the Scarface to play a game of Quatre against ABC. The object of the game is to get four in a row (or column).
 * 
 * In this version of the program the agent should first attempt to win the
 * game. If this isn't possible it should attempt to block any opponents three in a row. If this isn't possible the agent will attempt to block any opponents
 * two-in-a-rows. If this isn't possible the agent will attempt to add on to any two-in-a-rows it has active on the game board. If this isn't possible the 
 * agent will move randomly
 * 
 * After 30 plays against Scarface; I won all 30 plays. This is because, I made the play harder to the opponent
 * 
 *  TLZ NOTES:  The board index used here STARTS with '1' for rows (x coord) & 1 for columns (y coord).
 *            
 *   ==> Meet AGENT SCARFACE  <===
 * @author Tesfaye
 * @version 3
 */

public class Scarface {
    // This instance variable specifies the board size
    /** A class constant integer representing the size of the game board*/
    public static final int SIZE = 10;
    /** A class constant char representing the character placeholder of the computer */
    public static final char comp = 'C';
    /** A class constant Random object used for generating random board moves by the computer */
    public static final Random r = new Random();

    /** Main method
     */
    public static void main(String[] args) {
        System.out.println("I'm an agent that plays (and wins!)the game of Quatre");
        System.out.println();
        Scanner console = new Scanner(System.in);
        // Be sure you call a method that introduces and explains the game-and maybe then ASKS the User 
        //  if he/she wants to play first.
        char name = Intro(console);
        boolean userTurn = yesTo("Do you want to go first? ('y' or 'n'): ", console);
        // boolean userTurn = yesTo("Do you want to go first?", console);
        boolean suppress = yesTo("Do you want to Suppress output?", console);
        System.out.println("How many rounds do you want to play?");
        int maxRounds = console.nextInt();
        int rounds = 0;
        //testing the board as the player moves
        do {
            rounds++;
            char[][] board = initializeBoard();
            int moves = 0; //moves initialized to zero

            boolean done = false;
            boolean four = false;
            if (!suppress){

                printBoard(board);
            }
            //changed !done
            while (!done && moves < SIZE * SIZE) {
                four = false;
                moves++;
                if (userTurn) {

                    doComputerMove2(board, name);
                } else {
                    doComputerMove(board, name);
                }
                //determines after each move if the game should continue (i.e. did the player win?)
                if (userTurn) {
                    four = four_in_row(board, name);
                    if (four == true) {
                        System.out.println(name + " WINS!");
                        done = true;
                    }
                }else {
                    four = four_in_row(board, comp);
                    if (four == true) {
                        System.out.println(comp + " WINS )':");
                        done = true;
                    }
                }
                userTurn = !userTurn;
                if (!suppress || moves < 3){                    
                    printBoard(board);
                }
            }

            if (suppress){
                System.out.println(rounds);
                printBoard(board);
            }
            if (moves == SIZE * SIZE) {
                System.out.println("The game is a DRAW!");
            }
            System.out.println();
        }
        //while (yesTo("Want to play again?", console));
        while(rounds < maxRounds);
    }

    /** A method that prints the introduction greeting, and asks the user for a placeholder letter representing their name
     * @param console   A scanner object for console input
     * @return name     A char representing the placeholder letter of the user
     * 
     */
    public static char Intro(Scanner console) {
        System.out.println("This game is Called Quatre!");
        System.out.println();
        System.out.println("The goal is to place stones on the board, and get four in a row or column");
        System.out.println();
        System.out.println("What is the first letter of your name?: ");
        String input = console.next();
        //makes sure user only enters one letter 
        while ( input.length() > 1) {
            System.out.println("Please enter only one letter");
            input = console.next();
        }
        char name = input.charAt(0);
        return name;
    }

    /** Method that is in charge of taking input from the user when it is their turn. Asks the user where they want to put their stone. Uses input
     * validation to check user reply for validity. No return.
     * 
     * @param console   A scanner object used to get user input
     * @param board     An array representing the game board
     * @param name  A char representing the placeholder letter of the user
     */
    public static void doUserMove(Scanner console, char[][] board, char name) {
        System.out.println("You're move! Enter Row and Column number");
        int row = 3;
        int column = 3;
        System.out.println("Row?");
        do {
            if (row > 10 || row < 1) {
                System.out.println("Please enter a valid integer number: ");
            }
            while (!console.hasNextInt() ) {
                System.out.println("Please enter a valid integer number: ");
                console.next(); 
            }
            row = console.nextInt();
        } while (row > 10 || row < 1); 
        row -= 1;
        System.out.println("Column?: ");
        do {
            if (column > 10 || column < 1) {
                System.out.println("Please enter a valid integer number: ");
            }
            while (!console.hasNextInt() ) {
                System.out.println("Please enter a valid integer number: ");
                console.next(); 
            }
            column = console.nextInt();
        } while (column > 10 || column < 1);
        column -= 1;
        while (board[row][column] == name || board[row][column] == comp) {
            System.out.println("There is already a piece there, please try again") ;
            System.out.println("Row?: ");
            row = console.nextInt();
            row -= 1;
            System.out.println("Column?: ");
            column = console.nextInt();
            column -= 1;
        }

        board[row][column] = name;

    }

    /** This method gathers information about the state of the board, and facilitates the computers move based on this. It then
     *  passes this information on to the appropriate method to complete the move (if needed).
     * 
     * @param board     An array representing the game board
     * @param name  A char representing the placeholder letter of the user. 
     * @return false 
     */

    public static boolean doComputerMove3(char[][] board, char name) 
    {

        Random rand = new Random();
        int row = rand.nextInt(10);//getting random number for row and column from 0-9
        int column = rand.nextInt(10);
        if(board[row][column] == ' ')//checking if input number is from 0-9
        {
            board[row][column] = name;
        }
        // return ifWon(board);
        return false;
    }

    /** This method gathers information about the state of the board, and facilitates the computers move based on this. It then
     *  passes this information on to the appropriate method to complete the move (if needed). No return.
     * 
     * @param board     An array representing the game board
     * @param name  A char representing the placeholder letter of the user. 
     */
    public static void doComputerMove(char[][] board, char name) {
        //determind if the user or bot has two in a row
        char user_two = two_in_row(board, name);
        char bot_two = two_in_row(board, comp);
        // determine if the user has a current two in row, or in a column
        boolean user_row_two = false;
        boolean bot_row_two = false;
        //System.out.println(bot_row_two);
        if (user_two == 'a') {
            user_row_two = true;
        }
        if (bot_two == 'a') {
            bot_row_two = true;
        }
        //determine if the user has a current three in a row or in a column
        char user_three = three_in_row(board, name);
        char bot_three = three_in_row(board, comp);
        // variable to determine if user has three in a row or a column. Only used when it is one or the other.
        // true if the user has three in a row, false if user has three in a column consecutively.
        boolean user_row = false;
        boolean bot_row = false;
        if (user_three == 'a') {
            user_row = true;}

        //determind if bot has a current three in a row or column

        if (bot_three == 'a') {
            bot_row = false;}

        if ((bot_three == 'a' || bot_three == 'b' || bot_three == 'c') || (user_three == 'a' || user_three == 'b' || user_three == 'c')) {
            doComputerMoveSpecialThree(board, name, user_three, bot_three, user_row, bot_row, user_row_two, bot_row_two);
        }
        else if ((bot_two == 'a' || bot_two == 'b' || bot_two == 'c') || (user_two == 'a' || user_two == 'b' || user_two == 'c')) {
            doComputerMoveSpecialTwo(board, name, user_two, bot_two, user_row_two, bot_row_two);
        }
        // if neither the bot nor the user have three in a row or column the bot moves randomly
        else if (user_three == 'd' && bot_three == 'd' && bot_two == 'd' && user_two == 'd') {
            int row = r.nextInt(10);
            int column = r.nextInt(10);
            while (!move_possible(board, row, column, name)) {
                row = r.nextInt(10);
                column = r.nextInt(10);}

            board[row][column] = comp;
        }
    }

    /** If the computer detects that it, or the user has a two in a row scenario, control of the game board is passed to this method. Does not return
     * a value.
     * 
     * @param board     an array representing the game board
     * @param name  a char which is the user's placeholder name
     * @param user_two  a char representing what kind of two in a row scenario the user is in.
     * @param bot_two   a char representing what kind of two in a row scenario the bot is in.
     * @param user_row_two  a boolean value determining if the two in a row for the user is in a row or column
     * @param bot_row_two   a boolean value determining if the two in a row for the bot is in a row or column
     */
    public static void doComputerMoveSpecialTwo(char[][]board, char name, char user_two, char bot_two, boolean user_row_two, boolean bot_row_two) {
        if ((user_two == 'a' || user_two == 'b' || user_two == 'c')) {
            int[] location = move_location_two(board, name, user_row_two);
            if (location[2] == -2) {
                location = move_location_two(board, comp, bot_row_two);
            }
            board[location[0]][location[1]] = comp;
        }

        else if ((bot_two == 'a' || bot_two == 'b' || bot_two == 'c')) {
            int[] location = move_location_two(board, comp, bot_row_two);
            board[location[0]][location[1]] = comp;
        }
    }

    /** If the computer detects that it, or the user has a three in a row scenario, control of the game board is passed to this method. Does not return
     * a value.
     * 
     * @param board     an array representing the game board
     * @param name  a char which is the user's placeholder name
     * @param user_three    a char representing what kind of three in a row scenario the user is in.
     * @param bot_three     a char representing what kinf of three in a row scenario the bot is in.
     * @param user_row  a boolean value determining if the three in a row for the user is in a row or column
     * @param bot_row   a boolean value determining if the three in a row for the bot is in a row or column
     * @param user_row_two  a boolean value determining if the two in a row for the user is in a row or column
     * @param bot_row_two   a boolean value determining if the two in a row for the bot is in a row or column
     */
    public static void doComputerMoveSpecialThree(char[][]board, char name, char user_three, char bot_three, boolean user_row, boolean bot_row, boolean
    user_row_two, boolean bot_row_two) {

        // if the user has three in a row, or a column, but the bot doesn't this pathway is activated
        if ((user_three == 'a' || user_three == 'b') && bot_three == 'd') {
            int[] location = move_location(board, name, user_row);
            if (location[2] == -2) {
                location = move_location_two(board, name, user_row_two);
            }
            board[location[0]][location[1]] = comp;}

        // if the bot has three in a row, or a column, and the user isn't about to win, this pathway is activated
        else if ((bot_three == 'a' || bot_three == 'b') && user_three == 'd') {
            int[] location = move_location(board, comp, bot_row);
            if (location[2] == -2) {
                location = move_location_two(board, comp, bot_row_two);
            }
            board[location[0]][location[1]] = comp;}
        // if the bot has three in a row, or a column, and the user also has three in a row or column
        // the bot should first try to win, and then if not possible, block the user win. If it is not 
        // possible to do either, the computer moves randomly

        else if ((bot_three == 'a' || bot_three == 'b') && (user_three == 'a' || user_three == 'b')) {
            int[] location = move_location_special(board, comp, bot_row);
            if (location[0] == -1) {
                location = move_location_special(board, name, user_row);
                if (location[0] == -1) {
                    location = move_location_two(board, comp, bot_row_two);}
            }   
            board[location[0]][location[1]] = comp;
        }

        // if the user has three in a column and in a row, and the bot doesn't have three in a row or column
        // this pathway is activated
        else if (user_three == 'c' && bot_three == 'd'){
            int[] location = move_location_special(board, name, user_row);
            if (location[0] == -1) {
                user_row = !user_row;
                location = move_location_special(board, name, user_row);
                if (location[0] == -1) {
                    location = move_location_two(board, comp, bot_row_two);}
            }
            board[location[0]][location[1]] = comp;
        }
        // if the user does not have three but the bot has three in a column and in a row this pathway is activated
        else if (user_three == 'd' && bot_three == 'c') {
            int[] location = move_location_special(board, comp, bot_row);
            if (location[0] == -1) {
                bot_row = !bot_row;
                location = move_location_special(board, comp, bot_row);
                if (location[0] == -1) {
                    location = move_location_two(board, comp, bot_row_two);}
            }
            board[location[0]][location[1]] = comp;
        }
        // if the user has three in a row or column, and the bot has three in a row and column this pathway is activated
        else if ((user_three == 'a' || user_three == 'b') && bot_three == 'c') {
            int[] location = move_location_special(board, comp, bot_row);
            if (location[0] == -1) {
                bot_row = !bot_row;
                location = move_location_special(board, comp, bot_row);
                if (location[0] == -1) {
                    location = move_location_two(board, name, user_row_two);
                }
            }
            board[location[0]][location[1]] = comp;
        }
        // if the user has three in a row, and the bot has three in a column or row
        else if ((user_three == 'c') && (bot_three == 'b' || bot_three == 'a')) {
            int[] location = move_location_special(board, name, user_row);
            if (location[0] == -1) {
                user_row = !user_row;
                location = move_location_special(board, name, user_row);
                if (location[0] == -1) {
                    location = move_location_two(board, comp, bot_row_two);
                }
            }
            board[location[0]][location[1]] = comp;
        }
        // if both the user and bot have three in a row and column
        else if (user_three == 'c' && bot_three == 'c') {
            int[] location = move_location_special(board, comp, bot_row);
            if (location[0] == -1) {
                bot_row = !bot_row;
                location = move_location_special(board, comp, bot_row);
                if (location[0] == -1) {
                    location = move_location_special(board, name, user_row);
                    if (location[0] == -1) {
                        user_row = !user_row;
                        location = move_location_special(board, name, user_row);
                        if (location[0] == -1) {
                            location = move_location_two(board, comp, bot_row_two);
                        }
                    }

                }
            }
            board[location[0]][location[1]] = comp;
        }
    }

    /** A method that determines where the computer should place its stone if there is a two-in-a-row scenario. 
     * @param board     The array representing the game board
     * @param name  a char which is the user's placeholder name
     * @param row    a boolean variable telling the method if the user has two in a row or column (true for row, false for column)
     * @return move_coord    returns the coordinates of the next move the computer should make.
     */

    public static int[] move_location_two(char[][]board,char name, boolean row ) {
        char a = ' ';
        char b = ' ';
        int[] move_coord = new int[3];
        // for loop that returns the coordinates of where the computer needs to move to block user win,
        // or for the computer to win
        if (row == true) { 
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == name) {
                        a = board[i][j];
                        // catches a three in a row
                        if (a == name && b == name) {
                            move_coord[0] = i;
                            move_coord[1] = j + 1;
                            //makes sure move is possible, if not the computer trys to block move from othe side
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[1] = j - 2;
                                //if neither move is possible, continue the loop
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                            }
                            return move_coord;

                        }               
                    } else {
                        a = ' ';
                        b = ' ';
                    }
                    b = a; 
                    if (i == SIZE - 1 && j == SIZE - 1) {
                        move_coord[0] = r.nextInt(10);
                        move_coord[1] = r.nextInt(10);
                        // indicates the value returned is random
                        move_coord[2] = - 2;
                        while (!move_possible(board, move_coord[0], move_coord[1],name)) {
                            move_coord[0] = r.nextInt(10);
                            move_coord[1] = r.nextInt(10);
                        }
                        return move_coord;
                    }
                } 
                a = ' ';
                b = ' ';

            }
        }

        else {    
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[j][i] == name) {
                        a = board[j][i];
                        if (a == name && b == name) {
                            move_coord[0] = j + 1;
                            move_coord[1] = i;
                            int lol = move_coord[0] ;
                            //System.out.println(lol);
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[0] = j - 2;
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                            }
                            return move_coord;

                        }  
                    }
                    else {
                        a = ' ';
                        b = ' '; 
                    }
                    b = a;  
                    if (i == SIZE - 1 && j == SIZE - 1) {
                        move_coord[0] = r.nextInt(10);
                        move_coord[1] = r.nextInt(10);
                        while (!move_possible(board, move_coord[0], move_coord[1],name)) {
                            move_coord[0] = r.nextInt(10);
                            move_coord[1] = r.nextInt(10);
                        }
                        return move_coord;}
                } 
                a = ' ';
                b = ' ';

            }
        }
        return move_coord;
    }

    /** A method that determines where the computer should place its stone if there is a three-in-a-row scenario. 
     * @param board     The array representing the game board
     * @param name  a char which is the user's placeholder name
     * @param row    a boolean variable telling the method if the user has three in a row or column (true for row, false for column) false for column)
     * @return move_coord    returns the coordinates of the next move the computer should make.
     */

    public static int[] move_location(char[][]board,char name, boolean row ) {
        char a = ' ';
        char b = ' ';
        char c = ' ';
        int[] move_coord = new int[3];
        // for loop that returns the coordinates of where the computer needs to move to block user win,
        // or for the computer to win
        if (row == true) { 
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == name) {
                        a = board[i][j];
                        // catches a three in a row
                        if (a == name && b == name && c == name) {
                            move_coord[0] = i;
                            move_coord[1] = j + 1;
                            //makes sure move is possible, if not the computer trys to block move from othe side
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[1] = j - 3;
                                //if neither move is possible, continue the loop
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                            }
                            return move_coord;

                        }               
                    } else {
                        a = ' ';
                        b = ' ';
                        c = ' ';
                    }
                    c = b;
                    b = a; 
                    if (i == SIZE - 1 && j == SIZE - 1) {
                        move_coord[0] = r.nextInt(10);
                        move_coord[1] = r.nextInt(10);
                        move_coord[2] = -2;
                        while (!move_possible(board, move_coord[0], move_coord[1],name)) {
                            move_coord[0] = r.nextInt(10);
                            move_coord[1] = r.nextInt(10);
                        }
                        return move_coord;
                    }
                } 
                a = ' ';
                b = ' ';
                c = ' ';

            }
        }

        else {    
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[j][i] == name) {
                        a = board[j][i];
                        if (a == name && b == name && c == name) {
                            move_coord[0] = j + 1;
                            move_coord[1] = i;
                            int lol = move_coord[0] ;
                            // System.out.println(lol);
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[0] = j - 3;
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                            }
                            return move_coord;

                        }  
                    }
                    else {
                        a = ' ';
                        b = ' ';
                        c = ' '; 
                    }
                    c = b;
                    b = a;  
                    if (i == SIZE - 1 && j == SIZE - 1) {
                        move_coord[0] = r.nextInt(10);
                        move_coord[1] = r.nextInt(10);
                        while (!move_possible(board, move_coord[0], move_coord[1],name)) {
                            move_coord[0] = r.nextInt(10);
                            move_coord[1] = r.nextInt(10);
                        }
                        return move_coord;}
                } 
                a = ' ';
                b = ' ';
                c = ' ';
            }
        }
        return move_coord;
    }

    /** This method is a variation of the move_location method. It is used when the computer or user (or both) have three in a row AND column.
     *  The method returns the location of where the computer should move as an array. It returns an array of [-1][0] if move cannot be made.
     *  @param board    An array of the game board
     *  @param name    A character that tells the method which user 
     */
    public static int[] move_location_special(char[][]board,char name, boolean row ) {
        char a = ' ';
        char b = ' ';
        char c = ' ';
        int[] move_coord = new int[2];
        // for loop that returns the coordinates of where the computer needs to move to block user win,
        // or for the computer to win
        if (row == true) { 
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == name) {
                        a = board[i][j];
                        if (a == name && b == name && c == name) {
                            move_coord[0] = i;
                            move_coord[1] = j + 1;
                            int lol = move_coord[1] ;
                            //System.out.println(lol);
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[1] = j - 3;
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                                return move_coord;
                            }
                            return move_coord;

                        }               
                    } else {
                        a = ' ';
                        b = ' ';
                        c = ' ';
                    }
                    c = b;
                    b = a;
                    // if the end of the array is reached without finding a valid move the default [-1][0] is returned.
                    if (j == SIZE - 1 && i == SIZE - 1) {
                        move_coord[0] = -1;
                        move_coord[1] = 0;
                        return move_coord;
                    }
                } 
                a = ' ';
                b = ' ';
                c = ' ';
            }
        } else {    
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[j][i] == name) {
                        a = board[j][i];
                        if (a == name && b == name && c == name) {
                            move_coord[0] = j + 1;
                            move_coord[1] = i;
                            if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                move_coord[0] = j - 3;
                                if (!move_possible(board, move_coord[0], move_coord[1], name)) {
                                    continue;
                                }
                                return move_coord;
                            }
                            return move_coord;

                        }      
                    } else {
                        a = ' ';
                        b = ' ';
                        c = ' ';
                    }
                    c = b;
                    b = a;  
                    if (i == SIZE - 1 && j == SIZE -1) {
                        move_coord[0] = -1;
                        move_coord[1] = 0;
                        return move_coord;
                    }
                } 
                a = ' ';
                b = ' ';
                c = ' ';
            }
        }
        return move_coord;
    }

    /** This method determines if a given move is possible (i.e. if the spot is on the game board and 
     * not currently occupied)
     * 
     * @param board     The array representing the game board
     * @param row   an Int representing the row number of the move in question
     * @param column    an Int representing the column number of the move in question
     * @param name  a char which is the user's placeholder name
     */

    public static boolean move_possible(char[][]board, int row, int column, char name) {
        if (row < 0 || column < 0 || row > 9 || column > 9) {
            return false;
        }
        if (board[row][column] == name || board[row][column] == comp) {
            return false;
        }
        else {
            return true;
        }

    }

    /** This method determines if a player has three stones in a row 
     * @param board     The current game board 
     * @param name  The player character representer
     * @return a    returns a char 'a' if user currently has three in a row
     * @return b    returns a char 'b' if user currently has three in a column
     * @return c    returns a char 'c' if user has both three in a row and in a column
     * @return d    returns a char 'd' if user does not have 3 consecutive stones
     */
    public static char three_in_row(char[][] board, char name) {
        boolean three_in_row = false;
        boolean three_in_column = false;
        char a = ' ';
        char b = ' ';
        char c = ' ';

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == name) {
                    a = board[i][j];
                    if (a == name && b == name && c == name) {
                        three_in_row = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                    c = ' ';
                }
                c = b;
                b = a;
            } 
            a = ' ';
            b = ' ';
            c = ' ';
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[j][i] == name) {
                    a = board[j][i];
                    if (a == name && b == name && c == name) {
                        three_in_column = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                    c = ' ';
                }
                c = b;
                b = a;
            } 
            a = ' ';
            b = ' ';
            c = ' ';
        }

        if (three_in_row == true && three_in_column == true) {
            return 'c';
        }
        else if (three_in_row == true) {
            return 'a';
        }
        else if (three_in_column == true) {
            return 'b';
        }
        return 'd';
    }

    /** This method determines if a player has two stones in a row. 
     * @param board     The current game board 
     * @param name  The player character representer
     * @return a    returns a char 'a' if user currently has two in a row
     * @return b    returns a char 'b' if user currently has two in a column
     * @return c    returns a char 'c' if user has both two in a row and in a column
     * @return d    returns a char 'd' if user does not have two consecutive stones
     */
    public static char two_in_row(char[][]board, char name) {

        boolean two_in_row = false;
        boolean two_in_column = false;
        char a = ' ';
        char b = ' ';

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == name) {
                    a = board[i][j];
                    if (a == name && b == name) {
                        two_in_row = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                }
                b = a;
            } 
            a = ' ';
            b = ' ';
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[j][i] == name) {
                    a = board[j][i];
                    if (a == name && b == name) {
                        two_in_column = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                }
                b = a;
            } 
            a = ' ';
            b = ' ';
        }

        if (two_in_row == true && two_in_column == true) {
            return 'c';
        }
        else if (two_in_row == true) {
            return 'a';
        }
        else if (two_in_column == true) {
            return 'b';
        }
        return 'd';
    }

    /** This method checks to see if a player has four in a row or column, thus winnning the game.
     * @param board    The game board
     * @param name     The char representing the player that this method is checking for
     * @return a boolean value, True if the player has four in a row, false if the player does not.
     */
    public static boolean four_in_row(char[][] board, char name) {
        boolean four_in_row = false;
        boolean four_in_column = false;
        char a = ' ';
        char b = ' ';
        char c = ' ';
        char d = ' ';

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == name) {
                    a = board[i][j];
                    if (a == name && b == name && c == name && d == name) {
                        four_in_row = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                    c = ' ';
                    d = ' ';
                }
                d = c;
                c = b;
                b = a;
            } 
            a = ' ';
            b = ' ';
            c = ' ';
            d = ' ';
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[j][i] == name) {
                    a = board[j][i];
                    if (a == name && b == name && c == name && d == name) {
                        four_in_column = true;
                    }

                }
                else {
                    a = ' ';
                    b = ' ';
                    c = ' ';
                    d = ' ';
                }
                d = c;
                c = b;
                b = a;
            } 
            a = ' ';
            b = ' ';
            c = ' ';
            d = ' ';
        }

        if (four_in_row == true && four_in_column == true) {
            return true;
        }
        else if (four_in_row == true) {
            return true;
        }
        else if (four_in_column == true) {
            return true;
        }
        return false;
    }

    /**
     *  A method called at the beginning of the game to initialize the game board 
     *  @return result  An array representing the game board
     */
    public static char[][] initializeBoard() {
        char[][] result = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = ' ';
            }
        }
        return result;
    }

    /**
     *  A method that draws a line on the game board. No parameters or returns
     */
    public static void drawLine() {
        for (int i = 0; i <= 4 * SIZE; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     *  A method that prints the game board. no return
     * @param board     An array representing the game board      
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

    /**
     *  A method that asks the user if they want to go first or not, and makes sure the user types a valid answer
     * 
     * @param prompt    A string asking the user if they want to go first
     * @param console   The console scanner object
     * @return a boolean value, True if user wants to go first, False if user wants to defer first turn to the computer
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

    /**
     * This program was modified to make it harder to the opponent team by having a different form of approach. One, having x_xx and xx_x in
     * both the rows and columns the the player. The former approach fill in the first row and column and then jumped to third and fourth eventually fill in the
     * empty space to win by misguiding the opponent who is looking for three in rows and three in columns left and right. the same is to the second approach(xx_x)
     * 
     * @param borad
     * @param player
     * @return board
     * 
     */
    public static boolean doComputerMove2(char[][] board, char player) {
        Random r = new Random(); // constructing random
        if (r.nextInt(2) == 1){
            //check to see if agent has 3 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-3; j++){// j is the column
                    if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player){

                        if (j >0 && board[i][j-1] == ' ' ){
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+3 < SIZE && board[i][j+3] == ' '){
                            board[i][j+3] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
            //check to see if agent has 3 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-3; j++){ //j is the row
                    if (board[j][i] == player && board[j+1][i] == player && board[j+2][i] == player){

                        if (j >0 && board[j-1][i] == ' ' ){
                            board[j-1][i] = player;
                            return checkBoard(board);
                        }
                        if (j+3 < SIZE && board[j+3][i] == ' '){
                            board[j+3][i] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
        }
        else
        {
            //check to see if agent has 3 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-3; j++){ //j is the row
                    if (board[j][i] == player && board[j+1][i] == player && board[j+2][i] == player){

                        if (j >0 && board[j-1][i] == ' ' ){
                            board[j-1][i] = player;
                            return checkBoard(board);
                        }
                        if (j+3 < SIZE && board[j+3][i] == ' '){
                            board[j+3][i] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
            //check to see if agent has 3 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-3; j++){// j is the column
                    if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player){

                        if (j >0 && board[i][j-1] == ' ' ){
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+3 < SIZE && board[i][j+3] == ' '){
                            board[i][j+3] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
        }

        //check to see x_xx in row
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <SIZE-3; j++){ //j is the row
                if (board[i][j] == player && board[i][j+2] == player && board[i][j+3] == player && board[i][j+1] == ' ' ){
                    board[i][j+1] = player;
                    return checkBoard(board);
                }
            }
        }
        //check to see x_xx in column
        for (int i = 0; i < SIZE; i++){ // i is the column
            for (int j =0; j <SIZE-3; j++){ //j is the row
                if (board[j][i] == player && board[j+2][i] == player && board[j+3][i] == player && board[j+1][i] == ' ' ){
                    board[j+1][i] = player;
                    return checkBoard(board);
                }
            }
        }
        if (r.nextInt(2) == 1){

            //check to see if player has 3 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-3; j++){// j is the column
                    if (board [i][j] != ' ' && board [i][j] != player){ 
                        if (board[i][j] == board[i][j+1] && board [i][j] == board[i][j+2]){

                            if (j >0 && board[i][j-1] == ' ' ){
                                board[i][j-1] = player;
                                return checkBoard(board);
                            }
                            if (j+3 < SIZE && board[i][j+3] == ' '){
                                board[i][j+3] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }
            //check to see if player has 3 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-3; j++){ //j is the row
                    if (board [j][i] != ' ' && board [j][i] != player){ 
                        if (board[j][i] ==  board[j+1][i]  && board[j][i] == board[j+2][i]){

                            if (j >0 && board[j-1][i] == ' ' ){
                                board[j-1][i] = player;
                                return checkBoard(board);
                            }
                            if (j+3 < SIZE && board[j+3][i] == ' '){
                                board[j+3][i] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }

        }
        else
        {

            //check to see if player has 3 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-3; j++){ //j is the row
                    if (board [j][i] != ' ' && board [j][i] != player){ 
                        if (board[j][i] ==  board[j+1][i]  && board[j][i] == board[j+2][i]){

                            if (j >0 && board[j-1][i] == ' ' ){
                                board[j-1][i] = player;
                                return checkBoard(board);
                            }
                            if (j+3 < SIZE && board[j+3][i] == ' '){
                                board[j+3][i] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }
            //check to see if player has 3 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-3; j++){// j is the column
                    if (board [i][j] != ' ' && board [i][j] != player){ 
                        if (board[i][j] == board[i][j+1] && board [i][j] == board[i][j+2]){

                            if (j >0 && board[i][j-1] == ' ' ){
                                board[i][j-1] = player;
                                return checkBoard(board);
                            }
                            if (j+3 < SIZE && board[i][j+3] == ' '){
                                board[i][j+3] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }
        }
        if (r.nextInt(2) == 1){
            //check to see if player has 2 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-2; j++){// j is the column
                    if (board [i][j] != ' ' && board [i][j] != player){ 
                        if (board[i][j] == board[i][j+1]){

                            if (j >0 && board[i][j-1] == ' ' ){
                                board[i][j-1] = player;
                                return checkBoard(board);
                            }
                            if (j+2 < SIZE && board[i][j+2] == ' '){
                                board[i][j+2] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }

            //check to see if player has 2 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-2; j++){ //j is the row
                    if (board [j][i] != ' ' && board [j][i] != player){ 
                        if (board[j][i] == board[j+1][i]){

                            if (j >0 && board[j-1][i] == ' ' ){
                                board[j-1][i] = player;
                                return checkBoard(board);
                            }
                            if (j+2 < SIZE && board[j+2][i] == ' '){
                                board[j+2][i] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }
        }
        else
        {

            //check to see if player has 2 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-2; j++){ //j is the row
                    if (board [j][i] != ' ' && board [j][i] != player){ 
                        if (board[j][i] == board[j+1][i]){

                            if (j >0 && board[j-1][i] == ' ' ){
                                board[j-1][i] = player;
                                return checkBoard(board);
                            }
                            if (j+2 < SIZE && board[j+2][i] == ' '){
                                board[j+2][i] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }

            //check to see if player has 2 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-2; j++){// j is the column
                    if (board [i][j] != ' ' && board [i][j] != player){ 
                        if (board[i][j] == board[i][j+1]){

                            if (j >0 && board[i][j-1] == ' ' ){
                                board[i][j-1] = player;
                                return checkBoard(board);
                            }
                            if (j+2 < SIZE && board[i][j+2] == ' '){
                                board[i][j+2] = player; 
                                return checkBoard(board);
                            }
                        }
                    }
                }
            }
        }
        if (r.nextInt(2) == 1){
            //check to see if agent has 2 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-2; j++){// j is the column
                    if (board[i][j] == player && board[i][j+1] == player){

                        if (j >0 && board[i][j-1] == ' ' ){
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+2 < SIZE && board[i][j+2] == ' '){
                            board[i][j+2] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }

            //check to see if agent has 2 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-2; j++){ //j is the row
                    if (board[j][i] == player && board[j+1][i] == player){

                        if (j >0 && board[j-1][i] == ' ' ){
                            board[j-1][i] = player;
                            return checkBoard(board);
                        }
                        if (j+2 < SIZE && board[j+2][i] == ' '){
                            board[j+2][i] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
        }
        else
        {
            //check to see if agent has 2 in column
            for (int i = 0; i < SIZE; i++){ // i is the column
                for (int j =0; j <=SIZE-2; j++){ //j is the row
                    if (board[j][i] == player && board[j+1][i] == player){

                        if (j >0 && board[j-1][i] == ' ' ){
                            board[j-1][i] = player;
                            return checkBoard(board);
                        }
                        if (j+2 < SIZE && board[j+2][i] == ' '){
                            board[j+2][i] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
            //check to see if agent has 2 in row
            for (int i = 0; i < SIZE; i++){ // i is the row
                for (int j =0; j <=SIZE-2; j++){// j is the column
                    if (board[i][j] == player && board[i][j+1] == player){

                        if (j >0 && board[i][j-1] == ' ' ){
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+2 < SIZE && board[i][j+2] == ' '){
                            board[i][j+2] = player; 
                            return checkBoard(board);
                        }
                    }
                }
            }
        }

        //check to see if agent has 2f in column
        for (int i = 0; i < SIZE; i++){ // i is the row
            for (int j =0; j <SIZE; j++){ //j is the column
                if (board[i][j] == player){
                    int rn =r.nextInt(4);
                    if (rn==0){
                        //check to see left, right, up and down combinations
                        if (j >0 && board[i][j-1] == ' ' ){//left
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+1 < SIZE && board[i][j+1] == ' '){//right
                            board[i][j+1] = player; 
                            return checkBoard(board);

                        }
                        if (i>0 && board[i-1][j] == ' '){//up
                            board[i-1][j] = player;
                            return checkBoard(board);
                        }
                        if (i+1 < SIZE && board[i+1][j] == ' '){//down
                            board[i+1][j] = player; 
                            return checkBoard(board);

                        }  
                    }
                    else if (rn ==1)
                    {
                        //check to see left, up, right and down combinations
                        if (j >0 && board[i][j-1] == ' ' ){//left
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }

                        if (i>0 && board[i-1][j] == ' '){//up
                            board[i-1][j] = player;
                            return checkBoard(board);
                        }
                        if (j+1 < SIZE && board[i][j+1] == ' '){//right
                            board[i][j+1] = player; 
                            return checkBoard(board);

                        }
                        if (i+1 < SIZE && board[i+1][j] == ' '){//down
                            board[i+1][j] = player; 
                            return checkBoard(board);

                        }
                    }
                    else if (rn ==2)
                    {
                        //check to see left, right, up and down combinations
                        if (j >0 && board[i][j-1] == ' ' ){//left
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                        if (j+1 < SIZE && board[i][j+1] == ' '){//right
                            board[i][j+1] = player; 
                            return checkBoard(board);

                        }
                        if (i>0 && board[i-1][j] == ' '){//up
                            board[i-1][j] = player;
                            return checkBoard(board);
                        }
                        if (i+1 < SIZE && board[i+1][j] == ' '){//down
                            board[i+1][j] = player; 
                            return checkBoard(board);

                        }   
                    }
                    else {

                        //check to see if there is something on the left

                        if (j+1 < SIZE && board[i][j+1] == ' '){//right
                            board[i][j+1] = player; 
                            return checkBoard(board);

                        }
                        if (i>0 && board[i-1][j] == ' '){//up
                            board[i-1][j] = player;
                            return checkBoard(board);
                        }
                        if (i+1 < SIZE && board[i+1][j] == ' '){//down
                            board[i+1][j] = player; 
                            return checkBoard(board);

                        }
                        if (j >0 && board[i][j-1] == ' ' ){//left
                            board[i][j-1] = player;
                            return checkBoard(board);
                        }
                    }
                }
            }
        }

        boolean validMove = false; //testing the validMove

        do{

            int row = r.nextInt(SIZE);
            int column = r.nextInt(SIZE);
            if (board [row] [column] == ' '){
                board [row] [column] = player;
                validMove = true;
            }
        }

        while (!validMove);  

        return checkBoard(board);
    }

    /**
     * The boolean checkboard method returns wins if the condition met
     * @param board places win
     * @return true 
     */
    public static boolean checkBoard(char[][] board) {
        //check for rows
        //in a for loop with a control variable that takes on the values 0 to 9
        //check the board if the game is over. While the the row remains constant, the user adds one more column each time he/she plays
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j <=SIZE-4; j++){
                if (board[i][j]!= ' ' && (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3])){
                    System.out.println("Player "+board[i][j]+" wins!!");                      
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
                    System.out.println("Player "+board[j][i]+" wins!!");
                    return true;
                }

            }
        }

        return false;
    }

}
