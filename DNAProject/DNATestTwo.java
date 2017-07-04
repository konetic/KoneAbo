package DNAProject;
/*This program reports information about DNA
 *nucleotide sequences that may encode proteins.
 *Input file name? dna.txt
 *Output file name? output.txt
 * 
 * @author Tesfaye
 * @version 3
 */

import java.io.*;
import java.util.*;

public class DNATestTwo
{
    private static final double TOTALCANDG = 30;
    private static String outputFile;
    
    public static void main(String[] args) throws FileNotFoundException {
      System.out.println("This program reports information about DNA"
                    + "\nnucleotide sequences that may encode proteins.");
        Scanner read = new Scanner(System.in);
        System.out.print("Input file name? ");
        String inputFile = read.next();
        System.out.print("Output file name? ");
        outputFile = read.next();  
      
      
      Scanner input = new Scanner(new File(inputFile));
        
        boolean itIsARegion = true;
        String region = "";

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
                }
                //System.out.println(line);
                itIsARegion = !itIsARegion;
            }

        }
    }

    public static int[] countNucleotides(String line){
        int[] counts = new int[4];
        counts[0] = countAs(line);
        counts[1] = countCs(line);
        counts[2] = countGs(line);
        counts[3] = countTs(line);
        return counts;
    }

    public static double[] countNucleotidesPercent(int[] counts, double total){
        double[] countsMass = new double[4];
        countsMass[0] = (massPercent(counts[0] * 135.128, total));
        countsMass[1] = (massPercent(counts[1] * 111.103, total));
        countsMass[2] = (massPercent(counts[2] * 151.128, total));
        countsMass[3] = (massPercent(counts[3] * 125.107, total));
        return countsMass;
    }

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

    public static double massPercent(double mass, double total){
        return roundOff((mass/total)*100.00);
    }

    public static double totalMass(int[] counts, String line){
        double total = 0.0;
        total += counts[0]* 135.128;
        total += counts[1]* 111.103;
        total += counts[2]* 151.128;
        total += counts[3]* 125.107;
        total += countJunk(line)* 100.00;
        return total;
    }

    public static double roundOff(double value){
        return Math.round(value*10.0)/10.0;
    }

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
//comparing two 
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
        //rule #4
        double totalCandG = countsMass[1] + countsMass[2];
        if (totalCandG < TOTALCANDG){
            return false;
        }

        return true; //The Boolean object corresponding to the primitive value true.
    }

    public static void printOutput (String region, String nucleotides, int[] counts, double[] countsMass, double total, String[] codons, boolean protein) throws FileNotFoundException{
        PrintStream output = new PrintStream(new File("output.txt"));  
        output.println("Region Name: " + region);
        output.println("Nucleotides: " + nucleotides);
        output.println("Nuc. Counts: " + Arrays.toString(counts));
        output.println("Total Mass%: " + Arrays.toString(countsMass) + " of " + roundOff(total));
        output.println("Codons List: " + Arrays.toString(codons));
        output.print("Is Protein?: ");
        if (protein){
            output.println("YES");
        }
        else{
            output.println("NO");
        }

    }
}
