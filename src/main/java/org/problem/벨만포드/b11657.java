package org.problem.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 골드 4 벨만포드
public class b11657 {
    static List<Edge> edges = new ArrayList<>();
    static long[] dist;
    static int cityTotal;
    static int busTotal;

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityTotal = Integer.parseInt(st.nextToken());
        busTotal = Integer.parseInt(st.nextToken());
        System.out.println("[INFO] 도시 수: " + cityTotal + ", 버스 수: " + busTotal);

        dist = new long[cityTotal + 1];
        for (int i = 1; i <= cityTotal; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < busTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(startCity, endCity, cost));
        }
        System.out.println("[INFO] 간선 입력 완료, 총 " + edges.size() + "개");

        dist[1] = 0;
        System.out.println("[INFO] 시작 도시(1번) 거리 0으로 초기화");

        // 벨만포드 (최단거리 구하기)
        for (int i = 1; i < cityTotal; i++) {
            System.out.println("\n" + i + "번째 순회 시작");
            for (Edge edge : edges) {
                long fromDist = dist[edge.from];
                long toDist = dist[edge.to];
                String fromStr = (fromDist == Long.MAX_VALUE) ? "INF" : String.valueOf(fromDist);
                String toStr = (toDist == Long.MAX_VALUE) ? "INF" : String.valueOf(toDist);
                System.out.println("  [비교] " + edge.from + " → " + edge.to +
                        " (cost: " + edge.cost + "), dist[" + edge.to + "]=" + toStr +
                        ", fromDist=" + fromStr);

                if (fromDist != Long.MAX_VALUE && toDist > fromDist + edge.cost) {
                    dist[edge.to] = fromDist + edge.cost;
                    System.out.println("  [변경] " + edge.from + " → " + edge.to +
                            " (cost: " + edge.cost +
                            "), dist[" + edge.to + "] " + toDist + " → " + dist[edge.to]);
                } else {
                    String fromStrb = (fromDist == Long.MAX_VALUE) ? "INF" : String.valueOf(fromDist);
                    String toStrb = (toDist == Long.MAX_VALUE) ? "INF" : String.valueOf(toDist);
                    System.out.println("  [무시] " + edge.from + " → " + edge.to +
                            " (cost: " + edge.cost + "), dist[" + edge.to + "] remains " + toStrb +
                            " (from dist: " + fromStrb + ")");
                }
            }
        }

        // 음수 사이클 확인
        System.out.println("\n 음수 사이클 검사 시작");
        boolean negativeCycle = false;
        for (Edge edge : edges) {

            String fromStr = (dist[edge.from] == Long.MAX_VALUE) ? "INF" : String.valueOf(dist[edge.from]);
            String toStr = (dist[edge.to] == Long.MAX_VALUE) ? "INF" : String.valueOf(dist[edge.to]);
            System.out.println("  [비교] " + edge.from + " → " + edge.to +
                    " (cost: " + edge.cost + "), dist[" + edge.to + "]=" + toStr +
                    ", fromDist=" + fromStr);
            if (dist[edge.from] != Long.MAX_VALUE &&
                    dist[edge.to] > dist[edge.from] + edge.cost) {
                negativeCycle = true;
                System.out.println("[발견] 음수 사이클 발견: " + edge.from + " → " + edge.to);
                break;
            }
        }

        if (negativeCycle) {
            System.out.println(-1);
            return;
        } else {
            System.out.println("음수 사이클 없음");
        }

        // 결과 출력
        System.out.println("\n최종 거리 결과:");
        for (int i = 2; i <= cityTotal; i++) {
            if (dist[i] == Long.MAX_VALUE) {
                System.out.println(i + "번 도시: -1 (도달 불가)");
            } else {
                System.out.println(i + "번 도시: " + dist[i]);
            }
        }
    }
}
