package cn.com.sunzhiqiang.data.structure.tree.binary;

/**
 * 功能描述: 二叉树
 *
 * @author sunzhiqiang
 * @create 2019-01-25
 */
public class BinaryTree {

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
            insertNodeIntoTree(tempNode, root);
        }

        return root;
    }

    /**
     * 将结点插入树中
     *
     * @param waitingInsertNode
     * @param root
     */
    public void insertNodeIntoTree(Node waitingInsertNode, Node root) {

        if (waitingInsertNode.data.compareTo(root.data) > 0) {
            if (root.right == null) {
                root.right = waitingInsertNode;
                waitingInsertNode.parent = root;
                return;
            }
            root = root.right;

        } else {
            if (root.left == null) {
                root.left = waitingInsertNode;
                waitingInsertNode.parent = root;
                return;
            }
            root = root.left;
        }

        insertNodeIntoTree(waitingInsertNode, root);
    }

    /**
     * 从二叉树删除某个值
     *
     * @param root
     * @param data
     * @param <T>
     */
    public <T extends Comparable<T>> void deleteNodeFromTree(Node<T> root, T data) {

        int compareResult = data.compareTo(root.data);

        if (compareResult == 0) {
            if (root.parent != null) {
                if (root.parent.right != null && root.parent.right.data.compareTo(data) == 0) {
                    doRightDelete(root);
                    return;
                } else {
                    doLeftDelete(root);
                    return;
                }

            } else {
                doRootDelete(root);
                return;
            }
        }

        if (compareResult > 0) {
            root = root.right;
        } else {
            root = root.left;
        }

        deleteNodeFromTree(root, data);
    }

    private <T extends Comparable<T>> void doRootDelete(Node<T> root) {
        // 要删除的结点是根结点
        if (root.left == null && root.right == null) {
            root.data = null;
            return;
        }
        if (root.left == null) {
            root.left = root.right.left;
            root.right = root.right.right;
            root.data = root.right.data;
            return;
        }
        if (root.right == null) {
            root.left = root.left.left;
            root.right = root.left.right;
            root.data = root.left.data;
            return;
        }
        Node tempNode = root.right;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        tempNode.left = root.left;
        root.left = root.right.left;
        root.data = root.right.data;
        root.right = root.right.right;
        return;
    }

    private <T extends Comparable<T>> void doLeftDelete(Node<T> waitDeleteNode) {
        // 要删除的结点是叶子结点
        if (waitDeleteNode.right == null && waitDeleteNode.left == null) {
            waitDeleteNode.parent.left = null;
        }
        // 删除的结点没有右子树
        if (waitDeleteNode.right == null) {
            waitDeleteNode.parent.left = waitDeleteNode.left;
            waitDeleteNode.parent = null;
            return;
        }
        // 删除的结点没有左子树
        if (waitDeleteNode.left == null) {
            waitDeleteNode.parent.left = waitDeleteNode.right;
            waitDeleteNode.parent = null;
            return;
        }
        // 删除的结点既有左子树又有右子树
        waitDeleteNode.parent.left = waitDeleteNode.right;
        Node tempNode = waitDeleteNode.right;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        tempNode.left = waitDeleteNode.left;
        waitDeleteNode.parent = null;
        return;
    }

    private <T extends Comparable<T>> void doRightDelete(Node<T> waitDeleteNode) {
        // 要删除的结点是叶子结点
        if (waitDeleteNode.right == null && waitDeleteNode.left == null) {
            waitDeleteNode.parent.right = null;
            waitDeleteNode.parent = null;
            return;
        }
        // 删除的结点没有右子树
        if (waitDeleteNode.right == null) {
            waitDeleteNode.parent.right = waitDeleteNode.left;
            waitDeleteNode.parent = null;
            return;
        }
        // 删除的结点没有左子树
        if (waitDeleteNode.left == null) {
            waitDeleteNode.parent.right = waitDeleteNode.right;
            waitDeleteNode.parent = null;
            return;
        }
        // 删除的结点既有左子树又有右子树
        waitDeleteNode.parent.right = waitDeleteNode.right;
        Node tempNode = waitDeleteNode.right;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        tempNode.left = waitDeleteNode.left;
        waitDeleteNode.parent = null;
        return;
    }

    /**
     * 查询是否包含某个值
     *
     * @param root
     * @param data
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> boolean contain(Node<T> root, T data) {

        int compareResult = data.compareTo(root.data);

        if (compareResult == 0) {
            return true;
        }

        if (compareResult > 0) {
            root = root.right;
        } else {
            root = root.left;
        }
        return root == null ? false : contain(root, data);
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

    static class Node<T extends Comparable<T>> {

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
