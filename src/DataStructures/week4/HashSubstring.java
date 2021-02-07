import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    //testing code
    /*private static Data readInput() throws IOException {
        SecureRandom random = new SecureRandom();
        int patternL = 5+random.nextInt(4);
        int textL = 300+random.nextInt(10000);

        StringBuilder pattern = new StringBuilder();
        StringBuilder text = new StringBuilder();

        for(int i=0;i<patternL;i++){
            int flip = random.nextInt(5);
            char c;
            if(flip>2){
                //uppercase
                c = (char)(65+random.nextInt(3));
            } else {
                //lowercase
                c = (char)(97+random.nextInt(3));
            }
            pattern.append(c);
        }
        for(int i=0;i<textL;i++){
            int flip = random.nextInt(5);
            char c;
            if(flip>2){
                //uppercase
                c = (char)(65+random.nextInt(3));
            } else {
                //lowercase
                c = (char)(97+random.nextInt(3));
            }
            text.append(c);
        }
        String finalPattern = pattern.toString();
        String finalText = text.toString();
        return new Data(finalPattern, finalText);
    }*/
    //end of testing code

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String pattern = input.pattern;
        int patLen = pattern.length();
        String text = input.text;
        int textLen = text.length();
        long prime = 1000000L * 10000L + 7;
        int x = 1;
        long pHash = hashFunc(pattern, prime, x);
        long[] hashes = precomputeHashes(text, patLen, prime, x);
        List<Integer> occurrences = new ArrayList<Integer>();
        int test = 0;
        for (int i = 0; i <= textLen - patLen; ++i) {
            if (pHash != hashes[i]) {
                continue;
            }
            if (text.startsWith(pattern, i)) {
                test++;
                occurrences.add(i);
            }
        }
        return occurrences;
    }

    private static long[] precomputeHashes(String s, int patternLength, long prime, long x) {
        long[] hashes = new long[s.length() - patternLength + 1];
        String sub = s.substring(s.length() - patternLength);
        hashes[s.length() - patternLength] = hashFunc(sub, prime, x);
//        long y = 1;
//        for (int i = 1; i <= patternLength; i++) {
//            y = ((y * x) % prime + prime) % prime;
//        }

        for (int i = s.length() - patternLength - 1; i >= 0; i--) {
//            hashes[i] = (((x * hashes[i + 1]) + s.charAt(i) - (y * s.charAt(i + patternLength))) % prime + prime) % prime;
//            if (hashes[i] < 0) {
//                out.println("We got a negative! " + i + "," + hashes[i]);
//            }
            long tempHash = (long)s.charAt(i)+hashes[i+1]*x-(long)s.charAt(i+patternLength)*(long)Math.pow(x,patternLength);
            hashes[i]=(tempHash%prime+prime)%prime;
        }
        return hashes;
    }

    private static long hashFunc(String s, long prime, long x) {
        long hash = 0;
        long tempHash;
        for (int i = s.length() - 1; i >= 0; i--) {
//            hash = (((hash * x + s.charAt(i)) % prime) + prime) % prime;
            tempHash = (hash+(long)s.charAt(i)*(long)Math.pow(x,i));
            hash = (tempHash%prime+prime)%prime;
        }
        return hash;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

//    {
//        String t = "";
//        String p = "";
//        String s = "";
//        int P = 1000000007;
//        int X = 1;
//        long[] hashes = new long[10];
//        long hash = 0;
//        long a = 0;
//        for (int i = s.length() - 1; i >= 0; i--) {
//            a = (hash+(long)t.charAt(i)*(long)Math.pow(X,i));
//            hash = (a%P+P)%P;
//        }
//        hashes[9]=hash;
//        for(int i=hashes.length-2;i>=0;i--){
//            long ab = (long)t.charAt(i)+hashes[i+1]*X-(long)t.charAt(i+p.length())*(long)Math.pow(X,p.length());
//            hashes[i]=(ab%P+P)%P;
//        }
//    }
}

