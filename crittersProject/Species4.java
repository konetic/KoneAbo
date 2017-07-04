package crittersProject;
import java.awt.*;
/**
 * The Species4 class of critter is of 'low' intelligence and moves randomly about 
 * apparently without intent, and only occassionally infects another creature.
 * @author Tesfaye
 * @version 3
 */

public class Species4 extends Critter implements Vapors{
    private Color col;
    private boolean changeColor;
    private String symbol;
    private int hidden;
    public Species4(){
        symbol = "K";
        col = randomColor();
        changeColor = false;
        hidden = 0;
    }

    /**
     * get move as it advances to the next move
     * @param info describes what the surroundings /gets info about my neighbors
     * @return the next move 
     * 
     */
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER || info.getBack()==Neighbor.OTHER ||  info.getLeft() == Neighbor.OTHER || info.getRight() == Neighbor.OTHER) {

            vanish();

        }
        if (hidden>0){
            hidden--;
        }
        if (changeColor){
            col = randomColor();
        }
        changeColor = !changeColor;

        return beeLine(info);
    }

    /**
     * gets the color of Species4 of the critter
     * @return the color of Species4 of the critter
     * 
     */
    public Color getColor() {
        return col;
    }

    /**
     * 
     * get the string charactor or symbol
     * @return a symbol to print on the screen to represent what the charactor is
     * 
     * 
     */
    public String toString() {
        if (hidden>0){
            return " ";
        }
        else {
            return symbol;
        }

    }

    /**
     * gets random color as moves
     * @return colors
     * 
     */
    private Color randomColor(){
        double r = Math.random();
        Color rc;
        if (r<1.0/3.0){
            rc = Color.BLUE;   
        }
        else if (r<2.0/3.0){
            rc = Color.GREEN;
        }
        else
        {
            rc = Color.GRAY;
        }
        return rc;

    }
    private int steps = 0; // sets the move to 0
    /**
     * the method describes the actions it does and the directions where it moves in response to the the others action placed next to it
     * 
     * @param gets information about my neighbors 
     * @return the direction where it moves left, advance, infect and right
     * 
     */
    public Critter.Action beeLine(CritterInfo info){
        if (steps == 0){
            steps = 13;
            return Action.RIGHT;
        } 
        if (info.getFront() == Neighbor.EMPTY){
            steps--;
            return Action.ADVANCE;
        }
        if (info.getFront() == Neighbor.OTHER || info.getFront() == Neighbor.SAME){
            return Action.INFECT;
        }
        if (info.getFront() == Neighbor.WALL){
            steps = 0;
            return Action.RIGHT;
        }
        return Action.LEFT;
    }

    /**
     * describes the number of times Species2 vanishes
     * 
     */
    public void vanish(){
        hidden = 3;

    }
}

