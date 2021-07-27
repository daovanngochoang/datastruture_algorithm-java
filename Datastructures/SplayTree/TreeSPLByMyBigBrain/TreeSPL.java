package Datastructures.SplayTree.TreeSPLByMyBigBrain;


public class TreeSPL<E> {

    node<E> root;

    static class node <E> {

        node<E> left, right;
        E element;
        int key;

        node (int key, E elem){
            this.element = elem;
            this.key = key;
        }

    }

    public node<E> rightRotate (node<E> n){
        node<E> temp = n.left;
        n.left = (temp.right);
        temp.right = (n);
        return temp;
    }

    public node<E> leftRotate (node<E> n){
        node<E> temp = n.right;
        n.right = (temp.left);
        temp.left = (n);
        return temp;
    }



    public node<E> Splay (node<E> root, int key){
        // if key lie on the root.
        if (root == null || root.key == key) return root;

        // key smaller than the root
        else if (key < root.key){
            // if root.left is null then return root
            if (root.left == null) return root;

            // else, continue goes down to the root left
            root.left = Splay(root.left, key);

            // after it reach the last node, then it execute back 
            root = rightRotate(root);

        }else {
            if (root.right == null) return root;
            root.right = Splay(root.right, key);
            root = leftRotate(root);
        }

        return root;
    }


    // search the item in the tree
    public E search (int key){
        // bring the node to the root
        this.root = this.Splay(this.root, key);

        // the root is the node we want but some time its not equal to st that we want to
        if (this.root.key != key) return null;
        return this.root.element;
    }


    // insert, and bring the new node to the root
    public node<E> insert (node<E> root, int key, E elem){

        // if the root is null, that is the time that it reach the last node of condition and return new node as a result
        // for the call function.
        if (root == null){
            return new node<>(key, elem);


        // when it come to the last, the function will return a new node ==>
        // recursively set new node to it's parent
        }else if (key<root.key){ // set left, right based on the condition
            root.left = insert(root.left, key, elem);
            root = Splay(root, key);
        }else {
            root.right = insert(root.right, key, elem);
            root = Splay(root, key);
        }

        return root;
    }



    public void insert(int key, E elem){
        this.root = insert(this.root, key, elem);
    }



    void preOrder(node<E> root)
    {
        if (root != null)
        {
            System.out.print(root.element + " ");

            preOrder(root.left);
            preOrder(root.right);
        }
    }



    public void remove (int key){

        // bring the node to be remove to the top
        this.root = Splay(this.root, key);
        if (this.root.key != key ){
            return;
        }

        if (root.right == null){
            this.root = root.left;
        }else {
            // get the min parent and min node of the right side
            node<E> alterParent = finMin(this.root.right);
            node<E> alternative = alterParent.left;

            // because min always lie on the end of the left side ==> check the right side
            if (alternative.right != null){ // if it's right side still has a child
                alterParent.left = alternative.right;
            }else { // if it's right side is null
                alterParent.left = null;
            }
            // bring the min as root
            alternative.left = root.left;
            alternative.right = root.right;

            // in case the min lie at the right of the root
            if (alterParent == this.root){
                this.root.right = null;
            }

            this.root = alternative; // change root.
        }
    }

    public node<E> finMin(node<E> n){
        // find min will return the parent of the min node.
        if (n.left != null){
            if (n.left.left==null){
                return n;
            }
            return finMin(n.left);
        }else {
            return null;
        }
    }

    public static void main (String[] args){
        TreeSPL<Integer> test = new TreeSPL<>();
        test.insert(100, 100);
        test.insert(50, 50);
        test.insert(200, 200);
        test.insert(25, 25);
        test.insert(300, 300);
        test.insert(150, 150);
        test.insert(10, 10);
        test.insert(60, 60);

        test.preOrder(test.root);
        System.out.println("\n");
        test.search(300);
        test.search(60);
        test.search(10);

        test.remove(300);
        test.remove(200);
        test.remove(300);
        test.remove(300);


        test.preOrder(test.root);


    }
}
