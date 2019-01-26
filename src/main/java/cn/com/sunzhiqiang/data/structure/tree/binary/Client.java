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

        tree.preOrderTraversal(root);
        System.out.println();

        tree.inOrderTraversal(root);
        System.out.println();

        tree.postOrderTraversal(root);
        System.out.println();

        System.out.println(tree.contain(root, 1));
        System.out.println(tree.contain(root, 89));

        tree.preOrderTraversal(root);
        System.out.println();
        tree.deleteNodeFromTree(root, 5);
        tree.preOrderTraversal(root);
        System.out.println();

    }
}
