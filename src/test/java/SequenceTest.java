import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SequenceTest {

   @Test
   public void testGetLongestIncSubSequence() {
      Sequence sequence = new Sequence(20);

      sequence.append(2);
      sequence.append(6);
      sequence.append(3);
      sequence.append(4);
      sequence.append(1);
      sequence.append(2);
      sequence.append(9);
      sequence.append(5);
      sequence.append(8);

      assertThat("longest inc subsequence should be 2,3,4,5,8", sequence.getLongestIncreasingSubsequence().toString(), is("2,3,4,5,8"));
   }
}
