package Datastructures.BinaryTree.SplayTree.myTechnique;


import java.util.ArrayList;

public class SplayTree<E> {
    private SNode<E> root;
    ArrayList<E> array;


    SplayTree (){
        this.root = null;
    }

    public void remove(int key){
        /*
            delete a node :
                - get the item in the smallest node from it's right branch and set to the be the item in the node we
                going to delete, delete it's right smallest node.
         */
        SNode<E> cur = root;
        SNode<E> alternativeItem;
        while (cur.key != key){
            if (key <= cur.getKey()){
                cur = cur.getLeftChild();
            }else {
                cur = cur.getRightChild();
            }

            if (cur == null){
                return;
            }
        }

        if (cur.isLeaf()){ // if the cur is leaf
            setChild(cur.getParent(), cur, null); // set the parent point to null at the position we want to remove

        }else {

            alternativeItem = this.finMin(cur.getRightChild()); // get the smallest item of it's right branch
            if (alternativeItem == null){ // if there is no smallest on the right --> set the parent of the deleted node to it's left.

                if (cur == cur.getParent().leftChild){ // if the delete item is in the left of it's parent

                    alternativeItem = cur.getLeftChild();
                    cur.getParent().setLeftChild(alternativeItem); // set it's parent to point at it's left branch
                    alternativeItem.setParent(cur.getParent()); // set parent to its left branch
                    cur.setParent(null); // set delete parent to null

                }else { // right
                    alternativeItem = cur.getLeftChild();
                    cur.getParent().setRightChild(alternativeItem); // set parent to point at its right branch
                    alternativeItem.setParent(cur.getParent()); // set parent to its right branch
                    cur.setParent(null); // set delete parent to null
                }

            }else{
                cur.setElement(alternativeItem.element);  // set the alternative element up to the element we want to delete
                cur.setKey(alternativeItem.key);
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
    }


    public void setChild(SNode<E> parent, SNode<E> compareNode, SNode<E> newChild){
        // compare the parent with its the other node that to be set as its child
        if (parent.key >= compareNode.key){ // if < parent ==> left
            parent.setLeftChild(newChild);
        }else { // the right
            parent.setRightChild(newChild);
        }
    }

    public SNode<E> findMax(SNode<E> n){
        if (n != null) {

            if (n.getRightChild() == null) {
                return n;
            }
            return findMax(n.getRightChild());
        }else {
            return null;
        }
    }

    public SNode<E> finMin(SNode<E> n){
        if (n != null){
            if (n.getLeftChild()==null){
                return n;
            }
            return finMin(n.getLeftChild());
        }else {
            return null;
        }
    }

    // insert item
    // insert nonRecursion
    public void insert (int key ,E elem){
        // insert as the insert function of binary tree
        if (this.root == null){
            this.root = new SNode<E>(key, elem , null);

        }else {
            SNode<E> current  = root;
            while (true){
                if (key == current.key) break; // not allowed duplicate
                if (key  < current.getKey()){
                    if(!(current.getLeftChild() == null)){
                        current = current.leftChild;
                    }else {
                        current.setLeftChild(new SNode<E>(key, elem , current));
                        current = current.getLeftChild();
                        break;
                    }

                }else {
                    if(!(current.getRightChild()==null)){
                        current = current.rightChild;
                    }else {
                        current.setRightChild(new SNode<E>(key, elem , current));
                        current= current.getRightChild();
                        break;
                    }
                }
            }
            this.Splay(current);
        }
    }

    public E Search (int key){
        SNode<E> current = root;

        while (current!= null){
            if (current.key == key){ // if we find that item ==> splay to the top, return that element.
                this.Splay(current);
                return current.getElement();
            }
            if (key < current.getKey()){ // if it's key < current ==> go left
                current = current.getLeftChild();
            }else { // else, go right
                current = current.getRightChild();
            }
        }

        return null;
    }

    public SNode<E> getRoot() {
        return root;
    }

    public void setRoot(SNode<E> root) {
        this.root = root;
    }

    // rotation cases and scenarios

    // rightRotation
    public void rightRotate(SNode<E> splayedNode){

        /*
        - play up, from the node to its parent.
        - splayed node change position with its parent, and repeat this action until it get into the top.
         */

        if (splayedNode.getRightChild() != null){ // in case its right chill is not null => must set parent for that node to the new parent
            // set parent of it's right node to its parent, because it will point at it's parent as right chill and its parent point at its right chill as left chill
            splayedNode.getRightChild().setParent(splayedNode.getParent());
        }
        // its parent point to its right chill as left child.
        splayedNode.getParent().setLeftChild(splayedNode.getRightChild());
        // set its parent to be the right.
        splayedNode.setRightChild(splayedNode.getParent());

        // in case, its grandparent is not null
        if (splayedNode.getParent().getParent() != null){
            // if it's parent is the lef chill of grandparent ==> grand parent will point at splayNode as leftChild
            if (splayedNode.getParent().getParent().leftChild == splayedNode.getParent()){
                splayedNode.getParent().getParent().setLeftChild(splayedNode);
            }else { // else => ...... right child
                splayedNode.getParent().getParent().setRightChild(splayedNode);
            }
        }
        // reset, its parent to be its granParent
        splayedNode.setParent(splayedNode.getParent().getParent());
        // set its right, which is its original parent to point at it as parent.
        splayedNode.getRightChild().setParent(splayedNode);


    }



    // left Rotation
    // is contradict action with rightRotation.
    public void leftRotate(SNode<E> splayedNode){ // opposite with right rotation

        if (splayedNode.getLeftChild() != null){
            splayedNode.getLeftChild().setParent(splayedNode.getParent());
        }
        splayedNode.getParent().setRightChild(splayedNode.getLeftChild());
        splayedNode.setLeftChild(splayedNode.getParent());
        if (splayedNode.getParent().getParent() != null){
            if (splayedNode.getParent().getParent().rightChild == splayedNode.getParent()){
                splayedNode.getParent().getParent().setRightChild(splayedNode);
            }else {
                splayedNode.getParent().getParent().setLeftChild(splayedNode);
            }
        }

        splayedNode.setParent(splayedNode.getParent().getParent());
        splayedNode.getLeftChild().setParent(splayedNode);


    }



    public void Splay (SNode<E> node) {


        while (node != root){ // while, it's parent is not root ==> splay it to the top

            if (node.parent.leftChild == node){ // if it is the left child ==> splay right
                this.rightRotate(node);
            }else if (node.parent.rightChild == node){ // else, left
                this.leftRotate(node);
            }

            if (node.getParent() == null){ // if node is up to the top ==> set it to be root.
                this.root = node;
            }
        }
    }

    public void preorder(SNode<E> n){
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
