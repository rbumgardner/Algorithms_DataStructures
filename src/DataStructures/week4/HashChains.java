import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private ArrayList[] elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                add(query.s);
                break;
            case "del":
                delete(query.s);
                break;
            case "find":
                writeSearchResult(find(query.s));
                break;
            case "check":
                out.println(check(query.ind));
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    private void add(String s) {
        if(!find(s)) {
            int hash = hashFunc(s);
            ArrayList<String> list = elems[hash];
            list.add(0, s); //always add to the head
        }
    }

    private void delete(String s) {
        if (find(s)) {
            int hash = hashFunc(s);
            ArrayList<String> list = elems[hash];
            list.remove(s);
        }
    }

    private boolean find(String s) {
        int hash = hashFunc(s);
        ArrayList<String> list = elems[hash];
        return list.contains(s);
    }

    private String check(int index) {
        if (!elems[index].isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator iterator = elems[index].iterator();
            while (iterator.hasNext()) {
                String next = (String) iterator.next();
                sb.append(next);
                sb.append(" ");
            }
            return sb.toString();
        }
        return "";
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        elems = new ArrayList[bucketCount];
        for (int i = 0; i < elems.length; i++) {
            elems[i] = new ArrayList<>();
        }
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
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
}
