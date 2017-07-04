package crittersProject;
import java.awt.*;
/**
 * The Spot class of critter is of 'very high' intelligence and moves randomly about 
 * apparently without intent, and only occassionally infects another creature.
 * @author Tesfaye
 * @version 3
 */

public class Spot extends Critter {
    private Color x;
    private String symbol;
    private boolean changeColor;
    private int countMoves;
    public Spot(){
        x = Color.BLUE;
        countMoves = 0;
        symbol = "TT";
    }

    /**
     * get move as it advances to the next move
     * @param info describes what the surroundings /gets info about my neighbors
     * @return the next move 
     * 
     */
    public Action getMove(CritterInfo info) {
        if (info.getFront()== Neighbor.OTHER){
            x = Color.BLACK;
        }
        else if (info.getLeft()== Neighbor.OTHER){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.OTHER){
            x = Color.BLUE;
        }
        else {
            x = Color.RED;
        }
        //as moves, changes color

        if (info.getFront()== Neighbor.WALL){
            x = Color.GRAY;
        }
        else if (info.getLeft()== Neighbor.WALL){
            x = Color.BLACK;

        }
        else if (info.getRight()== Neighbor.WALL){
            x = Color.RED;
        }
        else {
            x = Color.BLUE;
        }
        if (info.getFront()== Neighbor.EMPTY){
            x = Color.RED;
        }
        else if (info.getLeft()== Neighbor.EMPTY){
            x = Color.GREEN;

        }
        else if (info.getRight()== Neighbor.EMPTY){
            x = Color.BLUE;
        }
        else {
            x = Color.BLACK;
        }
        if (info.getFront()== Neighbor.SAME){
            x = Color.GREEN;
        }
        else if (info.getLeft()== Neighbor.SAME){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.SAME){
            x = Color.BLUE;
        }
        else {
            x = Color.RED;
        }
        if (info.getBack()== Neighbor.OTHER){
            x = Color.BLACK;
        }
        else if (info.getLeft()== Neighbor.OTHER){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.OTHER){
            x = Color.BLUE;
        }
        else {
            x = Color.RED;
        }
        if (info.getFront()== Neighbor.WALL){
            x = Color.BLACK;
        }
        else if (info.getLeft()== Neighbor.WALL){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.WALL){
            x = Color.BLUE;
        }
        else {
            x = Color.RED;
        }
        if (info.getFront()== Neighbor.EMPTY){
            x = Color.BLACK;
        }
        else if (info.getLeft()== Neighbor.EMPTY){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.EMPTY){
            x = Color.BLUE;
        }
        if (info.getFront()== Neighbor.SAME){
            x = Color.BLACK;
        }
        else if (info.getLeft()== Neighbor.SAME){
            x = Color.GRAY;

        }
        else if (info.getRight()== Neighbor.SAME){
            x = Color.BLUE;
        }

        //for moves of counts, find next symbol
        if (countMoves%3 == 0 ){
            symbol = nextSymbol();
        }
        countMoves++;

        changeColor = !changeColor;
        //as moves in different direction, act to the encounters
        if (info.getFront()== Neighbor.OTHER ){
            return Action.INFECT;
        }

        if (info.getFront() == Neighbor.OTHER || info.getFront()== Neighbor.WALL){
            return Action.RIGHT;

        }
        if (info.getLeft() == Neighbor.OTHER || info.getLeft()== Neighbor.WALL){
            return Action.LEFT;

        }
        if (info.getRight() == Neighbor.OTHER || info.getRight()== Neighbor.WALL){
            return Action.RIGHT;

        }
        if (info.getBack() == Neighbor.OTHER || info.getBack()== Neighbor.WALL){
            return Action.LEFT;

        }
        if (info.getFront() == Neighbor.SAME || info.getBack()== Neighbor.WALL){
            return Action.LEFT;

        }
        if (info.getFront() == Neighbor.SAME){
            return Action.RIGHT;

        }

        return Action.ADVANCE;
    }

    /**
     * gets the color of Spot of the critter
     * @return the color of Spot of the critter
     * 
     */
    public Color getColor() {
        return x;
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

    /**
     * describes the string charactors
     * 
     * @return the next symbols
     */
    private String nextSymbol(){

        if (symbol.equals("TT")){
            return "BB";
        }
        else if (symbol.equals("BB")){
            return "WW";
        }
        else {
            return "TT";
        }
    }

}

