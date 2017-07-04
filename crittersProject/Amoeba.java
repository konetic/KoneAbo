package crittersProject;
import java.awt.*;
/**
 * The Amoeba class of critter moves randomly about apparently without intent
 * and only  infects another creature.
 * @author Tesfaye
 * @version 3
 */

public class Amoeba extends Critter {
    private String letter;
    private boolean changeLetter;
    private Color c; 
    public Amoeba(boolean cool){
        if (cool){
            c = Color.BLUE; //set color to blue
        }
        else{
            c = Color.PINK; //set color to pink
        }
        letter = "a";
        changeLetter = false;
    }

    /**
     * get move as it advances to the next move
     * @param info describes what the surroundings /gets info about my neighbors
     * @return the next move 
     * 
     */
    public Action getMove(CritterInfo info) {
        changeLetter = !changeLetter;
        if (changeLetter){
            letter = "A";

        }
        else {
            letter = "a";
        }
        //vanish if facing other in x/y directions
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
     * gets the color of Amoeba of the critter
     * @return the color of Amoeba of the critter
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
        return letter;
    }

}

