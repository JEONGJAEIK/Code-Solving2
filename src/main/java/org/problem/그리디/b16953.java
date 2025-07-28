package org.problem.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마지막 숫자가 3,5,7,9 인경우 만드는게 불가능
// 0,1,2,4,6,8 만 가능한데 마지막 숫자가 1이면 1을 붙이면 됨
// 다만 0,1,2,4,6,8도 확실히 가능하다고 보장못함
// after에서 before 가면서 가보자 마지막에 1이 있으면 1을 빼면된다 2를곱해서 뒷자리가 1이 될 수는 없으므로
// 규칙 1 : 마지막인덱스가 1이면 1을 제거한다
// 규칙 2 : 1이 아니면 2로 나눈다
public class b16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int before = Integer.parseInt(st.nextToken());
        int after = Integer.parseInt(st.nextToken());
        int count = 1;

        while (before < after) {
            if (after % 10 == 1) {
                after = (after -1) / 10;
            } else if (after % 2 == 0) {
                after /= 2;
            } else {
                break;
            }
            count++;
        }
        System.out.println(before == after ? count : -1);
    }
}
