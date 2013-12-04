/*
   Sequence is a class that represents a sequence of integers.  It 
   contains a method for finding the longest (strictly) increasing
   subsequence, to be written by students.
	
	J.W. Benham		November 23-28, 2012
   J.W. Benham    Modified November 6, 2013
                  Code deleted to make this a programming project
*/

import java.util.*;

class Sequence
{
   private int[]  seqValue;		// an array to hold the sequence
	private int	   seqLength,		// the number of integers in the sequence
	               maxLength;	   // the number of places in the array seqValue
			 
   /* inner class TableEntry for an entry in the dynamic 
      programming table used by the method
      getLongestIncreasingSubsequence
   */
          
	private class TableEntry implements Comparable<TableEntry>
	{
		private final Integer value;
      private int index = -1;

		   // The best last value so far for a list of given length

      /*
        YOU WILL NEED TO ADD OTHER ATTRIBUTES TO BE ABLE TO
        EXTRACT THE LONGEST INCREASING SUBSEQUENCE FROM THE TABLE
        YOU WILL NEED TO WRITE ONE OR MORE CONSTRUCTORS WITH APPROPRIATE
        PARAMETERS TO CREATE TABLE ENTRIES
      */

      public TableEntry(Integer value) {
         this.value = value;
      }

      public TableEntry(Integer value, Integer index) {
         this.value = value;
         this.index = index;
      }
         
		public Integer getValue()                {return value;}

      private int getIndex() {
         return index;
      }

      private void setIndex(int index) {
         this.index = index;
      }

      @Override
      public boolean equals(Object o) {
         if(o instanceof TableEntry) {
            return this.value.equals(((TableEntry) o).getValue());
         }

         return false;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public int compareTo(TableEntry o) {
         if(o == null) {
            return -1;
         }

         return this.value.compareTo(o.getValue());
      }

      // YOU SHOULD ALSO WRITE GET AND SET METHODS FOR OTHER ATTRIBUTES
	} // inner class TableEntry 
		
		 
	public Sequence(int mxLength)
	{
	    maxLength = mxLength;
		 seqValue = new int[maxLength];
		 seqLength = 0;
	}
	
	/*
	   Returns the length of the sequence
	*/
	public int getLength()
	{
	   return seqLength;
	}
	
	/*
	   Returns the maximum allowed length of the sequence
	*/
	public int getMaxLength()
	{
	   return maxLength;
	}
	
	/*
	   Returns value at the specified position
		
		pre-condition:  0 <= position < getLength();
	*/
	public int getValueAt(int position)
	{
	   return seqValue[position];
	}
	/*
	   Appends value to the end of the sequence
		
		pre-condition:  getLength() < getMaxLength
		post-condition: getLength() == getLength()@pre + 1
		                getValueAt[getLength()-1] == value
	*/
	public void append(int value)
	{
	   seqValue[seqLength] = value;
		seqLength++;
	}
	
	/*
	   Returns the longest strictly increasing subsequence
      It should use the dynamic programming algorithm for this
      that we discussed in class		
	*/
	public Sequence getLongestIncreasingSubsequence() {
      //TableEntry[][] bestSequences = new TableEntry[seqLength][seqLength];
       /* A table to maintain the best final values in the longest
		     increasing subsequences from seq(i) as i goes from 0 to 
			  seqLength-1.
		*/

      if(this.getLength() == 0) {
         return new Sequence(0);
      }

      List<TableEntry> lis = new ArrayList<TableEntry>(seqLength);
      int[] P = new int[seqLength]; // will store indexes of parent elements in the LIS so that we can recreate the LIS later

      for(int i = 0; i < seqLength; i++) {
         final int currentSeqValue = this.getValueAt(i);
         final TableEntry entry = new TableEntry(currentSeqValue, i);
         final int maxIdx = lis.size() - 1;

         if(!lis.isEmpty() && currentSeqValue <= lis.get(maxIdx).getValue()) {
            int insertionPoint = findInsertionPoint(lis, entry);

            if(insertionPoint == 0) {
               // No elements before this one, so this must start a (candidate) LIS
               P[i] = -1;
            }
            else {
               // Store the index of the element < this one
               P[i] = lis.get(insertionPoint - 1).getIndex();
            }

            lis.set(insertionPoint, entry);
         }
         else {
            // This number is the ending number in a LIS of this sequence.
            // Track its parent.
            P[i] = lis.isEmpty() ? -1 : lis.get(maxIdx).getIndex();
            lis.add(entry);
         }
      }

      return buildLIS(P, lis.get(lis.size() - 1).getIndex());
   }

   /**
    * Builds the lis from the P array.
    * @param P array of parent indicies
    * @param idx The starting index
    * @return A LIS of this sequence
    */
   private Sequence buildLIS(int[] P, int idx) {
      Sequence lis = new Sequence(this.getLength());
      Deque<Integer> stack = new ArrayDeque<Integer>(this.getLength());

      while(idx >= 0) {
         stack.addFirst(this.getValueAt(idx));
         idx = P[idx];
      }

      for(Integer val : stack) {
         lis.append(val);
      }

      return lis;
   }

   /**
    * Returns the index of the smallest element >= entry in list
    * @param list List to check
    * @param entry The key
    */
   private static int findInsertionPoint(List<TableEntry> list, TableEntry entry) {
      int insertionPoint = Collections.binarySearch(list, entry);

      if(insertionPoint < 0) {
         insertionPoint++;
         insertionPoint *= -1;
      }

      return insertionPoint;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();

      for(int i = 0; i < this.getLength(); i++) {
         builder.append(this.getValueAt(i));
         builder.append(',');
      }

      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
   }
} 