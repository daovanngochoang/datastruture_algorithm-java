package Datastructures.BinaryTree.SplayTree.newTechnique;

public class TreeSpl<E> {

    node<E> root ;


    public node<E> rightRotate (node<E> n){
        node<E> temp = n.leftChild;
        n.leftChild = (temp.rightChild);
        temp.rightChild = (n);
        return temp;
    }

    public node<E> leftRotate (node<E> n){
        node<E> temp = n.rightChild;
        n.rightChild = (temp.leftChild);
        temp.leftChild = (n);
        return temp;
    }


    public node<E> Splay(node<E> root, int key){

        if (root == null || key == root.key) {
            return root;

        }else if (key < root.key){

            // key is not in the tree.
            if (root.leftChild == null){
                return root;

            }else if (key < root.leftChild.key){ // left-left case.

                // recursively to the its left-left
                root.leftChild.leftChild = Splay(root.leftChild.leftChild, key);
                // if the grand child is not null ==> rotate the child
                // first rotation
                if (root.leftChild.leftChild != null) root.leftChild = rightRotate(root.leftChild);

            }else {

                // left-right case
                root.leftChild.rightChild = Splay(root.leftChild.rightChild, key);
                // if the grand child is not null ==> rotate the child
                // first rotation
                if (root.leftChild.rightChild != null) root.leftChild = leftRotate(root.leftChild);
            }
            // second rotation
            return root.leftChild == null ? root :  rightRotate(root);


        }else {
            if (root.rightChild == null) return root; // key not in the tree


            else if (key > root.rightChild.key){ // right-right case
                root.rightChild.rightChild = Splay(root.rightChild.rightChild, key);
                // if the grand child is not null ==> rotate the child
                // first rotation
                if (root.rightChild.rightChild != null) root.rightChild = leftRotate(root.rightChild);


            }else { // right-left case
                root.rightChild.leftChild = Splay(root.rightChild.leftChild, key);
                // if the grand child is not null ==> rotate the child
                // first rotation
                if (root.rightChild.leftChild != null) root.rightChild = rightRotate(root.rightChild);
            }

            // second rotation
            return root.rightChild == null ? root : leftRotate(root);
        }

    }
    public void insert (int key ,E elem){
        // insert as the insert function of binary tree
        if (this.root == null){
            this.root = new node<E>(key, elem );

        }else {

            this.root = Splay(this.root, key); // splay parent node to the top first

            // insert the new node to the top node
            if (this.root.key < key){
                if (this.root.rightChild == null){
                    this.root.rightChild = new node<E>(key, elem );}
                else {
                    node<E> temp = new node<E>(key, elem );
                    if (temp.key > root.rightChild.key){
                        temp.leftChild = root.rightChild;
                        root.rightChild = temp;
                    }else {
                        temp.rightChild = root.rightChild;
                        root.rightChild = temp;
                    }
                }
            }else {
                if (this.root.leftChild == null)this.root.leftChild = new node<E>(key, elem );
                else {
                    node<E> temp = new node<E>(key, elem );
                    if (temp.key > root.leftChild.key){
                        temp.leftChild = root.leftChild;
                        root.leftChild = temp;
                    }else {
                        temp.rightChild = root.leftChild;
                        root.leftChild = temp;
                    }
                }
            }
            // splay new node to the top.
            this.root = Splay(this.root, key);
        }
    }

    public E search (node<E> root, int key){
        node<E> result = Splay(root, key);
        this.root = result;
        return result.element;
    }

    void preOrder(node<E> root)
    {
        if (root != null)
        {
            System.out.print(root.element + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }


    public node<E> finMin(node<E> n){
        // find min will return the parent of the min node.
        if (n.leftChild != null){
            if (n.leftChild.leftChild==null){
                return n;
            }
            return finMin(n.leftChild);
        }else {
            return null;
        }
    }


    public void remove (int key){

        // bring the node to be remove to the top
        this.root = Splay(this.root, key);
        if (this.root.key != key ){
            return;
        }
        // get the min parent and min node of the right side
        node<E> alterParent = finMin(this.root.rightChild);
        node<E> alternative = alterParent.leftChild;

        // because min always lie on the end of the left side ==> check the right side
        if (alternative.rightChild != null){ // if it's right side still has a child
            alterParent.leftChild = alternative.rightChild;
        }else { // if it's right side is null
            alterParent.leftChild = null;
        }
        // bring the min as root
        alternative.leftChild = root.leftChild;
        alternative.rightChild = root.rightChild;

        // in case the min lie at the right of the root
        if (alterParent == this.root){
            this.root.rightChild = null;
        }

        this.root = alternative; // change root.

    }


    public static void main (String [] args){
        TreeSpl<Character> test1 = new TreeSpl<>();
        test1.insert(100, 'a');
        test1.insert(10, 'b');
        test1.insert(8, 'c');
        test1.insert(1, 'd');
        test1.insert(50, 'e');
        test1.insert(200, 'f');
        test1.insert(300, 'g');
        test1.insert(150, 'h');
        test1.insert(30, 'i');
        test1.insert(40, 'k');
        test1.insert(400, 'l');

        test1.preOrder(test1.root);
        System.out.println("\n aa" + test1.search(test1.root, 50));
        test1.remove(50);
        test1.remove(200);
        test1.remove(100);
        test1.remove(50);
        test1.remove(50);
        test1.remove(50);
        test1.remove(50);
        test1.remove(50);

        test1.preOrder(test1.root);

    }
}
