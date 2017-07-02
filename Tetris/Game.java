package Tetris;

import java.awt.*;
/**
 * Manages the game Tetris.  Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author Tesfaye Belete
 * @version Belete_HW1 04/07/2016
 */
public class Game
{
    
    private Grid theGrid;       // the grid that makes up the Tetris board
    private Tetris theDisplay;  // the visual for the Tetris game
    private LShape piece;        // the current piece that is dropping
    private boolean isOver;     // has the game finished?
    
    // possible move directions
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    /**
     * Create a Tetris game
     * @param Tetris the display
     */
    public Game(Tetris display)
    {
       theGrid = new Grid();
       theDisplay = display;
       piece = new LShape(1, Grid.WIDTH/2 -1, theGrid);
       isOver = false;
    }
    

    /** Draw the current state of the game
     * @param g the Graphics context on which to draw
     */
    public void draw(Graphics g)
    {
        theGrid.draw(g);
        if (piece != null)
            piece.draw(g);
    }
    
    /** Move the piece in the given direction
     * @param the direction to move
     * @throws IllegalArgumentException if direction is not legal
     */
    public void movePiece(int direction){
        if (piece != null)
            piece.move(direction);
        updatePiece();
        theDisplay.update();
        theGrid.checkRows();    
    }
    
    /**
     * Returns true if the game is over
     */
    public boolean isGameOver() {
       // game is over if the piece occupies the same space as some non-empty
       // part of the grid.  Usually happens when a new piece is made
       if (piece == null)
            return false;
       
      // check if game is already over
      if (isOver)
        return true;
       
      // check every part of the piece
      Point[] p = piece.getLocations();
      for (int i = 0; i <p.length; i++)
            if (theGrid.isSet((int) p[i].getX(), (int) p[i].getY()) ) {
                isOver = true;
                return true;
            }
      return false;
    }
    
    /**
     * 
     * Update the piece
     * 
     */
    
    private void updatePiece() {
        if (piece == null) {
          //CREATE A NEW PIECE HERE
        	piece = new LShape(1, Grid.WIDTH/2 -1, theGrid);
        } 
        
        // set Grid positions corresponding to frozen piece
        // and then release the piece
        else if (!piece.canMove(Game.DOWN)) {
            Point [] p = piece.getLocations();
            Color c = piece.getColor();
            for (int i =0; i < p.length; i++){
                theGrid.set((int)p[i].getX(), (int)p[i].getY(), c);
            }
            piece = null;
        }
       
   }
            
}
