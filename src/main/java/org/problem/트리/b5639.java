package org.problem.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 골드 5 이진 검색 트리
public class b5639 {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        void insert(int value) {
            if (this.value < value) {
                if (this.right == null) {
                    this.right = new Node(value);
                } else {
                    this.right.insert(value);
                }
            } else {
                if (this.left == null) {
                    this.left = new Node(value);
                } else {
                    this.left.insert(value);
                }
            }
        }

        void postorder() {
            if (this.left != null)
                left.postorder();
            if (this.right != null)
                right.postorder();
            System.out.println(this.value);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Node root = null;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int value = Integer.parseInt(line);
            if (root == null) {
                root = new Node(value);
            } else {
                root.insert(value);
            }
        }

        root.postorder();

    }
}
