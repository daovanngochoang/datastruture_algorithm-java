package Datastructures.SplayTree.myTechnique;

public class TestSplay {

    public static void main (String [] args){
    SplayTree<Integer> root = new SplayTree<>();


    root.insert(80, 80);
    root.insert(25, 25);
    root.insert(477, 477);
    root.insert(100, 100);
    root.insert(45, 45);
    root.insert(342, 342);
    root.insert(56, 56);
    root.insert(34, 34);
    root.insert(563, 563);
    root.insert(8, 8);
    root.insert(10, 10);
    root.insert(1, 1);
    int a = root.Search(45);
    root.remove(1);
    root.remove(10);
    root.remove(100);
    root.remove(342);
    root.remove(563);
    root.remove(8);




    System.out.println(root);
    }
}
