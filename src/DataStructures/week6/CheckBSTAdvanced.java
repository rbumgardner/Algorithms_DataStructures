package DataStructures.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CheckBSTAdvanced {
    long minValue = Long.MIN_VALUE;
    boolean isBST = true;
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CheckBSTAdvanced().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        PotentialBST tree = new PotentialBST();
        tree.read();
        tree.isBinarySearchTree();
        if (isBST) {
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

        public void isBinarySearchTree() {
            if (tree.length < 1) {
                isBST = true;
                return;
            }
            checkTree(0);
        }

        private void checkTree(int nodeIndex) {
            if (nodeIndex == -1) {
                return;
            }
            Node node = tree[nodeIndex];
            checkTree(node.left);

            if (node.key < minValue || (node.key == minValue && hasLeftChild(node))) {
                isBST = false;
                return;
            } else {
                minValue = node.key;
            }

            checkTree(node.right);
        }

        private boolean hasLeftChild(Node node) {
            return node.left != -1;
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
