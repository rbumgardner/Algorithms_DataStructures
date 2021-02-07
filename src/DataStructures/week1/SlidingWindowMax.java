package DataStructures.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.System.nanoTime;

public class SlidingWindowMax {
    public static void main(String[] args) throws IOException {
        long start = nanoTime();
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new SlidingWindowMax().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
        long end = nanoTime();
//        System.out.println("elapsed time: " + (end - start));
    }

    public void run() throws IOException {
        SlidingWindow sWindow = new SlidingWindow();
        sWindow.read();
        int[] ans = sWindow.getMaxSequence();
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
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

    public class SlidingWindow {
        int n;
        int[] sequence;
        int m;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
//            n = 100000;
            sequence = new int[n];
            for (int i = 0; i < n; i++) {
                sequence[i] = in.nextInt();
//                sequence[i] = n--;
            }
            m = in.nextInt();
//            m = 33333;
        }

        int[] getMaxSequence() {
            int[] ans = new int[n - m + 1];
            Deque<Integer> window = new ArrayDeque<>(m);
            for (int i = 0; i < m; i++) {
                while (!window.isEmpty() && sequence[window.peekLast()] <= sequence[i]) {
                    window.removeLast();
                }
                window.addLast(i);
            }

            for (int i = m; i < n; i++) {
                ans[i - m] = sequence[window.peek()];
                while (!window.isEmpty() && window.peek() <= i - m) {
                    window.removeFirst();
                }
                while (!window.isEmpty() && sequence[window.peekLast()] <= sequence[i]) {
                    window.removeLast();
                }
                window.addLast(i);
            }
            ans[n-m] = sequence[window.peek()];
            return ans;
        }
    }
}