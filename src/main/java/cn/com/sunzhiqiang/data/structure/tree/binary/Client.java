package cn.com.sunzhiqiang.data.structure.tree.binary;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-25
 */
public class Client {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        BinaryTree.Node root = tree.createTree(new Integer[]{5, 3, 1, 8, 9, 4});

        tree.preorderTraversal(root);
        System.out.println();

        tree.inorderTraversal(root);
        System.out.println();

        tree.postorderTraversal(root);
    }
}
