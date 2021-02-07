package AlgorithmToolBox.week5;

import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    char[] A = s.toCharArray();
    char[] B = t.toCharArray();
    int[][] editDistance = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      editDistance[i][0] = i;
    }
    for (int j = 0; j <= t.length(); j++) {
      editDistance[0][j] = j;
    }

    for (int j = 1; j <= B.length; j++) {
      for (int i = 1; i <= A.length; i++) {
        int insertion = editDistance[i][j - 1] + 1;
        int deletion = editDistance[i - 1][j] + 1;
        int mismatch = editDistance[i - 1][j - 1] + 1;
        int match = editDistance[i - 1][j - 1];
        if (A[i - 1] == B[j - 1]) {
          editDistance[i][j] = Math.min(Math.min(insertion, deletion), match);
        } else {
          editDistance[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
        }
      }
    }
    return editDistance[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
