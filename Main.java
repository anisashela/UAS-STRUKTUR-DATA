import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    static Node root;
    static Node temp = root;

    static void inorder(Node temp) {
        if (temp == null) {
            return;
        }

        inorder(temp.left);
        System.out.print(temp.key + " ");
        inorder(temp.right);
    }

    static void insert(Node temp, int key) {
        if (temp == null) {
            root = new Node(key);
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left == null) {
                temp.left = new Node(key);
                break;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new Node(key);
                break;
            } else {
                q.add(temp.right);
            }
        }
    }

    static void deleteDeepest(Node root,
                              Node delNode) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        Node temp = null;

        // Do level order traversal until last node
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp == delNode) {
                temp = null;
                return;

            }
            if (temp.right != null) {
                if (temp.right == delNode) {
                    temp.right = null;
                    return;
                } else {
                    q.add(temp.right);
                }
            }

            if (temp.left != null) {
                if (temp.left == delNode) {
                    temp.left = null;
                    return;
                } else {
                    q.add(temp.left);
                }
            }
        }
    }

    static void delete(Node root, int key) {
        if (root == null) {
            return;
        }

        if (root.left == null &&
                root.right == null) {
            if (root.key == key) {
                root = null;
                return;
            } else {
                return;
            }
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node temp = null, keyNode = null;
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.key == key) {
                keyNode = temp;
            }

            if (temp.left != null) {
                q.add(temp.left);
            }

            if (temp.right != null) {
                q.add(temp.right);
            }
        }

        if (keyNode != null) {
            int x = temp.key;
            deleteDeepest(root, temp);
            keyNode.key = x;
        }
    }

    public static void menu() {
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.print("Pilih Menu : ");
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        while (true) {
            menu();
            int pil = input.nextInt();
            switch (pil) {
                case 1:
                    System.out.print("root : ");
                    root = new Node(input.nextInt());
                    System.out.print("root.left : ");
                    root.left = new Node(input.nextInt());
                    System.out.print("root.left.left : ");
                    root.left.left = new Node(input.nextInt());
                    System.out.print("root.right: ");
                    root.right = new Node(input.nextInt());
                    System.out.print("root.right.left: ");
                    root.right.left = new Node(input.nextInt());
                    System.out.print("root.right.right: ");
                    root.right.right = new Node(input.nextInt());
                    System.out.print("Inorder Traversal : ");
                    inorder(root);
                    System.out.println(" ");
                    break;
                case 2:
                    inorder(root);
                    System.out.print("\nElement yang akan dihapus : ");
                    int hapus = input.nextInt();
                    delete(root, hapus);
                    System.out.print("Inorder Traversal : ");
                    inorder(root);
                    System.out.println(" ");
                    break;
            }
        }
    }
}