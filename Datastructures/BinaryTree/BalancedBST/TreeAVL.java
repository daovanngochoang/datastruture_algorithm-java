package Datastructures.BinaryTree.BalancedBST;


import java.util.ArrayList;

public class TreeAVL {
    /*
    https://www.javatpoint.com/avl-tree
    https://www.tutorialspoint.com/data_structures_algorithms/avl_tree_algorithm.htm
    https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
     */


    AVLNode root;
    static ArrayList<Integer> array;



    // insert with recursion
    public void insertRecur (int elem){
        this.root = insertRecur(elem, this.root, null);
    }

    public static AVLNode insertRecur (int elem, AVLNode node, AVLNode parent){
        // insert the item recursively
        if (node == null){
            // when it is set to be a node of a leaf node ==> return the new node
            return new AVLNode(elem, parent);
        }else if(elem < node.element) { // if elem < node ==> run to the left
            node.setLeftChild(insertRecur(elem, node.getLeftChild(), node)); // set left
        }else if (elem > node.element){ //to the right
            node.setRightChild(insertRecur(elem, node.getRightChild(), node)); // set right
        }

        // when a function that meet the leaf node and be set as it's chill then it continue run the call function to re-height each node.
        node.setHeight(1 + Math.max(height(node.getRightChild()), height(node.getLeftChild())));

        return reBalance(node);

    }




    // insert nonRecursion
    public void insert (int elem){
        // insert as the insert function of binary tree
        if (this.root == null){
            this.root = new AVLNode(elem , null);

        }else {
            AVLNode current  = root;
            while (true){
                if (elem == current.element) break; // not allowed duplicate
                if (elem  < current.getElement()){
                    if(!(current.getLeftChild() == null)){
                        current = current.leftChild;
                    }else {
                        current.setLeftChild(new AVLNode(elem , current));
                        break;
                    }

                }else {
                    if(!(current.getRightChild()==null)){
                        current = current.rightChild;
                    }else {
                        current.setRightChild(new AVLNode(elem , current));
                        break;
                    }
                }
            }
            // and then run up from that not to the top to find the imbalanced node
            makeBalance(current);

        }
    }

    public AVLNode findMax(AVLNode n){
        if (n != null) {

            if (n.getRightChild() == null) {
                return n;
            }
            return findMax(n.getRightChild());
        }else {
            return null;
        }
    }

    public AVLNode finMin(AVLNode n){
        if (n != null){
            if (n.getLeftChild()==null){
                return n;
            }
            return finMin(n.getLeftChild());
        }else {
            return null;
        }
    }

    public void remove(int item){
        /*
            delete a node :
                - get the item in the smallest node from it's right branch and set to the be the item in the node we
                going to delete, delete it's right smallest node.
         */
        AVLNode cur = root;
        AVLNode alternativeItem, alterParent ;
        while (cur.element != item){
            if (item <= cur.element){
                cur = cur.getLeftChild();
            }else {
                cur = cur.getRightChild();
            }
        }

        alterParent = cur.getParent();// get the parent of the current node to check imbalanced after remove
        if (cur.isLeaf()){ // if the cur is leaf
            setChild(cur.getParent(), cur, null); // set the parent point to null at the position we want to remove

        }else {

            alternativeItem = this.finMin(cur.getRightChild()); // get the smallest item of it's right branch
            if (alternativeItem == null){ // if there is no smallest on the right --> set the parent of the deleted node to it's left.

                if (cur == cur.getParent().leftChild){ // if the delete item is in the left of it's parent

                    alternativeItem = cur.getLeftChild();
                    cur.getParent().setLeftChild(alternativeItem); // set parent to point at its left branch
                    alternativeItem.setParent(cur.getParent()); // set parent to its left branch
                    cur.setParent(null); // set delete parent to null
                    alterParent = alternativeItem.getParent(); // return the alter parent to check im balance.

                }else { // right
                    alternativeItem = cur.getLeftChild();
                    cur.getParent().setRightChild(alternativeItem); // set parent to point at its right branch
                    alternativeItem.setParent(cur.getParent()); // set parent to its right branch
                    cur.setParent(null); // set delete parent to null
                    alterParent = alternativeItem.getParent(); // return the alter parent to check im balance.
                }

            }else{
                alterParent = alternativeItem.getParent();
                cur.setElement(alternativeItem.element);  // set the alternative element up to the element we want to delete

                if (alternativeItem.isLeaf()){  // if the alternative node is leaf

                    if (alternativeItem.getParent().equals(cur)){ // if it is the adjacent node in the right of the node we want to delete item
                        alternativeItem.getParent().setRightChild(null); // set the right of the item we want to delete to null.
                    }else {
                        // if the parent is not the node we want to delete
                        // ==> set the left of the alternative node's parent to null. because smallest always in the left.
                        alternativeItem.getParent().setLeftChild(null);
                    }
                }else {
                    // if it not a leaf that mean the right of that node has a child, because smallest always in the end of the left
                    // ==> set it's parent to point at it's right as the left of parent.
                    if (alternativeItem.getParent().equals(cur)){ // if it is the adjacent node in the right of the node we want to delete item
                        alternativeItem.getParent().setRightChild(alternativeItem.getRightChild());// set the right of the item we want to delete to the right of the alternative node
                    }else {
                        alternativeItem.getParent().setLeftChild(alternativeItem.getRightChild()); // else just set the the left
                    }

                }
            }
        }

        // re-balanced the node above the deleted node
        makeBalance(alterParent);

    }

    public void makeBalance (AVLNode node){
        while (node != null){
            reHeight(node); // set the current height after insert
            reBalance(node); // reBalance if there is imbalance branch
            this.root = node; // set the root to be the current node whenever it up to the top, because some time the root gonna be replaced.
            node =node.getParent(); // change the current to run up to the top
        }
    }

    public void setChild(AVLNode parent, AVLNode compareNode, AVLNode newChild){
        // compare the parent with its the other node that to be set as its child
        if (parent.element >= compareNode.element){ // if < parent ==> left
            parent.setLeftChild(newChild);
        }else { // the right
            parent.setRightChild(newChild);
        }
    }

    public static AVLNode reBalance (AVLNode node){
        // this function to check and re-balance branch.

        // get the left and the right of that node
        AVLNode left = node.getLeftChild();
        AVLNode right = node.getRightChild();

        // check balancedFactor if the left branch - the right branch >= 2 or <= -2 that mean that branch is imbalanced.
        int balancedFactor = height(left) - height(right);

        if (balancedFactor >= 2){ // this case the branch is imbalance with the left is longer the right

            // this is the case of splaying left then right (explained at the LeftRightRotate function)
            if (height(left.getLeftChild()) < height(left.getRightChild())){
                return LeftRightRotate(node);
            }else { // if not that case, do the normal rotation right.
                return rightRotate(node);
            }
        }else if (balancedFactor <= -2){// the right is longer the left

            // // this is the case of splaying right then left (explained at the RightLeftRotate function)
            if (height(right.getLeftChild()) > height(right.getRightChild())){
                return RightLeftRotate(node);
            }else { // do as normal.
                return leftRotate(node);
            }
        }
        return node;
    }


    static void reHeight (AVLNode node){ // set new height to the node after insert or delete
        node.setHeight(1 + Math.max(height(node.getRightChild()), height(node.getLeftChild())));
    }

    static int height(AVLNode node){ // return the height of node
        // return -1 when null because height = 1 + max (left, right) ==. if it null then 1 + -1 = 0.
        return node==null? -1 : node.getHeight();
    }



    // rightRotation
    public static AVLNode rightRotate(AVLNode node){

        /*
           (parent)
              |
            (80) (top node)                   (parent)  "set parent to point at new top node (splayedNode)"
            /                                    |
          (50) splayed node   rotate right ==> (50)   ==> splayed node be the top node after balancing
          /  \                                /    \
        (40)  (60)                         (40)    (80) ==> origin top, it's left point at the right child of splayed node, and
                                                   /        set the parent to be the splayed node which is become the new top node.
                                                (60)
         ==> re-height the top node and the original top node


     */
        AVLNode splayedNode = node.getLeftChild(); // get backup the leftChild node which is the splayed node
        node.setLeftChild(splayedNode.getRightChild());// set the leftChild of the origin top node = the rightChild of the splayedNode.
        if (splayedNode.getRightChild() != null){
            node.setLeftChild(splayedNode.getRightChild());
            node.getLeftChild().setParent(node);
        }

        splayedNode.setRightChild(node); // set rightChild of splayedNode to be the top node (node)
        if (node.getParent()!= null ){
            // the parent of the origin top is not null, to avoid the case node == root.
            if ( node.getParent().getElement() >= splayedNode.getElement()) {
                // if it smaller than that parent ==> parent will set the new top node (splayed node) to the left.
                node.getParent().setLeftChild(splayedNode);
            }else { // else ==> to the right.
                node.getParent().setRightChild(splayedNode);
            }
        }
        // reset parent of the new top node (splayed node) to point at the parent of the origin top node .
        splayedNode.setParent(node.getParent());

        // set the original top node point at splayedNode as parent.
        node.setParent(splayedNode);
        // re-height all these nodes
        reHeight(node);
        reHeight(splayedNode);
        // return the new top node of the new balanced branch which is the splayed node.
        return splayedNode;
    }



    // left Rotation
    public static AVLNode leftRotate(AVLNode node){ // opposite with right rotation
        AVLNode splayedNode = node.getRightChild();
        node.setRightChild(splayedNode.getLeftChild());
        if (splayedNode.getLeftChild() != null){
            node.setRightChild(splayedNode.getLeftChild());
            node.getRightChild().setParent(node);
        }
        splayedNode.setLeftChild(node);
        if (node.getParent() != null ){
            if ( node.getParent().getElement() >= splayedNode.getElement()) {
                node.getParent().setLeftChild(splayedNode);
            }else {
                node.getParent().setRightChild(splayedNode);
            }
        }
        splayedNode.setParent(node.getParent());
        node.setParent(splayedNode);
        reHeight(node);
        reHeight(splayedNode);
        return splayedNode;
    }


    public static AVLNode LeftRightRotate(AVLNode node){
    /*
        in this case: the splayed node has the right > the left.
        (80)                                                     (80)
       /                                                        /
      (40)      ==> left rotate the left child of 80 ==>    (50)   then rotate right ==>      (50)  then return node (50)
        \                                                    /                               /    \
         (50)                                             (40)                            (40)    (80)

     */
        leftRotate(node.getLeftChild());
        return rightRotate(node);
    }

    public static AVLNode RightLeftRotate(AVLNode node){
        /*
        in this case: the splayed node has the left > the right.
        (60)                                                     (60)
           \                                                        \
           (90)     ==> right rotate the right child of 80 ==>      (80)   then rotate left ==>    (80)   then return node (80)
           /                                                          \                           /    \
         (80)                                                          (90)                    (60)    (90)

     */
        rightRotate(node.getRightChild());
        return leftRotate(node);
    }

    public void clear (AVLNode root){
        if (!(root.getLeftChild()==null)){
            clear(root.getLeftChild());
            root.setLeftChild(null);
        }

        if (!(root.getRightChild()==null)){
            clear(root.getRightChild());
            root.setRightChild(null);
        }

        this.root = null;
    }

    public static void preorder(AVLNode n){
        if (n != null){

            if (!(n.getLeftChild()==null)){
                preorder(n.getLeftChild());
            }
            array.add(n.element);

            if (!(n.getRightChild()==null)){
                preorder(n.getRightChild());
            }
        }


    }

    public String toString(){
        array = new ArrayList<>();
        preorder(this.root);
        return array.toString();
    }

}
