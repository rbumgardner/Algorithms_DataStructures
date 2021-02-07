package DataStructures.week1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public int arrival_time;
    public int process_time;

    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }
}

class Response {
    public boolean dropped;
    public int start_time;

    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }
}

class Buffer {
    private final int size;
    private final ArrayList<Integer> finish_time;

    public Buffer(int size) {
        this.size = size;
        this.finish_time = new ArrayList<>();
    }

    /**
     * Key Idea:
     * When each process i comes, decide: a) if it will be dropped or not, or b) if enqueued, what is its start/finish time?
     * The ArrayList is simply for recordkeeping when calculating the start/finish times.
     * Process:
     * 1. Pop all packets in buffer that have already finished;
     * 2. If buffer is full, drop i and output -1;
     * 3. Determine the start_time by queue:
     *     if queue is empty, process i as it arrives;
     *     else, enqueue i after last finish_time;
     *
     * @param request
     * @return
     */
    public Response process(Request request) {
        // Get rid of stuff that has "processed".
        while (!finish_time.isEmpty() && finish_time.get(0) <= request.arrival_time) {
            finish_time.remove(0);
        }

        // If still full, just drop it;
        if (finish_time.size() == size) {
            return new Response(true, -1);
        }

        // Compute start_time and enqueue finish_time.
        int current_start_time = finish_time.isEmpty() ? request.arrival_time : finish_time.get(finish_time.size() - 1);
        finish_time.add(current_start_time + request.process_time);
        return new Response(false, current_start_time);
    }
}

class ProcessPackages {
    private static ArrayList<Request> readQueries(Scanner scanner) {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> processRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<>();
        for (Request request : requests) {
            responses.add(buffer.process(request));
        }
        return responses;
    }

    private static void printResponses(ArrayList<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = readQueries(scanner);
        ArrayList<Response> responses = processRequests(requests, buffer);
        printResponses(responses);
    }
}
