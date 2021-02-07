package DataStructures.week3;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<Worker> priorityQueue = new PriorityQueue<>(numWorkers, new WorkerComparator());
        for (int i = 0; i < numWorkers; i++) {
            priorityQueue.add(new Worker(i, 0L));
        }

        for (int i = 0; i < jobs.length; i++) {
            Worker worker = priorityQueue.poll();
            assignedWorker[i] = worker.getId();
            startTime[i] = worker.getFinishTime();
            worker.setFinishTime(worker.getFinishTime() + jobs[i]);
            priorityQueue.add(worker);
        }

//        long[] nextFreeTime = new long[numWorkers];
//        for (int i = 0; i < jobs.length; i++) {
//            int duration = jobs[i];
//            int bestWorker = 0;
//            for (int j = 0; j < numWorkers; ++j) {
//                if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                    bestWorker = j;
//            }
//            assignedWorker[i] = bestWorker;
//            startTime[i] = nextFreeTime[bestWorker];
//            nextFreeTime[bestWorker] += duration;
//        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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

    static class Worker {
        private int id;
        private long finishTime;

        public Worker(int id, long finishTime) {
            this.id = id;
            this.finishTime = finishTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(long finishTime) {
            this.finishTime = finishTime;
        }
    }

    static class WorkerComparator implements Comparator<Worker> {

        @Override
        public int compare(Worker w1, Worker w2) {
            if (w1.finishTime < w2.finishTime) {
                return -1;
            } else if (w1.finishTime > w2.finishTime) {
                return 1;
            } else {
                if (w1.id < w2.id) {
                    return -1;
                } else if (w1.id > w2.id) {
                    return 1;
                }
            }
            return 0;
        }
    }
}
