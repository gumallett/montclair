/******************************************************************
*
*  A driver class to test the Sequence class and its method
*  extractLongestIncreasingSubsequence.  It generates a random sequence
*  of integers (length given in a command-line argument), displays it,
*  extracts the longest increasing subsequence, and displays that.
*
*  J.W. Benham
*  November 23-28, 2012
*
********************************************************************/
import java.util.*;

class SequenceTester
{
   public static void main(String[] args)
   {
      final int maxLength = Integer.parseInt(args[0]);
	
      Sequence randomSequence
         = new Sequence(maxLength);
 
     // Generate a sequence of random integers								  
      Random randomNumberGenerator = new Random();
									   
      for (int i = 0; i < maxLength; i++)
        randomSequence.append(randomNumberGenerator.nextInt(1000));
			
     // Print the sequence
      System.out.println("The random sequence:");
      System.out.println();
      
		int valuesPerLine = 10;		// the number of values on each line
      for (int i = 0; i < randomSequence.getLength() && maxLength < 100; i++)
		{
		   System.out.print(randomSequence.getValueAt(i)+ "  ");
			if ((i+1)%valuesPerLine == 0)
			  System.out.println();
		}
		
		// Extract and print longest increasing subsequence
		
		Sequence longestIncrSubseq
               = randomSequence.getLongestIncreasingSubsequence();
		
		System.out.println("\nA longest increasing subsequence:\n");
		
		for (int i = 0; i < longestIncrSubseq.getLength(); i++)
		{
		   System.out.print(longestIncrSubseq.getValueAt(i)+ "  ");
			if ((i+1)%valuesPerLine == 0)
			  System.out.println();
		}
	}
}
