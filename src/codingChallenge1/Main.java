package codingChallenge1;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        int indexCalculation;
        String text;

        do 		//Generates input prompt until user types 1 to exit
        {
            System.out.println("Please enter a text to determine the reading level or type 1 to end the program ");
            text = input.nextLine();

            //Exits program if the user types 1
            if (text.equals("1")) 
            {
            	System.out.println("Program executed.");
                break;
            }
            
            double numberOfLetters = 0;
            double numberOfWords;
            double numberOfSentences = 0;

           boolean isEndOfSentence = false;
            for (int i = 0; i < text.length(); i++) 
            {
                char c = text.charAt(i);
                
                // (1) Counting the number of letters
                if (Character.isLetter(c)) 		
                {
                    numberOfLetters++;
                }
                
                if (isEndOfSentenceCharacter(c)) 	//Checks if it's at the end of a sentence
                {
                    if (!isHonorific(text, i)) 		//Makes sure honorifics don't get counted as end of a sentence
                    {
                        numberOfSentences++;
                        isEndOfSentence = true;
                    }
                } 
                else 
                {
                    isEndOfSentence = false;
                }
            }
            
            // (2) Counting the number of words
            if (text.trim().isEmpty()) 			//Checks if the trimmed text is empty
            {
                numberOfWords = 0;
            } 
            else 						//Gets rid of white space in the input
            {
                numberOfWords = text.trim().split("\\s+").length;
            }


            //Calculating the grade level
            double L = (numberOfLetters / numberOfWords) * 100;
            double S = (numberOfSentences / numberOfWords) * 100;
            
            indexCalculation = (int) (0.0588 * L - 0.296 * S - 15.8);

            //Prints out the grade level
            if (indexCalculation <= 0) {
                System.out.println("Grade: 0 \n");
            } else if (indexCalculation > 12) {
                System.out.println("Grade: 12");
            } else {
                System.out.println("Grade: " + indexCalculation);
            }
        } while (true);

        input.close();
    }

    //Method to account for different punctuation marks & acronyms with periods in them.
    private static boolean isEndOfSentenceCharacter(char c) 
    {
        return (c == '.' || c == '?' || c == '!') && !Character.isLetterOrDigit(c);   //Ensures the periods in acronyms aren't
    }																			     //counted as the end of a sentence

    //Method to account for honorifics (ensure they aren't counted as the end of a sentence)
    private static boolean isHonorific(String text, int index) 
    {
        if (index < 2) 
        {
            return false;
        }
        
        String prevWord = text.substring(index - 2, index); 
        
        //List of honorifcs to be accounted for when determining endOfSentence
        return prevWord.equals("Mr") || prevWord.equals("Ms") || prevWord.equals("Mrs") ||
                prevWord.equals("Dr") || prevWord.equals("Prof") || prevWord.equals("Rev") ||
                prevWord.equals("Hon") || prevWord.equals("Capt") || prevWord.equals("Sgt") ||
                prevWord.equals("Col") || prevWord.equals("Gen") || prevWord.equals("Gov") ||
                prevWord.equals("Pres") || prevWord.equals("Rep") || prevWord.equals("Sen");
    }
}



/** 	OUTPUT OF MY CODE:
 * 
 * 		Test Case 1: 
 * 
 * 	Please enter a text to determine the reading level or type 1 to end the program 
 *	Eddie doesn not like cleaning, but over the past few days, he has had to stuff his old toys into garbage bags and the new ones into boxes. He tossed out old, dried-up Silly Putty, puzzles he once glued together, plush animals and more. Eddie's family if about to move to a new house, and they need their home clean for the next family who will live in it. "Won't they want our stuff?" Eddie asked his mother. "Who wouldn't want toys?" "They'll bring their own toys," she answered. "Come on, Eddie. We're nearly done. All that's left to clean is the attic." 
	Grade: 5
	Please enter a text to determine the reading level or type 1 to end the program 
 * 
 * 
 * 		Test Case 2: 
 * 
 * 	Please enter a text to determine the reading level or type 1 to end the program 
	Do you know the difference between a submarine and a submersible? A submarine is a watercraft that is capable of independent operation under the sea. Submarines do not require support ships because submarines can renew their air and power supplies independently. Submersibles also submerge and operate underwater, but they need the support of a larger vessel. 
	Grade: 12
	Please enter a text to determine the reading level or type 1 to end the program 
 * 
 * 
 * 		Test Case 3:  (a paragraph with acronyms)
 * 	Please enter a text to determine the reading level or type 1 to end the program 
	The U.S.A is a country in North America. It is made up of 50 independent states. The capital of the United States is Washington D.C. It's is a big country with many different types of landscapes, like mountains, forests, desesrts, and beaches. The U.S.A is also home to many different types of animals like bears, eagles, and alligators.
	Grade: 5
	Please enter a text to determine the reading level or type 1 to end the program 
	
 * 
 */
