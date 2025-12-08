package org.problem.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 골드 4 거짓말
public class b1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int humanTotal = Integer.parseInt(st.nextToken());
        int partyTotal = Integer.parseInt(st.nextToken());

        Map<Integer, Boolean> human = new HashMap<>();
        for (int i = 1; i <= humanTotal; i++) {
            human.put(i, false);
        }

        st = new StringTokenizer(br.readLine());
        if (st.countTokens() > 1) {
            int trustTotal = Integer.parseInt(st.nextToken());
            for (int i = 0; i < trustTotal; i++) {
                human.put(Integer.valueOf(st.nextToken()), true);
            }
        }

        // 모든 파티 저장
        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < partyTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int partyVisit = Integer.parseInt(st.nextToken());

            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partyVisit; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }

        // 진실 전파가 멈출 때까지 반복
        boolean changed;
        do {
            changed = false;
            for (List<Integer> party : parties) {
                boolean hasTruth = false;
                for (int person : party) {
                    if (human.get(person)) {
                        hasTruth = true;
                        break;
                    }
                }
                if (hasTruth) {
                    for (int person : party) {
                        if (!human.get(person)) {
                            human.put(person, true);
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);

        // 이제 거짓말 가능한 파티 수 세기
        int count = 0;
        for (List<Integer> party : parties) {
            boolean hasTruth = false;
            for (int person : party) {
                if (human.get(person)) {
                    hasTruth = true;
                    break;
                }
            }
            if (!hasTruth) {
                count++;
            }
        }

        System.out.println(count);
    }
}