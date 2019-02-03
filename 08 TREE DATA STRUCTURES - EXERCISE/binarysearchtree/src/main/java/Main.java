public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(1);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);
        bst.insert(8);
        bst.insert(9);
        bst.insert(10);
        bst.insert(37);
        bst.insert(39);
        bst.insert(45);

        int item = bst.select(4);

        System.out.println(item);

    }
}
