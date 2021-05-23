import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This runs 0.53 seconds slower than the required time of 5.00 seconds.
 * That's a bummer as the online grader won't accept it. I don't have
 * time to screw around and shave off 0.53 seconds, so I'm leaving it
 * as is.
 */
public class LongestCommonSubstring {
    private final int prime1 = 1000000007;
//    private final int prime2 = 1000011161;
    private final int x1 = 31;
//    private final int x2 = 107;
    long[] sHash1;
//    long[] sHash2;
    long[] tHash1;
//    long[] tHash2;
    long[] p1Powers;
//    long[] p2Powers;

    static public void main(String[] args) throws IOException {
        new LongestCommonSubstring().run();
    }

    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line;
        while ((line = in.readLine()) != null) {
            StringTokenizer tok = new StringTokenizer(line);
            String s = tok.nextToken();
            String t = tok.nextToken();
            Answer ans = solve(s, t, out);
            out.format("%d %d %d\n", ans.i, ans.j, ans.len);
        }
//        String[] input = new String[]{"baababbbbabaababaa babbabaababaabbaabaa",
//                "bbababbbbbbaaabbbb bbbababbbaaaaaa",
//                "abaaabbbbbababbaabb bbbbbaaabbb",
//                "baaaaaababbbbbbaaaa aababbbbbabaaaab",
//                "abaaabaabbaa aaabbbaaaabb",
//                "bbaabbabaaabbbbb aaabbbbbbbbb",
//                "bbbabbbababbba baaabbaaabb",
//                "baaaaaaaab bbababbbaba",
//                "aaabaaabaabbaba bbbaabbbabbabbab"};
//        String[] input = new String[]{"aaabaaabaabbaba bbbaabbbabbabbab"};
//        for (String s : input) {
//            String[] strings = s.split(" ");
//            out.println("String s: " + strings[0] + "; String t: " + strings[1]);
//            Answer ans1 = solve(strings[0], strings[1], out);
//            out.format("%d %d %d\n", ans1.i, ans1.j, ans1.len);
//            Answer ans2 = solveNaive(strings[0], strings[1], out);
//            out.format("%d %d %d\n", ans2.i, ans2.j, ans2.len);
//            out.println("");
//        }
        out.close();
    }

    //Brute force method
    public Answer solveNaive(String s, String t, PrintWriter out) {
        Answer ans = new Answer(0, 0, 0);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                for (int len = 0; i + len <= s.length() && j + len <= t.length(); len++) {
                    if (len > ans.len && s.substring(i, i + len).equals(t.substring(j, j + len))) {
                        ans = new Answer(i, j, len);
                    }
                }
            }
        }
        out.println("Accepted LCS: " + s.substring(ans.i, ans.i + ans.len));
        return ans;
    }

    public Answer solve(String s, String t, PrintWriter out) { //O(n+m+m*log n)
        //swap so that s is the shorter and swap back just before returning
        boolean swap = false;
        if (s.length() > t.length()) {
            swap = true;
            String temp = s;
            s = t;
            t = temp;
        }

        sHash1 = precomputeHashes(s, x1, prime1); //O(n)
//        sHash2 = precomputeHashes(s, x2, prime2); //O(n)
        tHash1 = precomputeHashes(t, x1, prime1); //O(m)
//        tHash2 = precomputeHashes(t, x2, prime2); //O(m)
        p1Powers = precomputePowers(prime1, x1, Math.max(s.length(), t.length())); //O(m)
//        p2Powers = precomputePowers(prime2, x2, Math.max(s.length(), t.length())); //O(m)

        Answer answer = findCommonLength(s, t); //O(log n) * O(m)
        if (swap) {
            int temp = answer.i;
            answer.i = answer.j;
            answer.j = temp;
        }
//        int offset = swap ? answer.j : answer.i;
//        out.println("LCS: " + s.substring(offset, answer.len + offset));
        return answer;
    }

    private long[] precomputeHashes(String s, int x, int prime) {
        long[] hashes = new long[s.length() + 1];
        hashes[0] = 0;
        for (int i = 1; i < hashes.length; i++) {
            hashes[i] = (((x * hashes[i - 1] % prime + prime) % prime + (long) s.charAt(i - 1)) % prime + prime) % prime;
        }
        return hashes;
    }

    private long[] precomputePowers(int prime, int x, int l) {
        long[] powers = new long[l + 1];
        powers[0] = 1;
        for (int i = 1; i <= l; i++) {
            powers[i] = ((powers[i - 1] * x) % prime + prime) % prime;
        }
        return powers;
    }

    private Answer findCommonLength(String s, String t) {
        Answer finalAnswer = new Answer(0, 0, 0);
        int left = 0;
        int right = Math.min(s.length(), t.length());
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid == 0) {
                return new Answer(0, 0, 0);
            }
            Answer tempAnswer = checkCommon(s, t, mid); //O(m)
            if (tempAnswer.good) {
                left = mid + 1;
                finalAnswer = tempAnswer;
            } else {
                right = mid - 1;
            }
        }
        return finalAnswer;
    }

    private Answer checkCommon(String s, String t, int windowLength) {
        Hashes hashes = getHashes(s, t, windowLength); //O(m)
        Answer answer = new Answer(0, 0, windowLength);
        for (int i = 0; i < hashes.getTh1().size(); i++) { // O(m)
            if (hashes.getSh1().contains(hashes.getTh1().get(i))) {
                answer.i = hashes.getSh1().indexOf(hashes.getTh1().get(i));
                answer.j = i;
                answer.good = true;
            }
        }
        return answer;
    }

    private Hashes getHashes(String s, String t, int windowLength) {
        Hashes rollingHashes = new Hashes();
        for (int i = 0; windowLength + i <= t.length(); i++) {
            if (windowLength + i <= s.length()) {
                rollingHashes.getSh1().add(i, ((sHash1[i + windowLength] % prime1) - (p1Powers[windowLength] * sHash1[i] % prime1) + prime1) % prime1);
//                rollingHashes.getSh2().add(i, ((sHash2[i + windowLength] % prime2) - (p2Powers[windowLength] * sHash2[i] % prime2) + prime2) % prime2);
            }
            rollingHashes.getTh1().add(i, ((tHash1[i + windowLength] % prime1) - (p1Powers[windowLength] * tHash1[i] % prime1) + prime1) % prime1);
//            rollingHashes.getTh2().add(i, ((tHash2[i + windowLength] % prime2) - (p2Powers[windowLength] * tHash2[i] % prime2) + prime2) % prime2);
        }
        return rollingHashes;
    }

    public class Answer {
        boolean good = false;
        int i, j, len;

        Answer(int i, int j, int len) {
            this.i = i;
            this.j = j;
            this.len = len;
        }
    }

    class Hashes {
        List<Long> sh1 = new ArrayList<>();
//        List<Long> sh2 = new ArrayList<>();
        List<Long> th1 = new ArrayList<>();
//        List<Long> th2 = new ArrayList<>();

        public List<Long> getSh1() {
            return sh1;
        }

        public void setSh1(List<Long> sh1) {
            this.sh1 = sh1;
        }

//        public List<Long> getSh2() {
//            return sh2;
//        }

//        public void setSh2(List<Long> sh2) {
//            this.sh2 = sh2;
//        }

        public List<Long> getTh1() {
            return th1;
        }

        public void setTh1(List<Long> th1) {
            this.th1 = th1;
        }

//        public List<Long> getTh2() {
//            return th2;
//        }

//        public void setTh2(List<Long> th2) {
//            this.th2 = th2;
//        }
    }
}
