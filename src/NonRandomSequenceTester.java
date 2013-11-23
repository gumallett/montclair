public class NonRandomSequenceTester {

   public static void main(String[] args) {
      Sequence sequence = new Sequence(1024);

      for(String s : args) {
         try {
            Integer val = Integer.parseInt(s);
            sequence.append(val);
         }
         catch(NumberFormatException nfe) {
            System.out.println("Usage: java NonRandomSequenceTester [[num][num]...]");
         }
      }

      // Print the sequence
      System.out.println("The random sequence:");
      System.out.println();

      int valuesPerLine = 10;		// the number of values on each line
      for (int i = 0; i < sequence.getLength() && i < 100; i++)
      {
         System.out.print(sequence.getValueAt(i)+ "  ");
         if ((i+1)%valuesPerLine == 0)
            System.out.println();
      }

      // Extract and print longest increasing subsequence

      Sequence longestIncrSubseq
              = sequence.getLongestIncreasingSubsequence();

      System.out.println();
      System.out.println("\nA longest increasing subsequence:\n");

      for (int i = 0; i < longestIncrSubseq.getLength(); i++)
      {
         System.out.print(longestIncrSubseq.getValueAt(i)+ "  ");
         if ((i+1)%valuesPerLine == 0)
            System.out.println();
      }
   }
}
