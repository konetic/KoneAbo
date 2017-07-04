package DNAProject;

/*This program reports information about DNA
 *nucleotide sequences that may encode proteins.
 * 
 * @author Tesfaye
 *
 * @version 3
 * 
 */

import java.io.*;
import java.util.*;

public class DNATest
{
    //Declares constant values and string files
    private static final double TOTALCANDG = 30;
    private static String outputFile;
    //The main method throws exception when necessary
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("This program reports information about DNA"
            + "\nnucleotide sequences that may encode proteins.");
        Scanner read = new Scanner(System.in);
        System.out.print("Input file name? ");
        String inputFile = read.next();
        System.out.print("Output file name? ");
        outputFile = read.next(); 

        //The user prompted to enter the input file
        Scanner input = new Scanner(new File(inputFile)); //input from the dna text file
        boolean itIsARegion = true;
        String region = "";
        //Testing the dna text file internal content in the while loop what data in it such as blank spaces,
        //strings or count them
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals(""))
            {
                //blank line skipping
            }
            else {
                // it's not a blank line
                if (itIsARegion) {
                    region = line;
                }
                else {
                    int[] counts = countNucleotides(line); 
                    double total = totalMass(counts, line);
                    double[] countsMass = countNucleotidesPercent(counts, total); 
                    String[] codons = findCodons(line);
                    printOutput (region, line.toUpperCase(), counts, countsMass, total, codons, isProtein(codons, countsMass));
                    printToFile (region, line.toUpperCase(), counts, countsMass, total, codons, isProtein(codons, countsMass));
                }
                itIsARegion = !itIsARegion; //testing over and over again while itIsARegion false 
            }

        }
    }

    /**  
     * Retrieve the value of counts . 
     * @param line A string data type passed to the countNucleotides method
     * @return counts A String data type and returns the number of counts in each category such as A, C, G, and T  
     */ 

    public static int[] countNucleotides(String line){
        int[] counts = new int[4];
        counts[0] = countAs(line);
        counts[1] = countCs(line);
        counts[2] = countGs(line);
        counts[3] = countTs(line);
        return counts;
    }

    /**  
     * Retrieve the value of countsMass. 
     * @param counts An integer data type to the countNucleotidesPercent method to compute the counts mass
     * @param total A double data type passed to the countNucleotidesPercent method to compute the counts mass
     * @return countsMass  A double data type returns the mass computation of both parameters passed in.  
     */ 

    public static double[] countNucleotidesPercent(int[] counts, double total){
        double[] countsMass = new double[4];
        countsMass[0] = (massPercent(counts[0] * 135.128, total));
        countsMass[1] = (massPercent(counts[1] * 111.103, total));
        countsMass[2] = (massPercent(counts[2] * 151.128, total));
        countsMass[3] = (massPercent(counts[3] * 125.107, total));
        return countsMass;
    }

    /**  
     *Retrieve the value of countsAs in the for loop. 
     * @param line A string data type passed to line as a parameter countAs method
     * @return count returns the number of As counted on the line after all characters changed to upper case
     * and extracted using type charAt
     */ 
    public static int countAs(String line){
        int count = 0;
        line = line.toUpperCase();
        for (int i= 0; i <line.length(); i++){
            if (line.charAt(i)== 'A'){
                count ++;
                //counting As
            }
        }
        return count; // returning the number of As
    }

    /**  
     *Retrieve the value of countsCs in the for loop. 
     * @param line A string data type passed to line as a parameter countCs method
     * @return count returns the number of Cs counted on the line after all characters changed to upper case 
     * and extracted using type charAt
     */

    public static int countCs(String line){
        int count = 0;
        line = line.toUpperCase();
        for (int i= 0; i <line.length(); i++){
            if (line.charAt(i)== 'C'){
                count ++;
            }
        }
        return count;

    }

    /**  
     *Retrieve the value of countsGs in the for loop. 
     * @param line A string data type passed to line as a parameter countGs method
     * @return count returns the number of Gs counted on the line after all characters changed to upper case
     * and extracted using charAt
     */
    public static int countGs(String line){
        int count = 0;
        line = line.toUpperCase();
        for (int i= 0; i <line.length(); i++){
            if (line.charAt(i)== 'G'){
                count ++;
            }
        }
        return count;
    }

    /**  
     *Retrieve the value of countsTs in the for loop. 
     * @param line A string data type passed to line as a parameter countTs method
     * @return count returns the number of Ts counted on the line after all characters changed to upper case
     * and extracted using type charAt
     */ 
    public static int countTs(String line){
        int count = 0;
        line = line.toUpperCase();
        for (int i= 0; i <line.length(); i++){
            if (line.charAt(i)== 'T'){
                count ++;
            }
        }
        return count;
    }

    /**  
     *Retrieve the value of countJunk in the for loop. 
     * @param line A string data type passed to line as a parameter variable to the countJunk method when called
     * @return count returns the number of -(dashes)'s counted on the line after all characters changed to upper case
     * and extracted using type charAt
     */
    public static int countJunk(String line){
        int count = 0;
        line = line.toUpperCase();
        for (int i= 0; i <line.length(); i++){
            if (line.charAt(i)== '-'){
                count ++;
            }
        }
        return count;
    }

    /**  
     *Retrieve the value of the rounded massPercent. 
     * @param mass It's passed as double mass parameter variable to the massPercent method when called
     * @param total It passes double mass and total values as a parameter to the massPercent method when called
     * @return roundOff Returns a rounded percent of mass for the parameters double mass and double total.  
     */
    public static double massPercent(double mass, double total){
        return roundOff((mass/total)*100.00);
    }

    /**  
     *Retrieve the value of totalMass. 
     * @param counts A variable type of integer passed to the totalMass method when called
     * @param line A variable type of string passed to the  totalMass method
     * @return total Returns the total mass for each counts.  
     */

    public static double totalMass(int[] counts, String line){
        double total = 0.0;
        total += counts[0]* 135.128;
        total += counts[1]* 111.103;
        total += counts[2]* 151.128;
        total += counts[3]* 125.107;
        total += countJunk(line)* 100.00;
        return total;
    }

    /** 
     * @param value passes double value as a parameter roundOff method when called
     * @return value Returns the rounded value  
     */
    public static double roundOff(double value){
        return Math.round(value*10.0)/10.0;
    }

    /**Retrieve the value of totalMass. 
     * @param line passes double value as a parameter findCodons method when called with the given length,
     * all uppercase and no spaces in between
     * @return codons Returns the rounded value of codons  
     */
    public static String[] findCodons(String line){
        line = line.toUpperCase();

        while (line.contains("-")){
            line = line.replace("-", "");
        }
        String[] codons = new String[line.length()/3];
        for (int i = 0; i<line.length(); i+=3){
            codons[i/3] = line.substring(i, i+3);
        }

        return codons;
    }

    /**
     * 
     * @param codons A string data type passed to the isProtein method when the method called
     * @param countsMass A double data type passed to the isProtein method when the method called
     * @return false when if conditions not met 
     */

    public static boolean isProtein (String[] codons, double[] countsMass){
        //rule #1
        if (!codons[0].equals("ATG")){
            return false; //The Boolean object corresponding to the primitive value false.

        }
        //rule #2
        int lastIndex = codons.length-1;
        if (!codons[lastIndex].equals("TAA") && !codons[lastIndex].equals("TAG")&& !codons[lastIndex].equals("TGA")){
            return false;
        }
        //rule # 3
        if (codons.length < 5){
            return false;

        }
        // rule #4

        double totalCandG = countsMass[1] + countsMass[2];
        if (totalCandG < TOTALCANDG){
            return false;
        }

        return true; //The Boolean object corresponding to the primitive value true.
    }

    /**
     * Sets the value of printOutput. 
     * @param region A string data type passed to the method printOutput when the method called
     * @param nucleotides A string data type passed to the method printOutput when the method called
     * @param codons A string data type passed to the method printOutput when the method called
     * @param counts An integer data type passed to the method printOutput when the method called
     * @param countsMass A double data type passed to the method printOutput when the method called
     * @param total A doouble data type passed to the method printOutput when the method called
     * @param proteinas A boolean data type passed to the method printOutput when the method called
     * 
     * 
     */
    public static void printOutput (String region, String nucleotides, int[] counts, double[] countsMass, double total, String[] codons, boolean protein){
        System.out.println();
        System.out.println("Region Name: " + region);
        System.out.println("Nucleotides: " + nucleotides);
        System.out.println("Nuc. Counts: " + Arrays.toString(counts));
        System.out.println("Total Mass%: " + Arrays.toString(countsMass) + " of " + roundOff(total));
        System.out.println("Codons List: " + Arrays.toString(codons));
        System.out.print("Is Protein?: ");
        if (protein){
            System.out.println("YES");//retuns yes
        }
        else{
            System.out.println("NO"); //returns no

        }
    }

        /**
     * Sets the value of printToFile. 
     * @param region A string data type passed to the method printToFile when the method called
     * @param nucleotides A string data type passed to the method printToFile when the method called
     * @param codons A string data type passed to the method printToFile when the method called
     * @param counts An integer data type passed to the method printToFile when the method called
     * @param countsMass A double data type passed to the method printToFile when the method called
     * @param total A doouble data type passed to the method printToFile when the method called
     * @param proteinas A boolean data type passed to the method printToFile when the method called
     * 
     * 
     */
    public static void printToFile(String region, String nucleotides, int[] counts, double[] countsMass, double total, String[] codons, boolean protein)throws FileNotFoundException{
        try { PrintWriter xoutput = new PrintWriter(new FileWriter(outputFile, true)); 
            xoutput.println("Region Name: " + region);
            xoutput.println("Nucleotides: " + nucleotides);
            xoutput.println("Nuc. Counts: " + Arrays.toString(counts));
            xoutput.println("Total Mass%: " + Arrays.toString(countsMass) + " of " + roundOff(total));
            xoutput.println("Codons List: " + Arrays.toString(codons));
            xoutput.print("Is Protein?: ");
            if (protein){
                xoutput.println("YES"); //returns yes
            }
            else{
                xoutput.println("NO"); //returns no
            }
            xoutput.println();
            xoutput.close();
        }catch(IOException e){} // cathes exceptions
    }
}

