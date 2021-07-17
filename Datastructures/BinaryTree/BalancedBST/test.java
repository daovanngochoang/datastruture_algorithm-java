package Datastructures.BinaryTree.BalancedBST;

public class test {
    public static void main (String [] args){
        TreeAVL root = new TreeAVL();
        root.insertRecur(100);
        root.insertRecur(80);
        root.insertRecur(56);
        root.insertRecur(477);
        root.insertRecur(34);
        root.insertRecur(563);
        root.insertRecur(342);
        root.insertRecur(45);
        root.insertRecur(25);
        root.insertRecur(1);

        System.out.println(root);


        TreeAVL root1 = new TreeAVL();
        root1.insert(100);
        root1.insert(80);
        root1.insert(56);
        root1.insert(477);
        root1.insert(34);
        root1.insert(563);
        root1.insert(342);
        root1.insert(45);
        root1.insert(25);
        root1.insert(8);
        root1.insert(1);
        root1.insert(400);
        root1.insert(300);
        root1.insert(200);
        root1.insert(250);



        root1.remove(400);
        root1.remove(300);
        root1.remove(200);
        root1.remove(250);
        root1.remove(45);
        root1.remove(25);
        root1.remove(8);
        root1.remove(100);
        root1.remove(80);
        root1.remove(56);
        root1.remove(477);
        root1.remove(34);



        System.out.println(root1);
    }
}
