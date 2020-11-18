/**
 * HandoutExamples.java
 * Generates examples from the assignment handout.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-17
 *
 */
public class HandoutExamples {

    /** Drives execution. */
    public static void main(String[] args) {
        String sourceText = "ABCDE";
        int k = 1;
        MarkovModel model = new MarkovModel(k, sourceText);
        System.out.println("k = " + k + ", source text: " + sourceText);
        System.out.println("The first kgram: " + model.getFirstKgram());
        System.out.println("A random kgram: " + model.getRandomKgram());
        System.out.println("All kgrams: " + model.getAllKgrams());
        System.out.println("The Markov model: ");
        System.out.println(model);
        for (int i = 0; i < sourceText.length(); i++) {
            String kgram = model.getRandomKgram();
            System.out.println(kgram + " " + model.getNextChar(kgram));
        }
        //System.out.println(model.getFirstKgram());
    }
}


/*

RUNTIME OUTPUT:

k = 2, source text: agggcagcgggcg
The first kgram: ag
A random kgram: cg
All kgrams: [gg, cg, ag, gc, ca]
The Markov model:
{gg=gcgc, cg=g, ag=gc, gc=agg, ca=g}

 */

