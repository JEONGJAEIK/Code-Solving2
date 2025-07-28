package org.problem.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());

        List<List<City>> graph = new ArrayList<>();

        for (int i = 0; i < cityCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < busCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new City(end, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[cityCount + 1];
        int[] dist = new int[cityCount + 1];

        for (int i = 0; i < cityCount + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        for (int i = 0; i < cityCount; i++) {
            int cityValue = Integer.MAX_VALUE;
            int cityIndex = 0;
            for (int j = 1; j < cityCount + 1; j++) {
                if (!visited[j] && dist[j] < cityValue) {
                    cityValue = dist[j];
                    cityIndex = j;
                }
            }
            visited[cityIndex] = true;

            for (int j = 0; j < graph.get(cityIndex).size(); j++) {
                City adjCity = graph.get(cityIndex).get(j);
                if (dist[adjCity.index] > dist[cityIndex] + adjCity.cost) {
                    dist[adjCity.index] = dist[cityIndex] + adjCity.cost;
                }
            }
        }

        System.out.println(dist[target]);

    }
}

class City {
    int index;
    int cost;

    City(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
