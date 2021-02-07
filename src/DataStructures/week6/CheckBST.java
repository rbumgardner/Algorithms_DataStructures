package DataStructures.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class CheckBST {
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CheckBST().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        PotentialBST potentialBST = new PotentialBST();
        potentialBST.read();
        if (potentialBST.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements()) {
                tok = new StringTokenizer(in.readLine());
            }
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class PotentialBST {
        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            if (tree.length < 1) {
                return true;
            }
            ArrayList<Integer> result = new ArrayList<>();
            traverseInOrder(result, 0);
            return isSorted(result);
        }

        private boolean isSorted(ArrayList<Integer> result) {
            if (result.isEmpty() || result.size() == 1) {
                return true;
            }
            Iterator<Integer> it = result.iterator();
            Integer current, previous = it.next();
            while (it.hasNext()) {
                current = it.next();
                if (previous.compareTo(current) > 0) {
                    return false;
                }
                previous = current;
            }
            return true;
        }

        private void traverseInOrder(ArrayList<Integer> result, int rootIndex) {
            if (rootIndex == -1) {
                return;
            }
            traverseInOrder(result, tree[rootIndex].left);
            result.add(tree[rootIndex].key);
            traverseInOrder(result, tree[rootIndex].right);
        }

        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }
    }
}
