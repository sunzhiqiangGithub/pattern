package cn.com.sunzhiqiang.data.structure.tree.binary;

import lombok.Data;

/**
 * 功能描述: 二叉搜索树
 *
 * @author sunzhiqiang
 * @create 2019-01-25
 */
@Data
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    /**
     * 创建一个二叉搜索树
     *
     * @param t
     * @param <T>
     * @return
     */
    public void create(T[] t) {

        if (t == null || t.length == 0) {
            root = null;
            return;
        }

        root = new Node(t[0]);

        for (int i = 1; i < t.length; i++) {
            insert(t[i]);
        }
    }

    /**
     * 将结点插入树中
     *
     * @param waitingInsertNode
     * @param root
     */
    public void insert(T t) {

        if (this.root == null) {
            this.root = new Node<>(t);
            return;
        }

        Node<T> root = this.root;
        Node<T> waitingInsertNode = new Node<>(t);

        while (root != null) {
            if (t.compareTo(root.data) > 0) {
                if (root.right == null) {
                    root.right = waitingInsertNode;
                    waitingInsertNode.parent = root;
                    return;
                }
                root = root.right;

            } else if (t.compareTo(root.data) < 0) {
                if (root.left == null) {
                    root.left = waitingInsertNode;
                    waitingInsertNode.parent = root;
                    return;
                }
                root = root.left;
            }
        }
    }

    /**
     * 从二叉树删除某个值
     *
     * @param root
     * @param data
     * @param <T>
     */
    public void delete(T data) {

        Node<T> waitingDeleteNode = this.root;

        while (waitingDeleteNode != null) {
            if (data.compareTo(waitingDeleteNode.data) > 0) {
                waitingDeleteNode = waitingDeleteNode.right;
            } else if (data.compareTo(waitingDeleteNode.data) < 0) {
                waitingDeleteNode = waitingDeleteNode.left;
            } else {
                // 执行删除操作
                doDelete(waitingDeleteNode);
                return;
            }
        }
    }

    private void doDelete(Node<T> waitingDeleteNode) {
        if (waitingDeleteNode.left != null && waitingDeleteNode.right != null) {
            Node<T> tempNode = waitingDeleteNode.right;
            while (tempNode.left != null) {
                tempNode = tempNode.left;
            }
            waitingDeleteNode.data = tempNode.data;
            waitingDeleteNode = tempNode;
        }

        Node<T> child = null;
        if (waitingDeleteNode.left != null) {
            child = waitingDeleteNode.left;
        } else if (waitingDeleteNode.right != null) {
            child = waitingDeleteNode.right;
        } else {
            child = null;
        }

        if (waitingDeleteNode.parent == null) {
            this.root = child;
            if (child != null) {
                child.parent = null;
            }
        } else if (waitingDeleteNode.parent.left == waitingDeleteNode) {
            waitingDeleteNode.parent.left = child;
            if (child != null) {
                child.parent = waitingDeleteNode.parent;
            }
        } else {
            waitingDeleteNode.parent.right = child;
            if (child != null) {
                child.parent = waitingDeleteNode.parent;
            }
        }
    }

    /**
     * 查询是否包含某个值
     *
     * @param root
     * @param data
     * @param <T>
     * @return
     */
    public boolean contain(T data) {

        Node<T> root = this.root;

        while (root != null) {
            if (data.compareTo(root.data) > 0) {
                root = root.right;
            } else if (data.compareTo(root.data) < 0) {
                root = root.left;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 搜索值为data的结点
     *
     * @param data
     * @return
     */
    public Node<T> search(T data) {

        Node<T> root = this.root;

        while (root != null) {
            if (data.compareTo(root.data) > 0) {
                root = root.right;
            } else if (data.compareTo(root.data) < 0) {
                root = root.left;
            } else {
                return root;
            }
        }

        return null;
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void preOrderTraversal(Node root) {

        if (root == null || root.data == null) {
            return;
        }

        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrderTraversal(Node root) {

        if (root == null || root.data == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderTraversal(Node root) {

        if (root == null || root.data == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    private static class Node<T> {

        private T data;

        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }
}
