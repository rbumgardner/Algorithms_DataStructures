package DataStructures.week4;

import java.util.*;
import java.io.*;

public class SubstringEquality {
	public class Solver {
		private String s;
		private int p1 = 1000000007;
		private int p2 = 1000000009;
		private int x = 31;
		long[] h1;
		long[] h2;
		long[] p1Powers;
		long[] p2Powers;

		public Solver(String s) {
			this.s = s;
			h1 = precomputeHashes(p1);
			h2 = precomputeHashes(p2);
			p1Powers = precomputePowers(p1, s.length());
			p2Powers = precomputePowers(p2, s.length());
		}

		public boolean ask(int a, int b, int l) {
			long aHash1 = ((h1[a+l]%p1) - (p1Powers[l]*h1[a]%p1)+p1)%p1;
			long aHash2 = ((h2[a+l]%p2) - (p2Powers[l]*h2[a]%p2)+p2)%p2;
			long bHash1= ((h1[b+l]%p1) - (p1Powers[l]*h1[b]%p1)+p1)%p1;
			long bHash2 = ((h2[b+l]%p2) - (p2Powers[l]*h2[b]%p2)+p2)%p2;
			if (aHash1 == bHash1 && aHash2 == bHash2) {
				return true;
			}
			return false;
		}

		public boolean askNaive(int a, int b, int l) {
			return s.substring(a, a + l).equals(s.substring(b, b + l));
		}

		private long[] precomputeHashes(int p) {
			long[] hashes = new long[s.length() + 1];
			hashes[0] = 0;
			for (int i = 1; i < hashes.length; i++) {
				hashes[i] = (((x*hashes[i-1]%p+p)%p + (long)s.charAt(i - 1))%p+p)%p;
			}
			return hashes;
		}

		private long[] precomputePowers(int p, int l) {
			long[] powers = new long[l + 1];
			powers[0] = 1;
			for (int i = 1; i <= l; i++) {
				powers[i] = ((powers[i - 1] * x) % p + p) % p;
			}
			return powers;
		}
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();
			out.println(solver.ask(a, b, l) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new SubstringEquality().run();
	}

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() throws FileNotFoundException {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
