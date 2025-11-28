package org.problem.트리dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b15681 {
    static int rootNode;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] nodeSize;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력

        int nodeCount = Integer.parseInt(st.nextToken()); // 정점의 수 최대 십만
        int rootNumber = Integer.parseInt(st.nextToken()); // 루트 번호 최대 십만
        int queryCount = Integer.parseInt(st.nextToken()); // 쿼리의 수 최대 십만

        for (int i = 0; i < nodeCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[nodeCount + 1];
        nodeSize = new int[nodeCount + 1];
        rootNode = rootNumber;

        for (int i = 0; i < nodeCount - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(rootNode);

        for (int i = 0; i < queryCount; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            System.out.println(nodeSize[u]);
        }
    }

    static void dfs(int n) {
        visited[n] = true;
        nodeSize[n] = 1;
        List<Integer> aa = graph.get(n);
        for (Integer i : aa) {
            if (!visited[i]) {
                dfs(i);
                nodeSize[n] += nodeSize[i];
            }
        }
    }
}
