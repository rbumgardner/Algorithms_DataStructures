package DataStructures.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeOrders {
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeOrders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeReader treeReader = new TreeReader();
        treeReader.read();
        print(treeReader.inOrder());
        print(treeReader.preOrder());
        print(treeReader.postOrder());
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

    public class TreeReader {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            traverseInOrder(result, 0);
            return result;
        }

		private void traverseInOrder(ArrayList<Integer> result, int rootIndex) {
			if (rootIndex == -1) {
				return;
			}
			traverseInOrder(result, left[rootIndex]);
			result.add(key[rootIndex]);
			traverseInOrder(result, right[rootIndex]);
		}

		List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            traversePreOrder(result, 0);
            return result;
        }

		private void traversePreOrder(ArrayList<Integer> result, int rootIndex) {
			if (rootIndex == -1) {
				return;
			}
			result.add(key[rootIndex]);
			traversePreOrder(result, left[rootIndex]);
			traversePreOrder(result, right[rootIndex]);
        }

		List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            traversePostOrder(result, 0);
            return result;
        }

		private void traversePostOrder(ArrayList<Integer> result, int rootIndex) {
        	if (rootIndex == -1) {
        		return;
			}
        	traversePostOrder(result, left[rootIndex]);
        	traversePostOrder(result, right[rootIndex]);
        	result.add(key[rootIndex]);
		}
	}
}
