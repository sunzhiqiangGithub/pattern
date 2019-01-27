package cn.com.sunzhiqiang.data.structure.tree.binary;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-25
 */
public class Client {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();
        tree.create(new Integer[]{5, 3, 1, 8, 9, 4});

        tree.preOrderTraversal(tree.getRoot());
        System.out.println();

        tree.inOrderTraversal(tree.getRoot());
        System.out.println();

        tree.postOrderTraversal(tree.getRoot());
        System.out.println();

        System.out.println(tree.contain(1));
        System.out.println(tree.contain(89));

        tree.preOrderTraversal(tree.getRoot());
        System.out.println();
        tree.delete(3);
        tree.preOrderTraversal(tree.getRoot());
        System.out.println();

    }
}
