package crittersProject;
import java.awt.*;
/**
 * The Phantoma class of critter is of 'high' intelligence and moves randomly about 
 * apparently without intent, and only occassionally infects another creature.
 * @author Tesfaye
 * @version 3
 */

public class Phantoma extends Critter {
    private String symbol;
    int countMoves;
    public Phantoma(){
        countMoves = 0;
        symbol = "?";
    }

    /**
     * 
     * get the string charactor or symbol
     * @return a symbol to print on the screen to represent what the charactor is
     * 
     * 
     */
    public String toString() {
        return symbol;
    }

    /**
     * gets the color of phantoma of the critter
     * @return the color of phantoma of the critter
     * 
     */
    public Color getColor() {
        return Color.GRAY;
    }

    /**
     * get move as it advances to the next move
     * @param info describes what the surroundings /gets info about my neighbors
     * @return the next move 
     * 
     */
    public Action getMove(CritterInfo info) {
        if (countMoves%6 == 0){
            symbol = nextSymbol();    
        }
        countMoves++;

        if (info.getFront()== Neighbor.OTHER){
            return Action.INFECT;

        }
        if (info.getFront() == Neighbor.EMPTY) {
            return Action.ADVANCE;
        }

        return Action.LEFT;

    }

    /**
     * describes the string charactors
     * 
     * @return the next symbols
     */
    private String nextSymbol(){
        if (symbol.equals("?")){
            return "??";
        }
        else if(symbol.equals("??")){
            return "!";
        }
        else{
            return "?";
        }
    }
}
