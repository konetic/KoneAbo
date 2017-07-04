package crittersProject;
import java.awt.*;
/**
 * The Chromista class of critter is of 'low' intelligence and It moves randomly about 
 * apparently without intent, and only occassionally infects another creature. It chnages
 * its color as it moves
 * @author Tesfaye
 * @version 3
 */

public class Chromista extends Critter {
    private Color c;
    private boolean changeColor;
    public Chromista(){

        c = randomColor();
        changeColor = false;
    }

    /**
     * get move as it advances to the next move
     * @param info describes what the surroundings /gets info about my neighbors
     * @return the next move 
     * 
     */
    public Action getMove(CritterInfo info) {
        if (changeColor){
            c = randomColor();
        }
        changeColor = !changeColor;
        if (info.getFront()== Neighbor.OTHER){
            return Action.INFECT;
        }
        if (info.getFront()== Neighbor.WALL || info.getLeft() == Neighbor.WALL){
            return Action.RIGHT;

        }
        if (info.getFront() == Neighbor.SAME){
            return Action.LEFT;

        }
        return Action.ADVANCE;
    }

    /**
     * gets the color of chromista of the critter
     * @return the color of chromista of the critter
     * 
     */
    public Color getColor() {
        return c;
    }

    /**
     * 
     * get the string charactor or symbol
     * @return a symbol to print on the screen to represent what the charactor is
     * 
     * 
     */
    public String toString() {
        return "C";
    }

    /**
     * gets random color as moves
     * @return varied colors in response of propertions 
     * 
     */
    private Color randomColor(){
        double r = Math.random();
        Color rc;
        if (r<1.0/3.0){
            rc = Color.RED;   
        }
        else if (r<2.0/3.0){
            rc = Color.GREEN;
        }
        else
        {
            rc = Color.BLUE;
        }
        return rc;

    }
}

