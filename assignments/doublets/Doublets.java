import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a HashSet of Strings.
 *
 * @author Rolf Versluis (rzv0018@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-06
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   private HashSet<String> lexicon;
   private boolean lexLoaded;
   private static List<Character> letterList = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s =
                 new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            boolean added = lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         lexLoaded = true;
         in.close();
      } catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param str1 the first string
    * @param str2 the second string
    * @return the Hamming distance between str1 and str2 if they are the
    * same length, -1 otherwise
    */
   @Override
   public int getHammingDistance(String str1, String str2) {
      if (str1 == null || str2 == null || str1.length() != str2.length()) {
         return -1;
      }
      int distance = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            distance++;
         }
      }
      return distance;
   }

   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    * <p>
    * Breadth-first search must be used in all implementing classes.
    *
    * @param start the starting word
    * @param end   the ending word
    * @return a minimum length word ladder from start to end
    */
   @Override
   public List<String> getMinLadder(String start, String end) {
      if (start == null || end == null) {
         return new ArrayList<String>();
      }
      if (start.equals("") || end.equals("")) {
         return new ArrayList<String>();
      }
      if (start.equals(end)) {
         return new ArrayList<String>(Arrays.asList(start));
      }
      breadthFirstSearchWords searcher = new breadthFirstSearchWords();
      return searcher.getDoublet(start, end);
   }

   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param word the given word
    * @return the neighbors of the given word
    */
   @Override
   public List<String> getNeighbors(String word) {
      Set<String> neighbors = new HashSet<>();
      List<String> neighbors2 = new ArrayList<>();
      char[] tryWord = word.toCharArray();
      String tryWord2 = "";
      for (int i = 0; i < word.length(); i++) {
         tryWord = word.toCharArray();
         for (Character letter : letterList) {
            tryWord[i] = letter;
            tryWord2 = new String(tryWord);
            if (isWord(tryWord2)) {
               neighbors.add(tryWord2);
            }
         }
      }
      for (String item : neighbors) {
         if (!item.equals(word)) {
            neighbors2.add(item);
         }
      }
      return neighbors2;
   }

   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   @Override
   public int getWordCount() {
      return lexicon.size();
   }

   /**
    * Checks to see if the given string is a word.
    *
    * @param str the string to check
    * @return true if str is a word, false otherwise
    */
   @Override
   public boolean isWord(String str) {
      if (str == null) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      return lexicon.contains(str);
   }

   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param sequence the given sequence of strings
    * @return true if the given sequence is a valid word ladder,
    * false otherwise
    */
   @Override
   public boolean isWordLadder(List<String> sequence) {
      if (sequence == null || sequence.isEmpty()) {
         return false;
      }
      boolean isLadder = true;
      String lastWord = "";
      for (int i = 0; i < sequence.size(); i++) {
         if (i !=0) {
            isLadder = getHammingDistance(sequence.get(i), lastWord) == 1;
            if (!isLadder) {
               return false;
            }
         }
         isLadder = isWord(sequence.get(i));
         if (!isLadder) {
            return false;
         }
         lastWord = sequence.get(i);
      }
      return true;
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

   private class breadthFirstSearchWords {
      List<String> solutionList;
      List<ArrayList> allSolutionsList;
      Set<String> currentSet;
      int bestLength;

      public List<String> getDoublet(String firstWord, String lastWord) {
         currentSet = new TreeSet<>();
         solutionList = new ArrayList<>();
         allSolutionsList = new ArrayList<>();
         List<String> sendBack = new ArrayList<>();
         breadthFirstMemory(firstWord, lastWord);
         int shortest = 200000;
         for (List<String> list : allSolutionsList) {
            if (list.size() < shortest) {
               sendBack = list;
               shortest = list.size();
            }
         }
         //System.out.println(allSolutionsList.size());
         return sendBack;
      }


      /**
       * Constructs a node for linking positions together.
       */
      private class Node {
         String word;
         Node predecessor;

         public Node(String word, Node predecessor) {
            this.word = word;
            this.predecessor = predecessor;
         }
      }


      /**
       * Initializes all to unvisited and launches a breadth-first
       * search in the current grid starting at position (x,y).
       */
      public void breadthFirstMemory(String first, String last) {
         bfsMemory(new Node(first, null), last);
      }

      /**
       * Search the current grid using breadth-first search. This algorithm is
       * identical to the breadth-first search above, except for the addition of
       * memory. Positions are added to the queue wrapped in a node, which is linked
       * to a node containing the position's immediately preceding neighbor; that is,
       * the neighbor responsible for having this position added to the queue.
       */
      private ArrayList<String> printNodes(Node node) {
         ArrayList<String> templist = new ArrayList<>();
         Node temp = node;
         while (temp.predecessor != null) {
            //System.out.print(temp.word + "<-");
            templist.add(temp.word);
            temp = temp.predecessor;
         }
         //System.out.println(temp.word);
         templist.add(temp.word);
         Collections.reverse(templist);
         return templist;
      }


      private void bfsMemory(Node start, String last) {
         Deque<Node> queue = new ArrayDeque<>();
         List<String> currentList;
         //visit(start);
         //process(start);
         queue.addLast(new Node(start.word, null));
         currentSet.add(start.word);
         Node pointer = start;
         while (!queue.isEmpty()) {
            Node wordNode = queue.removeFirst();
            /// do something with this word
            currentList = getNeighbors(wordNode.word);
            for (String hammOne : currentList) {
               if (!currentSet.contains(hammOne)) {
                  queue.addLast(new Node(hammOne, wordNode));
                  currentSet.add(hammOne);
                  //System.out.println(queue.size());
               }
               if (hammOne.equals(last)) {
                  pointer = new Node(hammOne, wordNode);
                  allSolutionsList.add(printNodes(pointer));
                  pointer = wordNode;
               }
            }
         }
      }
   }
}

