package extremeScarface;

import java.util.*;
/**
 * This program allows the Scarface to play a game of Quatre against ABC. The object of the game is to get four in a row (or column).
 * This code creates a simple text game that looks like the game connect 4.
 * This code will be focusing on using 2-D arrays.
 * 
 * After 30 plays against Scarface; I won all 30 plays. This is because, I made the play harder to the opponent
 * 
 *  NOTE:  The board index used here STARTS with '0' for rows (x coord) & 0 for columns (y coord).
 * 
 *   ==> Meet AGENT EXTREME  <===
 * @author Tesfaye
 * @version 3
 */

public class Extreme {
    // This instance variable specifies the board size
    public static final int SIZE = 10;

    /**
     * The main method will be running method functions.
     */
    public static void main(String[] args) 
    {
        Scanner console = new Scanner(System.in);
        printIntro(console);
        char name = 'B';
        if(yesTo("Do you want play against me? ", console))//asking user if they want to play or not.
        {
            System.out.println();
            System.out.println("Well then, hello there! I'm an agent that plays (and wins!)the game of Quatre");
            boolean userTurn = yesTo("Do you want to go first?", console);
            boolean suppress = yesTo("Do you want to Suppress output?", console);
            System.out.println("How many rounds do you want to play?");
            int maxRounds = console.nextInt();
            int rounds = 0;

            //testing the board as the player moves
            do 
            {
                rounds++;
                char[][] board = initializeBoard();
                int moves = 0;
                boolean done = false;
                if (!suppress){
                    printBoard(board);
                }
                //changed !done
                while (!done && moves < SIZE * SIZE) //checking if the user said n and if move is still below 100
                {
                    moves++;
                    if (userTurn) //when the users turn, it goes to doUserMove method.
                    {
                        done = doComputerMove2(board,name);

                    } else //when the Agent turn, it goes to doComputerMove method.
                    {
                        done = doComputerMove(board);
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
                if (moves == SIZE * SIZE)//if there is no more space, it stops the game and declare a draw.
                {
                    System.out.println("The game is a DRAW!");
                }
                System.out.println();
            } 
            // while (yesTo("Want to play again?", console));//when game is over, code will ask user to play again
            while(rounds < maxRounds);
        }

        else //if answered no on promt, a message will be send.
        {
            System.out.println("Come back when you're ready!");
        }
    }

    /**
     *  This method will be casking the user for a number from 0 to 9.
     *  Then it will check if it is out of bound.
     * 
     * @param  Scanner console will let the program interact with the user.
     * @param  char[] board let us place the input in the simple text board game.
     * @return     returns the character of the user. in this case, returns U.
     */
    public static void doUserMove(Scanner console, char[][] board) 
    {

        do//this do while loop will make the game goes on until there is a win.
        {
            System.out.print("Which row from 0-9 do you want to place your character: ");
            int row = console.nextInt();

            System.out.print("Which column from 0-9 do you want to place your character: ");
            int column = console.nextInt();

            if(row >= SIZE || row < 0)//checking for outof bound
            {
                System.out.print("That position is OUT-OF-BOUND! You must enter a number from 0 to 9!");
                System.out.println();
            }

            else if(column >= SIZE || column < 0)//checking for outof bound
            {
                System.out.print("That position is OUT-OF-BOUND! Please enter a number from 0 to 9: ");
                System.out.println();
            }

            else if(board[row][column] != ' ')//checking if there is already a character at the specific space
            {
                System.out.println("FCFS: First Come For Serve! Please enter another position.");
                System.out.println();
            }

            else
            {
                board[row][column] = 'U';//draws the user;s character when all tests fails.
                return;
            }

        }
        while(true);
    }

    /**
     * In this method, the Agent will pick a random number from 0-9 for the row and column
     * and that will be the space it will put its character.
     * 
     * @param  char[] board let us place the input in the simple text board game.
     * @return    returns method ifWon(board) where we check if computer wins or not.
     */
    public static boolean doComputerMove(char[][] board) //char name) 
    {

        Random rand = new Random();
        int row = rand.nextInt(10);//getting random number for row and column from 0-9
        int column = rand.nextInt(10);
        if(board[row][column] == ' ')//checking if input number is from 0-9
        {
            board[row][column] = 'A';
        }
        return ifWon(board);
    }

    /**
     * In this method, we will check if the user or the agent wins the game.
     * there are two for loops that checks four characters in a row diagonally or vertically.
     * 
     * @param char[] board let us place the input in the simple text board game.
     * @return if no one wins, it will return false. if someone wins, returns true
     */
    public static boolean ifWon(char[][] board)
    {
        for(int i = 0; i <= SIZE - 1; i++)//this for loops checks diagonally if there is a row of 4 same char.
        {     for(int j = 0; j <= SIZE - 4; j++)
            {
                if(board[i][j] != ' ')
                {
                    if(board[i][j] == board[i][j+1] && 
                    board[i][j] == board[i][j+2] && 
                    board[i][j] == board[i][j+3])
                    {
                        if(board[i][j] == 'U')
                        {
                            System.out.println("I admit defeat, you win this round!");
                        }
                        if(board[i][j] == 'A')
                        {
                            System.out.println("HA! See? You are no match against me!");
                        }
                        return true;
                    }
                }
            }
        }

        for(int j = 0; j <= SIZE - 1; j++)//this for loops checks vertically if there is a row of 4 same char.
        {     for(int i = 0; i <= SIZE - 4; i++)
            {
                if(board[i][j] != ' ')
                {
                    if(board[i][j] == board[i+1][j] && 
                    board[i][j] == board[i+2][j] && 
                    board[i][j] == board[i+3][j])
                    {
                        if(board[i][j] == 'U')
                        {
                            System.out.println("I admit defeat, you win this round!");
                        }
                        else
                        {
                            System.out.println("HA! See? You are no match against me!");
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     *  This method creates the board with row and column of SIZE by SIZE (10by10)
     * 
     * @return     returns a box with 10 by 10 parameter.
     */
    public static char[][] initializeBoard() 
    {
        char[][] result = new char[SIZE][SIZE];//initiallizing the board 
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                result[i][j] = ' ';
            }
        }
        return result;
    }

    /**
     *  This method creates an outline dashes for the board.
     * 
     */
    public static void drawLine() 
    {
        for (int i = 0; i <= 4 * SIZE; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     *  This board creates the outline for the board, creating a box for the characters.
     * 
     */
    public static void printBoard(char[][] board) 
    {
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
     *  This method prompt and checks whether the input is y or n.
     *  If the user input capital letters Y or N, the toLowerCase() method
     *  will convert it to lower case.
     *  And if the user input any other answer, it while statement will prompt 
     *  the user until his/her input is valid.
     * 
     * @param  prompt = a string prompt for the user.
     * @param  console = a scanner so that we can interact with the user.
     * @return  returns y for yes when the user's input is y.
     */
    public static boolean yesTo(String prompt, Scanner console) 
    {
        System.out.print(prompt + " ");
        String response = console.next().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) 
        {
            System.out.println("Wrong input! Please answer y or n.");
            System.out.print(prompt + " ");
            response = console.next().toLowerCase();
        }
        return response.equals("y");
    }

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
        //
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

    public static boolean checkBoard(char[][] board) {
        //checl for rows
        //in a for loop with a control variable that takes on the values 0 to 9
        //check the board if the game is over. while the the row remains constant, the user adds one column more each time he/she plays
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

    /**
     * This method creates only the intro.
     */
    public static void printIntro(Scanner console)
    {
        System.out.println("Thank You for Playing The \"Quatre\" Game-Playing Agent!");
        System.out.println("This is how you play. Each player will take turns and the first player to");
        System.out.println("form 4 of their characters diagonally or vertically wins.");
        System.out.println();
    }
}

