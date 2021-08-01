package DatastructuresAlgorithm.SplayTree.newTechnique;

public class TreeSplay<E> {
    node<E> root;

    public void insert (int key ,E elem){
        // insert as the insert function of binary tree
        if (this.root == null){
            this.root = new node<E>(key, elem );

        }else {

            this.root = splay(this.root, key);
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
            this.root = splay(this.root, key);
        }
    }


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

    public node<E> splay (node<E> root, int key){

        // if the root is null or the key is right in the root.
        if (root == null || root.key == key) return root;

        if (root.key > key){ // if that key <  root.key

            if (root.leftChild == null) return root; // the case that root's left is null

            if (root.leftChild.key > key){
                root.leftChild.leftChild = splay(root.leftChild.leftChild, key);
                root = rightRotate(root);

            }else if (root.leftChild.key < key) {
                root.leftChild.rightChild = splay(root.leftChild.rightChild, key);
                if (root.leftChild.rightChild != null ) root.leftChild = leftRotate(root.leftChild);
            }

            return (root.leftChild == null) ? root : rightRotate(root);
        }else {

            if (root.rightChild == null) return root;

            if (root.rightChild.key < key){
                root.rightChild.rightChild = splay(root.rightChild.rightChild, key);
                root = leftRotate(root);
            }else {
                root.rightChild.leftChild = splay(root.rightChild.leftChild, key);
                if (root.rightChild.leftChild != null) root.rightChild = rightRotate(root.rightChild);
            }

            return (root.rightChild == null) ? root : leftRotate(root);
        }
    }

    public E search (node<E> root, int key){
        node<E> result = splay(root, key);
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


    public static void main (String [] args){
        TreeSplay<Character> test = new TreeSplay<>();
        test.insert(100, 'a');
        test.insert(10, 'b');
        test.insert(8, 'c');
        test.insert(1, 'd');
        test.insert(50, 'e');
        test.insert(200, 'f');
        test.insert(300, 'g');
        test.insert(150, 'h');
        test.insert(30, 'i');
        test.insert(40, 'k');
        test.insert(400, 'l');

        test.preOrder(test.root);
        System.out.println("\n aa" + test.search(test.root, 50));
        test.preOrder(test.root);

    }
}
