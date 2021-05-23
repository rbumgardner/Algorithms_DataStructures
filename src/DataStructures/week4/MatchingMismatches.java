package DataStructures.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MatchingMismatches {
    private final int prime1 = 1000000007;
    private final int prime2 = 1000011161;
    private final int x1 = 31;
    private final int x2 = 107;
    long[] patternH1;
    long[] patternH2;
    long[] textH1;
    long[] textH2;
    long[] p1Powers;
    long[] p2Powers;
    int mismatchCount = 0;
    int mismatchCountMax = 0;

    static public void main(String[] args) {
        new MatchingMismatches().run();
    }

    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        in.lines().forEach(line -> {
            StringTokenizer tok = new StringTokenizer(line);
            mismatchCountMax = Integer.valueOf(tok.nextToken());
            String s = tok.nextToken();
            String t = tok.nextToken();
            List<Integer> ans = solve(s, t);
            out.format("%d ", ans.size());
            if (ans.size() > 0) {
                out.println(ans.stream()
                                    .map(n -> String.valueOf(n))
                                    .collect(Collectors.joining(" "))
                );
            }
        });
        out.flush();
        out.close();
    }

    public List<Integer> solve(String text, String pattern) {
        precomputeStuff(text, pattern);
        return checkPattern(text, pattern);
    }

    private ArrayList<Integer> checkPattern(String text, String pattern) {
        ArrayList<Integer> pos = new ArrayList<>();
        for (int offset = 0; offset < text.length() - pattern.length() + 1; offset++) {
            if (mismatchCountMax < pattern.length()) {
                mismatchCount = 0;
                binarySearch(0, pattern.length() - 1, offset);
                if (mismatchCount <= mismatchCountMax) {
                    pos.add(offset);
                }
            } else {
                pos.add(offset);
            }
        }
        return pos;
    }

    private void binarySearch(int left, int right, int offset) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            //test pattern[mid] == text[mid+i]
            if (!checkHashes(mid, mid, offset)) {
                mismatchCount++;
                if (mismatchCount > mismatchCountMax) {
                    return;
                }
            }

            //check left side
            if (!checkHashes(left, mid - 1, offset)) {
                binarySearch(left, mid - 1, offset);
            }

            //check right side
            if (!checkHashes(mid + 1, right, offset)) {
                binarySearch(mid + 1, right, offset);
            }
        }
        return;
    }

    private boolean checkHashes(int left, int right, int offset) {
        boolean match = false;
        int length = right - left + 1;
        int textPos = left + offset;
        long patHash1 = ((patternH1[left + length] % prime1) - (p1Powers[length] * patternH1[left] % prime1) + prime1) % prime1;
        long patHash2 = ((patternH2[left + length] % prime2) - (p2Powers[length] * patternH2[left] % prime2) + prime2) % prime2;
        long textHash1 = ((textH1[textPos + length] % prime1) - (p1Powers[length] * textH1[textPos] % prime1) + prime1) % prime1;
        long textHash2 = ((textH2[textPos + length] % prime2) - (p2Powers[length] * textH2[textPos] % prime2) + prime2) % prime2;

        if ((patHash1 == textHash1) && (patHash2 == textHash2)) {
            match = true;
        }

        return match;
    }

    private void precomputeStuff(String text, String pattern) {
        patternH1 = precomputeHashes(pattern, x1, prime1); //O(n)
        patternH2 = precomputeHashes(pattern, x2, prime2); //O(n)
        textH1 = precomputeHashes(text, x1, prime1); //O(m)
        textH2 = precomputeHashes(text, x2, prime2); //O(m)
        p1Powers = precomputePowers(prime1, x1, pattern.length()); //O(m)
        p2Powers = precomputePowers(prime2, x2, pattern.length()); //O(m)
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
}
