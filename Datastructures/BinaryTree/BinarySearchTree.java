package Datastructures.BinaryTree;

import Datastructures.List.ArrayListImplement;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

public class BinarySearchTree {

    Node root = null;
    static ArrayList<Integer> array;

    public void buildTree(int [] array){
        for (int i : array){
            this.insert(i);
        }
    }

    public void remove(int item){
        Node cur = root;
        Node alternativeItem = root;
        while (cur.element != item){
            if (item <= cur.element){
                cur = cur.getLeftChild();
            }else {
                cur = cur.getRightChild();
            }
        }

        alternativeItem = this.finMin(cur.getRightChild());
        cur.setElement(alternativeItem.element);

        if (alternativeItem.isLeaf()){
            if (alternativeItem.getParentNode().equals(cur)){
                alternativeItem.getParentNode().setRightChild(null);
            }else {
                alternativeItem.getParentNode().setLeftChild(null);
            }
        }else {

            alternativeItem.getParentNode().setLeftChild(alternativeItem.getRightChild());
        }

    }

    public void insert(int item){

        if (this.root == null){
            this.root = new Node(item, null, null, null);

        }else {
            Node current  = root;
            while (true){
                if (item <= current.getElement()){
                    if(!(current.getLeftChild() == null)){
                        current = current.leftChild;
                    }else {
                        current.setLeftChild(new Node(item, null, null, current));
                        current.setLeaf(false);
                        break;
                    }

                }else {
                    if(!(current.getRightChild()==null)){
                        current = current.rightChild;
                    }else {
                        current.setRightChild(new Node(item, null, null, current));
                        current.setLeaf(true);
                        break;
                    }
                }

            }
        }
    }

    public int findMax(Node n){

        if (n.getRightChild()==null){
            return n.element;
        }
        return findMax(n.getRightChild());
    }

    public Node finMin(Node n){
        if (n.getLeftChild()==null){
            return n;
        }
        return finMin(n.getLeftChild());
    }

    String StringRe = "";
    public static int preorder(Node n){

        if (!(n.getLeftChild()==null)){
            preorder(n.getLeftChild());
        }
        int result = n.element;
        array.add(result);

        if (!(n.getRightChild()==null)){
            preorder(n.getRightChild());
        }

        return result;
    }

    public ArrayList<Integer> getArray(){
        array = new ArrayList<>();
        preorder(this.root);

        return array;
    }

    public String toString(){
        array = new ArrayList<>();
        preorder(this.root);
        return array.toString();
    }

    public static void main(String[] args) {
        BinarySearchTree ADV = new BinarySearchTree();
        int[] a = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};

        ADV.buildTree(a);
        System.out.println(ADV);

        ADV.remove(1);
        System.out.println(ADV);

        int max = ADV.findMax(ADV.root);
        System.out.println(max);


    }
}











