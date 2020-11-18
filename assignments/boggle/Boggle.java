import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Boggle implements WordSearchGame {
   // create board for searching in two dimensional array
   String[][] board;
   TreeSet<String> lexicon;
   boolean lexLoaded;
   int size;
   // visited positions in the search area
   private boolean[][] visited;


   public Boggle() {
   }

   /**
    * Loads the lexicon into a data structure for later use.
    *
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   @Override
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      lexicon = new TreeSet<String>();
      try {
         Scanner s =
                 new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (s.hasNext()) {
            String str = s.next();
            boolean added = lexicon.add(str.toUpperCase());
            s.nextLine();
         }
         lexLoaded = true;
      } catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }

   /**
    * Creates a String representation of the board, suitable for printing to
    * standard out. Note that this method can always be called since
    * implementing classes should have a default board.
    */
   @Override
   public String getBoard() {
      StringBuilder output = new StringBuilder();
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            output.append(board[i][j]);
         }
         output.append("\n");
      }
      return output.toString();
   }

   public String getVisited() {
      StringBuilder output = new StringBuilder();
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            if (visited[i][j]) {
               output.append("T-");
            } else {
               output.append("F-");
            }
         }
         output.deleteCharAt(output.length() - 1);
         output.append("\n");
      }
      return output.toString();
   }

   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    *
    * @param letterArray This array of length N^2 stores the contents of the
    *                    game board in row-major order. Thus, index 0 stores the contents of board
    *                    position (0,0) and index length-1 stores the contents of board position
    *                    (N-1,N-1). Note that the board must be square and that the strings inside
    *                    may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *                                  square.
    */
   @Override
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      size = (int) Math.sqrt(letterArray.length);
      if (letterArray.length != size * size) {
         throw new IllegalArgumentException();
      }
      board = new String[size][size];
      int counter = 0;
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            board[i][j] = letterArray[counter];
            counter++;
         }
      }
   }

   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    *
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *                          characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    * found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException    if loadLexicon has not been called.
    */
   @Override
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      SortedSet<String> results = new TreeSet<>();
      List<String> foundWords2 = new ArrayList<>();
      //List<Integer> result = null;
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            // find all valid 6 letter words from this position and append to the set
            foundWords2 = findValidWords(i, j, minimumWordLength);
            results.addAll(foundWords2);
         }
      }
      return results;
   }

   List<String> findValidWords(int x, int y, int minWordLength) {
      markAllUnvisited();
      List<String> foundWords = new ArrayList<>();
      Position start = new Position(x, y);
      List<String> wordInProgress = new ArrayList<>();
      if (isValid(start)) {
         foundWords = findWords(start, minWordLength, wordInProgress, foundWords);
      }
      return foundWords;
   }

   List<String> findWords(Position position, int minWordLength, List<String> wordInProgress, List<String> foundWords) {
      if (isVisited(position) || !isValid(position) || !isValidPrefix(assembleWord(wordInProgress))) {
         return foundWords;
      }
      visit(position);
      wordInProgress.add(board[position.x][position.y]);
      if (assembleWord(wordInProgress).length() >= minWordLength && isValidWord(assembleWord(wordInProgress))) {
         foundWords.add(assembleWord(wordInProgress));
      }
      for (Position neighbor : position.neighbors()) {
         foundWords = findWords(neighbor, minWordLength, wordInProgress, foundWords);
      }
      unVisit(position);
      wordInProgress.remove(wordInProgress.size() - 1);
      return foundWords;
   }

   /**
    * Computes the cumulative score for the scorable words in the given set.
    * To be scoreable, a word must (1) have at least the minimum number of characters,
    * (2) be in the lexicon, and (3) be on the board. Each scorable word is
    * awarded one point for the minimum number of characters, and one point for
    * each character beyond the minimum number.
    *
    * @param words             The set of words that are to be scored.
    * @param minimumWordLength The minimum number of characters required per word
    * @return the cummulative score of all scorable words in the set
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException    if loadLexicon has not been called.
    */
   @Override
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      int score = 0;
      for (String item : words) {
         score += 1 + (item.length() - minimumWordLength);
      }
      return score;
   }

   /**
    * Determines if the given word is in the lexicon.
    *
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException    if loadLexicon has not been called.
    */
   @Override
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      return lexicon.contains(wordToCheck);
   }

   /**
    * Determines if there is at least one word in the lexicon with the
    * given prefix.
    *
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException    if loadLexicon has not been called.
    */
   @Override
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      String ceilingWord = lexicon.ceiling(prefixToCheck);
      boolean testWord;
      try {
         assert ceilingWord != null;
         testWord = ceilingWord.startsWith(prefixToCheck);
      } catch (NullPointerException e) {
         System.out.println("caught null pointer exception " + prefixToCheck + " returned ceiling word " + ceilingWord);
         return false;
      }
      return testWord;
   }

   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    *
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    * that makes up the word on the game board. If word is not on the game
    * board, return an empty list. Positions on the board are numbered from zero
    * top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    * board, the upper left position is numbered 0 and the lower right position
    * is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException    if loadLexicon has not been called.
    */
   @Override
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!lexLoaded) {
         throw new IllegalStateException();
      }
      List<Integer> result = null;
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            result = depthFirstSearch(i, j, wordToCheck);
            if (result.size() > 0) {
               return result;
            }
         }
      }
      return result;
   }

   /**
    * Initializes visited to false.
    */
   private void markAllUnvisited() {
      visited = new boolean[size][size];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }

   /**
    * Initializes all to unvisited and launches a depth-first
    * search in the current grid starting at position (x,y).
    */
   List<Integer> depthFirstSearch(int x, int y, String wordToSearch) {
      markAllUnvisited();
      Position start = new Position(x, y);
      List<Integer> successPath = new ArrayList<>();
      List<String> wordInProgress = new ArrayList<>();
      StringBuilder wordSoFar = new StringBuilder();
      if (isValid(start)) {
         //order = 1;
         successPath = dfsOneWord(start, wordToSearch, wordInProgress, successPath);
      }
      return successPath;
   }

   /**
    * Perform a depth-first traversal on grid starting from the given
    * position. This algorithm is the one most closely associated with
    * "depth-first search with backtracking" as used in most writings.
    */
   private List<Integer> dfsOneWord(Position position, String wordToSearch,
                                    List<String> wordInProgress, List<Integer> successPath) {
      if (isVisited(position) || !isValid(position) || !wordToSearch.startsWith(assembleWord(wordInProgress))) {
         return successPath;
      }
      visit(position);
      wordInProgress.add(board[position.x][position.y]);
      successPath.add(position.x * size + position.y);
      if (assembleWord(wordInProgress).equals(wordToSearch)) {
         return successPath;
      }
      for (Position neighbor : position.neighbors()) {
         successPath = dfsOneWord(neighbor, wordToSearch, wordInProgress, successPath);
         if (assembleWord(wordInProgress).equals(wordToSearch)) {
            return successPath;
         }
      }
      unVisit(position);
      wordInProgress.remove(wordInProgress.size() - 1);
      successPath.remove(successPath.size() - 1);
      return successPath;
   }

   String assembleWord (List<String> wordBuilder) {
      StringBuilder buildIt = new StringBuilder();
      for (String item : wordBuilder) {
         buildIt.append(item);
      }
      return buildIt.toString();
   }


   ///////////////////////////////////////////
   // Position class and associated methods //
   ///////////////////////////////////////////

   /**
    * Is this position valid in the search area?
    */
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < size) && (p.y >= 0) && (p.y < size);
   }

   /**
    * Has this valid position been visited?
    */
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void unVisit(Position p) {
      visited[p.x][p.y] = false;
   }

   /**
    * Models an (x,y) position in the grid.
    */
   private class Position {
      int x;
      int y;

      /**
       * Constructs a Position with coordinates (x,y).
       */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }

      /**
       * Returns a string representation of this Position.
       */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }

      /**
       * Returns all the neighbors of this Position.
       */
      public Position[] neighbors() {
         // number of neighbors, degrees of motion
         int MAX_NEIGHBORS = 8;
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }
}
