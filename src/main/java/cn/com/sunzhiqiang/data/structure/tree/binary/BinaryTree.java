package cn.com.sunzhiqiang.data.structure.tree.binary;

/**
 * 功能描述: 二叉树
 *
 * @author sunzhiqiang
 * @create 2019-01-25
 */
public class BinaryTree {

    private Node root;

    /**
     * 创建一个二叉树
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> Node<T> createTree(T[] t) {

        Node root = new Node(t[0]);

        for (int i = 1; i < t.length; i++) {
            Node tempNode = new Node(t[i]);
            insertIntoTree(tempNode, root);
        }

        return root;
    }

    private void insertIntoTree(Node tempNode, Node root) {
        if (((Comparable) tempNode.data).compareTo(root.data) > 0) {
            if (root.right == null) {
                root.right = tempNode;
                return;
            }
            root = root.right;
        } else {
            if (root.left == null) {
                root.left = tempNode;
                return;
            }
            root = root.left;
        }

        insertIntoTree(tempNode, root);
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void preorderTraversal(Node root) {

        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inorderTraversal(Node root) {

        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postorderTraversal(Node root) {

        if (root == null) {
            return;
        }

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    static class Node<T> {

        private T data;

        private Node left;
        private Node right;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }
}
