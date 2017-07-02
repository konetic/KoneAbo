package Tetris;


/**
 * Handles events for the Tetris Game.  User events (key strokes) as well as periodic timer
 * events.
 * 
 * @author Tesfaye Belete
 * @version Belete_HW1 04/07/2016
 */
import java.awt.event.*;
import javax.swing.*;

public class EventController extends KeyAdapter implements ActionListener
{
    private Game theGame;
    private Timer timer;
    private static final double PIECE_MOVE_TIME = 0.8;  //controls time between
                                                   //piece moving down
                                                   //increase to slow it down
    private boolean gameOver;
    
    
    public EventController(Game g) {
        theGame = g;
        gameOver = false;
        double delay = 1000 * PIECE_MOVE_TIME;  // in milliseconds
        timer = new Timer((int)delay, this);
        timer.setCoalesce(true);    // if multiple events pending, bunch them to 1 event
        timer.start();
    }

    /*
     * Respond to special keys being pressed
     * Currently just responds to the space key
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_SPACE :
                    handleMove(Game.DOWN);
                    break;
               // HANDLE other keystrokes here
                case KeyEvent.VK_LEFT :
                    handleMove(Game.LEFT);
                    break;
                case KeyEvent.VK_RIGHT :
                    handleMove(Game.RIGHT);
                    break;
            }
            
        }
    }
   
   /** Update the game periodically based on a timer event*/
    public void actionPerformed(ActionEvent e) {
        handleMove(Game.DOWN);
     }
     
     /** Update the game by moving in the given direction
      */
     private void handleMove(int direction){
        theGame.movePiece(direction);
        gameOver = theGame.isGameOver();
        if (gameOver)
            timer.stop();
     }
}
