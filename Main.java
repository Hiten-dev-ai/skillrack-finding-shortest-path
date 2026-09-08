import java.io.*;
import java.util.*;

public class Main {
    static class Edge { int to, weight; Edge(int to, int weight) { this.to = to; this.weight = weight; } }
    static class FS {
        private final InputStream in = System.in; private final byte[] b = new byte[1 << 16]; int p, n;
        int read() throws IOException { if (p >= n) { n = in.read(b); p = 0; if (n < 0) return -1; } return b[p++]; }
        int nextInt() throws IOException { int c, x = 0; do c = read(); while (c <= 32); while (c > 32) { x = x * 10 + c - 48; c = read(); } return x; }
    }
    public static void main(String[] args) throws Exception {
        FS fs = new FS(); int n = fs.nextInt(); Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) { int from = fs.nextInt(), to = fs.nextInt(), weight = fs.nextInt(); graph.computeIfAbsent(from, x -> new ArrayList<>()).add(new Edge(to, weight)); }
        int start = fs.nextInt(), end = fs.nextInt(); Map<Integer, Long> dist = new HashMap<>();
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(x -> x[1])); pq.add(new long[]{start, 0}); dist.put(start, 0L);
        while (!pq.isEmpty()) {
            long[] cur = pq.poll(); int u = (int) cur[0]; long d = cur[1];
            if (d != dist.getOrDefault(u, Long.MAX_VALUE)) continue;
            if (u == end) { System.out.println(d); return; }
            for (Edge e : graph.getOrDefault(u, Collections.emptyList())) if (d + e.weight < dist.getOrDefault(e.to, Long.MAX_VALUE)) { dist.put(e.to, d + e.weight); pq.add(new long[]{e.to, d + e.weight}); }
        }
        System.out.println("City " + end + " cannot be reached from City " + start);
    }
}
