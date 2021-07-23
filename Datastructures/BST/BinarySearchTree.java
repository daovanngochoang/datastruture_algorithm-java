package Datastructures.BST;



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
        /*
            delete a node :
                - get the item in the smallest node from it's right branch and set to the be the item in the node we
                going to delete, delete it's right smallest node.
         */
        Node cur = root;
        Node alternativeItem ;
        while (cur.element != item){
            if (item <= cur.element){
                cur = cur.getLeftChild();
            }else {
                cur = cur.getRightChild();
            }
        }

        if (cur.isLeaf()){ // if the cur is leaf
            if (cur.getParentNode().element >= cur.element){
                cur.getParentNode().setLeftChild(null);
            }else {
                cur.getParentNode().setRightChild(null);
            }

        }else {

            alternativeItem = this.finMin(cur.getRightChild()); // get the smallest item of it's right branch
            if (alternativeItem == null){ // if there is no smallest on the right --> set the parent of the deleted node to it's left.

                if (cur == cur.parentNode.leftChild){ // if the delete item is in the left of it's parent
                    alternativeItem = cur.getLeftChild();
                    cur.getParentNode().setLeftChild(alternativeItem); // set parent to point at its left branch
                    alternativeItem.setParentNode(cur.getParentNode()); // set parent to its left branch
                    cur.setParentNode(null); // set delete parent to null
                }else { // right
                    alternativeItem = cur.getLeftChild();
                    cur.getParentNode().setRightChild(alternativeItem); // set parent to point at its right branch
                    alternativeItem.setParentNode(cur.getParentNode()); // set parent to its right branch
                    cur.setParentNode(null); // set delete parent to null
                }

            }else{
                cur.setElement(alternativeItem.element);  // set the alternative element up to the element we want to delete

                if (alternativeItem.isLeaf()){  // if the alternative node is leaf

                    if (alternativeItem.getParentNode().equals(cur)){ // if it is the adjacent node in the right of the node we want to delete item
                        alternativeItem.getParentNode().setRightChild(null); // set the right of the item we want to delete to null.
                    }else {
                        // if the parent is not the node we want to delete
                        // ==> set the left of the alternative node's parent to null. because smallest always in the left.
                        alternativeItem.getParentNode().setLeftChild(null);
                    }
                }else {
                    // if it not a leaf that mean the right of that node has a child, because smallest always in the end of the left
                    // ==> set it's parent to point at it's right as the left of parent.
                    if (alternativeItem.getParentNode().equals(cur)){ // if it is the adjacent node in the right of the node we want to delete item
                        alternativeItem.getParentNode().setRightChild(alternativeItem.getRightChild());// set the right of the item we want to delete to the right of the alternative node
                    }else {
                        alternativeItem.getParentNode().setLeftChild(alternativeItem.getRightChild()); // else just set the the left
                    }

                }
            }
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
                        break;
                    }

                }else {
                    if(!(current.getRightChild()==null)){
                        current = current.rightChild;
                    }else {
                        current.setRightChild(new Node(item, null, null, current));
                        break;
                    }
                }

            }
        }
    }

    public Node findMax(Node n){
        if (n != null) {

            if (n.getRightChild() == null) {
                return n;
            }
            return findMax(n.getRightChild());
        }else {
            return null;
        }
    }

    public Node finMin(Node n){
        if (n != null){
            if (n.getLeftChild()==null){
                return n;
            }
            return finMin(n.getLeftChild());
        }else {
            return null;
        }
    }

    public static void preorder(Node n){

        if (n == null){
            System.out.println(n);
        }else {
            if (!(n.getLeftChild()==null)){
                preorder(n.getLeftChild());
            }
            int result = n.element;
            array.add(result);

            if (!(n.getRightChild()==null)){
                preorder(n.getRightChild());
            }
        }


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

    public void clear (Node root){
        if (!(root.getLeftChild()==null)){
            clear(root.getLeftChild());
        }
        root.setLeftChild(null);

        if (!(root.getRightChild()==null)){
            clear(root.getRightChild());
        }
        root.setRightChild(null);

        this.root = null;

    }

    public static void main(String[] args) {
        BinarySearchTree ADV = new BinarySearchTree();
        int[] a = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};

        ADV.buildTree(a);
        System.out.println(ADV);

        ADV.remove(1);
        ADV.remove(3);
        ADV.remove(-2);
        ADV.remove(5);
        ADV.remove(6);
        ADV.remove(100);
        ADV.remove(56);
        ADV.remove(477);
        ADV.remove(563);
        ADV.remove(3);
        ADV.remove(4);
        ADV.remove(6);
        ADV.remove(7);
        ADV.remove(7);
        ADV.remove(2);
        ADV.remove(2);

        System.out.println(ADV);



    }
}











