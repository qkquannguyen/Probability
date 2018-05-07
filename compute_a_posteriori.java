/* ********************************************************************************************** *
 * Name     : Minh-Quan Nguyen                                                                    *
 * UTA ID#  : 1001032212                                                                          *
 * Class    : CSE4308 - 001                                                                       *
 * Assignment #9 - Posterior Probabilities and Bayesian Networks                                  *
 * ********************************************************************************************* */

// ---------------------------------- Import Needed Libraries ---------------------------------- //
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// --------------------------------- Compute A Posterior Class --------------------------------- //
public class compute_a_posteriori {

    // ------------------------ Class Variable ------------------------ //
    private static double probabilityZero       = 0.00;                         // --- 0.00%
    private static double probabilityOneTenth   = 0.10;                         // --- 0.10%
    private static double probabilityTwoTenth   = 0.20;                         // --- 0.20%
    private static double probabilityFourTenth  = 0.40;                         // --- 0.40%

    private static double probabilityOneFourth  = 0.25;                         // --- 0.25%
    private static double probabilityOneHalf    = 0.50;                         // --- 0.50%
    private static double probabilityThreeFourth= 0.75;                         // --- 0.75%
    private static double probabilityOneWhole   = 1.00;                         // --- 100.00%

    private static int MAX_POSSIBLE_HYPOTHESIS = 5;                             // --- 5 Total Maximum amount of hypothesis


    // ----------------------- Main Function -------------------------- //
    public static void main(String[] args) throws IOException {
        // --- Initialize Main Variables
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");             // --- Formats Decimal answers to 5 decimal places
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        File fileOut = new File("result.txt");                                  // --- Creates a new file named "result.txt"
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);      // --- File Output Stream
                                                                                // --- Writes Results (Line 41)
        BufferedWriter resultsWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        int i, j;                                                               // --- Iterator for For-Loops
        int lengthOfQ;                                                          // --- Length of String Q
        String userStringInput;                                                 // --- Takes in the first argument from the command line from the user

        List<List<Double>> possibleHypothesis = new ArrayList<>();              // --- List of Possible Hypothesis,
        List<Double> priorPossibleHypothesis = new ArrayList<>();               // --- List of Prior Probability
        List<Double> cherryCandiesHypothesis = new ArrayList<>();               // --- List of Cherry Candies Probability
        List<Double> limeCandiesHypothesis = new ArrayList<>();                 // --- List of Line Candies Probability
        List<Character> userInputCharacter = new ArrayList<>();                 // --- List of the character the user inputted from the inputted String

        // --- Store all the prior hypothesis given by Assignment 9 Problem description
        // --- Store them in a List which is then stored inside another list
        priorPossibleHypothesis.add(probabilityOneTenth);
        priorPossibleHypothesis.add(probabilityTwoTenth);
        priorPossibleHypothesis.add(probabilityFourTenth);
        priorPossibleHypothesis.add(probabilityTwoTenth);
        priorPossibleHypothesis.add(probabilityOneTenth);
        possibleHypothesis.add(priorPossibleHypothesis);

        // --- Store all the cherry candies hypothesis given by Assignment 9 Problem description
        cherryCandiesHypothesis.add(probabilityOneWhole);
        cherryCandiesHypothesis.add(probabilityThreeFourth);
        cherryCandiesHypothesis.add(probabilityOneHalf);
        cherryCandiesHypothesis.add(probabilityOneFourth);
        cherryCandiesHypothesis.add(probabilityZero);

        // --- Store all the lime candies hypothesis given by Assignment 9 Problem description
        limeCandiesHypothesis.add(probabilityZero);
        limeCandiesHypothesis.add(probabilityOneFourth);
        limeCandiesHypothesis.add(probabilityOneHalf);
        limeCandiesHypothesis.add(probabilityThreeFourth);
        limeCandiesHypothesis.add(probabilityOneWhole);

        // --- Get the users String argument at position 0 in the command line
        userStringInput = args[0];

        // --- Check to see if the user input string is empty, if it is, then there was no observations made
        if (isEmpty(userStringInput)) {
            System.out.println("There has been no observations made by the user.\n");
        }

        // --- Iterate through the string and store the string as characters in a Array List
        for (i = 0 ; i < userStringInput.length() ; i++) {
            userInputCharacter.add(userStringInput.charAt(i));
        }

        // --- Begin writing to the file as specified in Assignment 9 requirements
        // --- Observation Sequence
        resultsWriter.write("Observation Sequence Q: ");
        resultsWriter.write(userStringInput);
        resultsWriter.newLine();

        // --- Length of Inputted String
        lengthOfQ = userStringInput.length();
        resultsWriter.write("Length of Q: ");
        resultsWriter.write(Integer.toString(lengthOfQ));
        resultsWriter.newLine();
        resultsWriter.newLine();

        // --- Begin to read each character the user gives.
        // --- 'C' means Cherry Candies
        // --- 'L' means Lime Candies
        // --- An Array List of size zero means there was no observation made / no inputted string from the user
        for (i = 0 ; i < userInputCharacter.size() ; i++) {

            // --- If the user gives a character 'L'
            // --- Compute the probability
            if (userInputCharacter.get(i) == 'L') {

                // --- Write the current observation index into the results file
                resultsWriter.write("After Observation " + Integer.toString(i + 1) + " = L:");
                resultsWriter.newLine();
                resultsWriter.newLine();

                // --- Calculate the total amount of limes through probability and hypothesis
                // --- Do necessary arithmetic and put the results in the Array List
                double totalAmountLimes = (limeCandiesHypothesis.get(0) * possibleHypothesis.get(i).get(0)) +
                                           limeCandiesHypothesis.get(1) * possibleHypothesis.get(i).get(1) +
                                           limeCandiesHypothesis.get(2) * possibleHypothesis.get(i).get(2) +
                                           limeCandiesHypothesis.get(3) * possibleHypothesis.get(i).get(3) +
                                           limeCandiesHypothesis.get(4) * possibleHypothesis.get(i).get(4);

                double knownAmountOfLimesNumOne     = (limeCandiesHypothesis.get(0) * possibleHypothesis.get(i).get(0));
                double possibleAmountOfLimesNumOne  = (knownAmountOfLimesNumOne / totalAmountLimes);

                double knownAmountOfLimesNumTwo     = (limeCandiesHypothesis.get(1) * possibleHypothesis.get(i).get(1));
                double possibleAmountOfLimesNumTwo  = (knownAmountOfLimesNumTwo / totalAmountLimes);

                double knownAmountOfLimesNumThree   = (limeCandiesHypothesis.get(2) * possibleHypothesis.get(i).get(2));
                double possibleAmountOfLimesNumThree= (knownAmountOfLimesNumThree / totalAmountLimes);

                double knownAmountOfLimesNumFour    = (limeCandiesHypothesis.get(3) * possibleHypothesis.get(i).get(3));
                double possibleAmountOfLimesNumFour = (knownAmountOfLimesNumFour / totalAmountLimes);

                double knownAmountOfLimesNumFive    = (limeCandiesHypothesis.get(4) * possibleHypothesis.get(i).get(4));
                double possibleAmountOfLimesNumFive = (knownAmountOfLimesNumFive / totalAmountLimes);

                List<Double> listOfPossibleProbabilitiesForLimes = new ArrayList<>();
                double limeOne  = Double.parseDouble(decimalFormat.format(possibleAmountOfLimesNumOne));
                double limeTwo  = Double.parseDouble(decimalFormat.format(possibleAmountOfLimesNumTwo));
                double limeThree= Double.parseDouble(decimalFormat.format(possibleAmountOfLimesNumThree));
                double limeFour = Double.parseDouble(decimalFormat.format(possibleAmountOfLimesNumFour));
                double limeFive = Double.parseDouble(decimalFormat.format(possibleAmountOfLimesNumFive));

                listOfPossibleProbabilitiesForLimes.add(limeOne);
                listOfPossibleProbabilitiesForLimes.add(limeTwo);
                listOfPossibleProbabilitiesForLimes.add(limeThree);
                listOfPossibleProbabilitiesForLimes.add(limeFour);
                listOfPossibleProbabilitiesForLimes.add(limeFive);

                possibleHypothesis.add(listOfPossibleProbabilitiesForLimes);

                // --- Write the probabilities to the file
                for (j = 0 ; j < MAX_POSSIBLE_HYPOTHESIS ; j++) {
                    double getHypothesis = possibleHypothesis.get(possibleHypothesis.size() - 1 ).get(j);
                    resultsWriter.write("P(h" + Integer.toString(j + 1));
                    resultsWriter.write(" | Q) = " + Double.toString(getHypothesis));
                    resultsWriter.newLine();
                }

                // --- Get the index of the hypothesis array list
                int indexOfFinalHypothesis = possibleHypothesis.size() - 1;

                // --- Calculate the final lime and cherry probability
                double finalLimeProbability = (limeCandiesHypothesis.get(0) * possibleHypothesis.get(indexOfFinalHypothesis).get(0) +
                                               limeCandiesHypothesis.get(1) * possibleHypothesis.get(indexOfFinalHypothesis).get(1) +
                                               limeCandiesHypothesis.get(2) * possibleHypothesis.get(indexOfFinalHypothesis).get(2) +
                                               limeCandiesHypothesis.get(3) * possibleHypothesis.get(indexOfFinalHypothesis).get(3) +
                                               limeCandiesHypothesis.get(4) * possibleHypothesis.get(indexOfFinalHypothesis).get(4));
                double limeValue = Double.parseDouble(decimalFormat.format(finalLimeProbability));

                double finalCherryProbability = (cherryCandiesHypothesis.get(0) * possibleHypothesis.get(indexOfFinalHypothesis).get(0) +
                                                 cherryCandiesHypothesis.get(1) * possibleHypothesis.get(indexOfFinalHypothesis).get(1) +
                                                 cherryCandiesHypothesis.get(2) * possibleHypothesis.get(indexOfFinalHypothesis).get(2) +
                                                 cherryCandiesHypothesis.get(3) * possibleHypothesis.get(indexOfFinalHypothesis).get(3) +
                                                 cherryCandiesHypothesis.get(4) * possibleHypothesis.get(indexOfFinalHypothesis).get(4));
                double cherryValue = Double.parseDouble(decimalFormat.format(finalCherryProbability));

                // --- Write the results into the results file
                resultsWriter.newLine();
                resultsWriter.write("Probability that the next candy we pick will be L, given Q: " + Double.toString(limeValue));
                resultsWriter.newLine();
                resultsWriter.write("Probability that the next candy we pick will be C, given Q: " + Double.toString(cherryValue));
                resultsWriter.newLine();
                resultsWriter.newLine();
            }

            // --- If the user gives a character 'C'
            // --- Compute the probability
            else if (userInputCharacter.get(i) == 'C') {

                // --- Write the current observation index into the results file
                resultsWriter.write("After Observation " + Integer.toString(i + 1) + " = C:");
                resultsWriter.newLine();
                resultsWriter.newLine();

                // --- Calculate the total amount of cherries through probability and hypothesis
                // --- Do necessary arithmetic and put the results in the Array List
                double totalAmountOfCherries = (cherryCandiesHypothesis.get(0) * possibleHypothesis.get(i).get(0)) +
                                                cherryCandiesHypothesis.get(1) * possibleHypothesis.get(i).get(1) +
                                                cherryCandiesHypothesis.get(2) * possibleHypothesis.get(i).get(2) +
                                                cherryCandiesHypothesis.get(3) * possibleHypothesis.get(i).get(3) +
                                                cherryCandiesHypothesis.get(4) * possibleHypothesis.get(i).get(4);

                double knownAmountOfCherriesNumOne      = (cherryCandiesHypothesis.get(0) * possibleHypothesis.get(i).get(0));
                double possibleAmountOfCherriesNumOne   = (knownAmountOfCherriesNumOne / totalAmountOfCherries);

                double knownAmountOfCherriesNumTwo      = (cherryCandiesHypothesis.get(1) * possibleHypothesis.get(i).get(1));
                double possibleAmountOfCherriesNumTwo   = (knownAmountOfCherriesNumTwo / totalAmountOfCherries);

                double knownAmountOfCherriesNumThree    = (cherryCandiesHypothesis.get(2) * possibleHypothesis.get(i).get(2));
                double possibleAmountOfCherriesNumThree = (knownAmountOfCherriesNumThree / totalAmountOfCherries);

                double knownAmountOfCherriesNumFour     = (cherryCandiesHypothesis.get(3) * possibleHypothesis.get(i).get(3));
                double possibleAmountOfCherriesNumFour  = (knownAmountOfCherriesNumFour / totalAmountOfCherries);

                double knownAmountOfCherriesNumFive     = (cherryCandiesHypothesis.get(4) * possibleHypothesis.get(i).get(4));
                double possibleAmountOfCherriesNumFive  = (knownAmountOfCherriesNumFive / totalAmountOfCherries);

                List<Double> listOfPossibleProbabilitiesForCherries = new ArrayList<>();
                double cherryOne    = Double.parseDouble(decimalFormat.format(possibleAmountOfCherriesNumOne));
                double cherryTwo    = Double.parseDouble(decimalFormat.format(possibleAmountOfCherriesNumTwo));
                double cherryThree  = Double.parseDouble(decimalFormat.format(possibleAmountOfCherriesNumThree));
                double cherryFour   = Double.parseDouble(decimalFormat.format(possibleAmountOfCherriesNumFour));
                double cherryFive   = Double.parseDouble(decimalFormat.format(possibleAmountOfCherriesNumFive));

                listOfPossibleProbabilitiesForCherries.add(cherryOne);
                listOfPossibleProbabilitiesForCherries.add(cherryTwo);
                listOfPossibleProbabilitiesForCherries.add(cherryThree);
                listOfPossibleProbabilitiesForCherries.add(cherryFour);
                listOfPossibleProbabilitiesForCherries.add(cherryFive);

                possibleHypothesis.add(listOfPossibleProbabilitiesForCherries);

                // --- Write the probabilities to the file
                for (j = 0 ; j < MAX_POSSIBLE_HYPOTHESIS ; j++) {
                    double getHypothesis = possibleHypothesis.get(possibleHypothesis.size() - 1 ).get(j);
                    resultsWriter.write("P(h" + Integer.toString(j + 1));
                    resultsWriter.write(" | Q) = " + Double.toString(getHypothesis));
                    resultsWriter.newLine();
                }

                // --- Get the index of the hypothesis array list
                int indexOfFinalHypothesis = possibleHypothesis.size() - 1;

                // --- Calculate the final lime and cherry probability
                double finalLimeProbability = (limeCandiesHypothesis.get(0) * possibleHypothesis.get(indexOfFinalHypothesis).get(0) +
                                               limeCandiesHypothesis.get(1) * possibleHypothesis.get(indexOfFinalHypothesis).get(1) +
                                               limeCandiesHypothesis.get(2) * possibleHypothesis.get(indexOfFinalHypothesis).get(2) +
                                               limeCandiesHypothesis.get(3) * possibleHypothesis.get(indexOfFinalHypothesis).get(3) +
                                               limeCandiesHypothesis.get(4) * possibleHypothesis.get(indexOfFinalHypothesis).get(4));
                double limeValue = Double.parseDouble(decimalFormat.format(finalLimeProbability));

                double finalCherryProbability = (cherryCandiesHypothesis.get(0) * possibleHypothesis.get(indexOfFinalHypothesis).get(0) +
                                                 cherryCandiesHypothesis.get(1) * possibleHypothesis.get(indexOfFinalHypothesis).get(1) +
                                                 cherryCandiesHypothesis.get(2) * possibleHypothesis.get(indexOfFinalHypothesis).get(2) +
                                                 cherryCandiesHypothesis.get(3) * possibleHypothesis.get(indexOfFinalHypothesis).get(3) +
                                                 cherryCandiesHypothesis.get(4) * possibleHypothesis.get(indexOfFinalHypothesis).get(4));
                double cherryValue = Double.parseDouble(decimalFormat.format(finalCherryProbability));

                // --- Write the results into the results file
                resultsWriter.newLine();
                resultsWriter.write("Probability that the next candy we pick will be L, given Q: " + Double.toString(limeValue));
                resultsWriter.newLine();
                resultsWriter.write("Probability that the next candy we pick will be C, given Q: " + Double.toString(cherryValue));
                resultsWriter.newLine();
                resultsWriter.newLine();
            }
        }

        resultsWriter.close();
    }

    // ----------------- isEmpty Function for Strings ----------------- //
    // --- Taken from the commons.apache.org Library
    /**
    * <p>Checks if a CharSequence is empty ("") or null.</p>
    *
    * <pre>
    * StringUtils.isEmpty(null)      = true
    * StringUtils.isEmpty("")        = true
    * StringUtils.isEmpty(" ")       = false
    * StringUtils.isEmpty("bob")     = false
    * StringUtils.isEmpty("  bob  ") = false
    * </pre>
    *
    * <p>NOTE: This method changed in Lang version 2.0.
    * It no longer trims the CharSequence.
    * That functionality is available in isBlank().</p>
    *
    * @param cs  the CharSequence to check, may be null
    * @return {@code true} if the CharSequence is empty or null
    * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
    */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
