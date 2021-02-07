package DataStructures.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeHeight {
    int n;
    int parent[];

    void read() throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = in.nextInt();
        }
    }

    int computeHeight() {
        // Replace this code with a faster implementation
        Node<Integer>[] nodes = new Node[parent.length];
        for (int i = 0; i < parent.length; i++) {
            nodes[i] = new Node<Integer>();
        }
        int rootIndex = 0;
        for (int childIndex = 0; childIndex < parent.length; childIndex++) {
            if (parent[childIndex] == -1) {
                rootIndex = childIndex;
            } else {
                nodes[parent[childIndex]].addChild(nodes[childIndex]);
            }
        }
        return getDepth(nodes[rootIndex]);
    }

    private int getDepth(Node<Integer> node) {
        int height = 0;
        if (node == null) {
            return height;
        }

        Queue<Node<Integer>> queue = new ArrayDeque<Node<Integer>>();
        queue.add(node);
        Node<Integer> front = null;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                front = queue.poll();
                if (front.hasChildren()) {
                    for (Node<Integer> child : front.getChildren()) {
                        queue.add(child);
                    }
                }
            }
            height++;
        }
        return height;
    }

    static public void main(String[] args) throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }

    static class FastScanner {
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

    static class Node<T> {
        private T value;
        private Node<T> parent = null;
        private List<Node<T>> children = null;
//        private int depth;

        public void Node(T value) {
            this.value = value;
        }

        public void addChild(Node<T> child) {
            if (children == null) {
                children = new ArrayList<>();
            }
            child.parent = this;
//            child.depth = this.depth + 1;
            children.add(child);
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public boolean hasChildren() {
            return children != null && !children.isEmpty();
        }

        public int getChildCount() {
            if (hasChildren()) {
                return children.size();
            } else {
                return 0;
            }
        }
    }
}
