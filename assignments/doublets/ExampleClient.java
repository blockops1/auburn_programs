import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * ExampleClient.java
 * Provides example calls to WordLadderGame methods in an instance of
 * the Doublets class.
 *
 * The word list files must be extracted into the current directory
 * before running this class.
 *
 *      jar xf WordLists.jar
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-06
 */
public class ExampleClient {

    /** Drives execution. */
    public static void main(String[] args) throws FileNotFoundException {
        WordLadderGame doublets = new Doublets(new FileInputStream(new File("wordlists/sowpods.txt")));


        System.out.println(doublets.getHammingDistance("tiger", "tiger"));
        System.out.println(doublets.getHammingDistance("tiger", "eagle"));
        System.out.println(doublets.getHammingDistance("war", "eagle"));
        System.out.println(doublets.getHammingDistance("barner", "bammer"));

        System.out.println(doublets.isWord("tiger"));
        System.out.println(doublets.isWord("eagle"));
        System.out.println(doublets.isWord("aubie"));

        System.out.println(doublets.getWordCount());
       System.out.println(doublets.isWordLadder(Arrays.asList("cat", "cat", "cat", "cat")));
        System.out.println(doublets.isWordLadder(Arrays.asList("cat", "cot", "zot", "dot")));
        System.out.println(doublets.isWordLadder(Arrays.asList("cat", "cot", "pot", "dot")));
       System.out.println(doublets.isWordLadder(Arrays.asList("xxxx", "xxxy", "xxxz")));
        System.out.println(doublets.getNeighbors("tiger"));

        System.out.println(doublets.getMinLadder("cat", "hat"));
       System.out.println(doublets.getMinLadder("cat", "hop"));
       System.out.println(doublets.getMinLadder("cat", "pun"));
       System.out.println(doublets.getMinLadder("health", "forget"));
       System.out.println(doublets.getMinLadder("clash", "clown"));
       System.out.println(doublets.getMinLadder("cat", "dog"));
       System.out.println(doublets.getMinLadder("aa", "bb"));
    }
}

/*

RUNTIME OUTPUT

0
4
-1
2
true
true
false
267751
false
true
[liger, niger, tiler, timer, titer, tiges]
[cat, hat]

 */

