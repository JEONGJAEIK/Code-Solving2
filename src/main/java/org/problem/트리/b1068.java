package org.problem.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 골드 5 트리
public class b1068 {
    static List<Integer>[] graph = new ArrayList[50];
    static int ancestor;
    static int answer;
    static int removeNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeTotal = Integer.parseInt(br.readLine());

        for (int i = 0; i < nodeTotal; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nodeTotal; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent != -1) {
                graph[parent].add(i);
            } else {
                ancestor = i;
            }
        }
        removeNode = Integer.parseInt(br.readLine());

        if (removeNode == ancestor) {
            System.out.println(0);
        } else {
            dfs(ancestor);
            System.out.println(answer);
        }
    }

    static void dfs(int start) {

        if (start == removeNode) return;

        int childCount = 0;

        for (Integer child : graph[start]) {
            if (child != removeNode) {
                childCount++;
                dfs(child);
            }
        }

        if (childCount == 0) {
            answer++;
        }
    }
}

