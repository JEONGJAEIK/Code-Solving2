package org.problem.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1991 {

    static class Node {
        String value;
        Node left, right;

        Node(String value) {
            this.value = value;
        }

        void insert(String mid, String left, String right) {
            if (this.value.equals(mid)) {
                if (!left.equals(".")) this.left = new Node(left);
                if (!right.equals(".")) this.right = new Node(right);
            } else {
                if (this.left != null) this.left.insert(mid, left, right);
                if (this.right != null) this.right.insert(mid, left, right);
            }
        }
        void preorder() {
            System.out.print(this.value);
            if (this.left != null) left.preorder();
            if (this.right != null) right.preorder();
        }

        void inorder() {
            if (this.left != null) left.inorder();
            System.out.print(this.value);
            if (this.right != null) right.inorder();
        }

        void postorder() {
            if (this.left != null) left.postorder();
            if (this.right != null) right.postorder();
            System.out.print(this.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Node root = null;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String mid = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();


            if (root == null) {
                root = new Node(mid);
                if (!left.equals(".")) {
                    root.left = new Node(left);
                }
                if (!right.equals(".")) {
                    root.right = new Node(right);
                }
            } else {
                root.insert(mid, left, right);
            }
        }

        root.preorder();
        System.out.println();
        root.inorder();
        System.out.println();
        root.postorder();
    }
}
