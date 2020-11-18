public class RodCutting {
    // array for best price from rod cutting
    static int[] r = new int[0];
    // array for first cut of rod
    static int[] s = new int[0];
    // array for sale price of each rod
    static int[] p = new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    int MemoizedCutRod(int n) {
        for (int i = 1; i <= n; i++) {
            r[i] = -1;
        }
        return MemoizedCutRodAux(n);
    }

    int MemoizedCutRodAux(int n) {
        int q = 0;
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            q = 0;
        } else {
            q = -1;
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i] + MemoizedCutRodAux(n - i));
            }
        }
        r[n] = q;
        return q;
    }

    void MemoizedCutRodSoln(int n) {
        for (int i = 1; i <= n; i++) {
            r[i] = -1;
        }
        MemoizedCutRodSolnAux(n);
        for (int j = 1; j <= n; j++) {
            if (s[j] == 0) {
                s[j] = j;
            }
        }
    }

    int MemoizedCutRodSolnAux(int n) {
        int q = 0;
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            q = 0;
        } else {
            q = -1;
            r[n] = p[n];
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i] + MemoizedCutRodSolnAux(n - i));
                if (q > r[n]) {
                    r[n] = q;
                    s[n] = i;
                }
            }
        }
        //r[n] = q;
        return q;
    }

    void ExtendedBottomUpCutRodWithCutCost (int n, int cutCost) {
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = p[j];
            r[j] = p[j];
            int value = 0;
            for (int i = 1; i < j; i++) {
                value = p[i] + r[j-i] - cutCost;
                if (q < value) {
                    //System.out.println(i + " " + j + " " + (j-i) + " " + q + " " + value);
                    q = value;
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        for (int j = 1; j <= n; j++) {
            if (s[j] == 0) {
                s[j] = j;
            }
        }
    }

    void PrintCutRodSolution(int n) {
        int j = 0;
        for (int i = 1; i <= n; i++) {
            j = i;
            System.out.print("Rod length " + i + " should be cut into rod(s) of length ");
            while (j > 0)  {
                System.out.print(s[j] + ", ");
                j = j - s[j];
            }
            System.out.println("for a price of " + r[i]);
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int cutCost = 1;
        r = new int[n+1];
        s = new int[n+1];
        RodCutting test = new RodCutting();
        test.ExtendedBottomUpCutRodWithCutCost(n, cutCost);
        //System.out.println(" 0  1  2  3  4   5   6   7   8   9   10");
        //System.out.println(Arrays.toString(p));
        //System.out.println(Arrays.toString(r));
        //System.out.println(Arrays.toString(s));
        r = new int[n+1];
        s = new int[n+1];
        test.MemoizedCutRod(n);
        //System.out.println();
        //System.out.println(" 0  1  2  3  4   5   6   7   8   9   10");
        //System.out.println(Arrays.toString(p));
        //System.out.println(Arrays.toString(r));
        r = new int[n+1];
        s = new int[n+1];
        test.MemoizedCutRodSoln(n);
        //System.out.println();
        //System.out.println(" 0  1  2  3  4   5   6   7   8   9   10");
        //System.out.println(Arrays.toString(p));
        //System.out.println(Arrays.toString(r));
        //System.out.println(Arrays.toString(s));
        test.PrintCutRodSolution(n);
    }

}
