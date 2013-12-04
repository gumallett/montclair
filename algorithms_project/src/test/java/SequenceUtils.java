import java.util.Random;

public class SequenceUtils {
   public static Sequence randomSequence(int maxLength) {
      Sequence randomSequence = new Sequence(maxLength);

      // Generate a sequence of random integers
      Random randomNumberGenerator = new Random();

      for (int i = 0; i < maxLength; i++) {
         randomSequence.append(randomNumberGenerator.nextInt(1000));
      }

      return randomSequence;
   }
}
