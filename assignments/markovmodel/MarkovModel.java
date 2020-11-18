import java.io.File;
import java.util.*;
import java.io.IOException;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Rolf Versluis (rzv0018@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;
   private String firstKgram;

   // add other fields as you need them ...


   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      if (K < 1) {
         return;
      }
      if (sourceText == null || sourceText.length() < K) {
         return;
      }
      model = new HashMap<String, String>();
      String kgram = null;
      String letter = null;
      if (sourceText.length() == K) {
         kgram = sourceText;
         letter = "";
         model.put(kgram, letter);
         return;
      }
      if (model.isEmpty()) {
         firstKgram = sourceText.substring(0, K);
      }
      for (int i = 0; i < sourceText.length() - K; i++) {
         // lookup kgram. If new, add new entry. If exists, append letter
         kgram = sourceText.substring(i, i + K);
         letter = sourceText.substring(i + K, i + K + 1);
         if (model.containsKey(kgram)) {
            model.put(kgram, model.get(kgram) + letter);
         } else {
            if (kgram != null) {
               model.put(kgram, letter);
            }
         }
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstKgram;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      Random ran = new Random();
      int number = ran.nextInt(model.size());
      int counter = 0;
      for (String item : model.keySet()) {
         if (counter == number) {
            return item;
         }
         counter++;
      }
      //System.out.println(counter + " " + number);
      return null;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
    public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      if (kgram == null || kgram.isEmpty()) {
         return '\u0000';
      }
      if (model.get(kgram).isEmpty()) {
         return '\u0000';
      }
      Random ran = new Random();
      int number = 0;
      String letterChoices = model.get(kgram);
      number = ran.nextInt(letterChoices.length());
      return letterChoices.charAt(number);
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
    @Override
    public String toString() {
      return model.toString();
   }

}
