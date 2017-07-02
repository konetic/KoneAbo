package Tetris;

import java.awt.*;
/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration
 *              Sq<br>
 *              Sq<br>
 *              Sq Sq<br>
 * 
 * The game piece "floats above" the Grid.  The (row, col) coordinates 
 * are the location of the middle Square on the side within the Grid
 * 
 * @author CSC 143
 */
public class LShape 
{
    private boolean ableToMove;    // can this piece move
    private Square[] square;       // the Squares that make up this piece
                                    // Made up of PIECE_COUNT squares
    private Grid theGrid;         // the board this piece is on
    
    // number of squares in 1 Tetris game piece
    private static final int PIECE_COUNT = 4;
    
     
    /**
     * Create an L-Shape piece.  See class description for actual location
     * of r and c
     * @param r row location for this piece
     * @param c column location for this piece
     * @param g the grid for this game piece
     * 
     */
    public LShape(int r, int c, Grid g)
    {
        theGrid = g;
        square = new Square[PIECE_COUNT];
        ableToMove = true;
        
        // Create the squares
        square[0] = new Square(g, r - 1, c, Color.magenta, true);
        square[1] = new Square(g, r, c , Color.magenta, true);
        square[2] = new Square(g, r + 1, c, Color.magenta, true);
        square[3] = new Square(g, r + 1, c + 1, Color.magenta, true);
    }
    
    /**
    * Draw the piece on the given Graphics context
    */
   public void draw(Graphics g){
        for (int i = 0; i < PIECE_COUNT; i++)
            square[i].draw(g);
   }
   
    /**
    * Move the piece if possible
    * Freeze the piece if it cannot move down anymore
    * @param direction the direction to move
    * @throws IllegalArgumentException if direction is not Square.DOWN, 
    * Square.LEFT, or Square.RIGHT
    */
   public void move(int direction){
        if (canMove(direction)){
            for (int i = 0; i < PIECE_COUNT; i++)
                square[i].move(direction);
         }       
        // if we couldn't move, see if because we're at the bottom
        else if (direction == Game.DOWN){
            ableToMove = false;
        }
    }

    /** Return the (row,col) grid coordinates occupied by this Piece
     * @return an Array of (row,col) Points
     */
    public Point[] getLocations(){
        Point[] points = new Point[PIECE_COUNT];
        for(int i = 0; i < PIECE_COUNT; i++) {
            points[i] = new Point(square[i].getRow(), square[i].getCol());
        }
        return points;
   }
   
   /** 
    * Return the color of this piece
    */
   public Color getColor() {
        // all squares of this piece have the same color
        return square[0].getColor();
   }
   
   /**
    * Returns if this piece can move in the given direction
    * @throws IllegalArgumentException if direction is not Square.DOWN, 
    * Square.LEFT, or Square.RIGHT
    */
   public boolean canMove(int direction) {
        if (!ableToMove)
            return false;
            
       //Each square must be able to move in that direction
       boolean answer = true;
       for (int i = 0; i < PIECE_COUNT; i++) {
            answer = answer && square[i].canMove(direction);
       }
       
       return answer;
   }
}
