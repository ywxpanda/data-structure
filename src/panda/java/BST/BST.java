package panda.java.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * can be compared
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left;
        private Node right;

        Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //递归实现add
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }
        add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contain(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e == e) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }


    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e + ",");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e + ",");
        inOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + ",");
    }


    private void preOrder1(Node node) {
        Stack<Node> stack = new Stack();
        if (node != null) {
            stack.push(node);
        }
        while (!stack.isEmpty()) {
            Node head = stack.pop();
            System.out.print(head.e + ",");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    /**
     * 二叉树按层遍历
     */
    private void levelOrder(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.print(cur.e + ",");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public Node getMin(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("root is empty");
        }
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public Node getMax(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("root is empty");
        }
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }


    public E removeMax() {
        E ret = getMax(root).e;
        root = removeMax(root);
        return ret;
    }


    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public E removeMin() {
        E ret = getMin(root).e;
        root = removeMin(root);
        return ret;

    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }


    private void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { //equals
            if (node.right == null) {
                return removeMax(node);
            }
            if (node.left == null){
                return removeMin(node);
            }
            //都不为空的情况，用左子树的最小值
            Node res = getMin(node.right);
            res.right = removeMin(node.right);
            res.left = node.left;

            return res;
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] arr = {1, 24, 54, 65, 5467, 57, 6, 45, 5, 66, 776, 87, 9};
        for (int i : arr) {
            bst.add(i);
        }
        bst.inOrder(bst.root);
        System.out.println();
        bst.remove(57);
        bst.inOrder(bst.root);

    }
}
