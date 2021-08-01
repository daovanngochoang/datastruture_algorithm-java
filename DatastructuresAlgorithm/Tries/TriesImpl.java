package DatastructuresAlgorithm.Tries;

import java.util.LinkedList;


public class TriesImpl {

    private final node root = new node(null, null);
    static class node {
        String word;
        Character character;
        LinkedList<node> children;

        node (Character c, String w){
            this.word = w;
            this.children = new LinkedList<>();
            this.character = c;
        }
    }


    private node findNode (char character, LinkedList<node> childrenList){
        for (node item : childrenList){
            if (item.character == character){
                return item;
            }
        }
        return null;
    }

    public void add (String w){
        buildTries(this.root, 0, w);
    }




    public void buildTries (node root, int index, String w){

        if (index >= w.length()){ // if the length of the word is out of index ==> insert that word to current node
            root.word = w;
            return;
        }

        // find the similar node by char
        node similarCharNode = findNode(w.charAt(index), root.children);

        if (similarCharNode == null){ // if not => insert to children new node of word and current char
            root.children.add(new node(w.charAt(index), w));


        }else { // if there is an similar node with the same char
            if (similarCharNode.word == null || !similarCharNode.children.isEmpty()){ // if that word is null ==>
                buildTries(similarCharNode, ++index, w);

            }else {
                index ++;
                if (similarCharNode.word.equals(w)) {
                    return;
                }
                while (index < similarCharNode.word.length() || index < w.length()) { // length condition

                    // in case the index is smaller than the length of 2 words
                    if (index < similarCharNode.word.length() && index < w.length()) {

                        // if char is equal ==> add new node
                        if (similarCharNode.word.charAt(index) == w.charAt(index)) {
                            // add new node with current char and move the word from the parent to the new child
                            node newNode = new node(w.charAt(index), similarCharNode.word);
                            // set new node to be the children of the current
                            similarCharNode.children.add(newNode);
                            similarCharNode.word = null; // set current word = null
                            similarCharNode = newNode; // move the current to new node
                            index++; // increase the index to move on the next char

                        } else { // the case that 2 chars are different
                            // add 2 new node of the 2 char and the current word vs new word to be the children of the current.
                            similarCharNode.children.add(new node(w.charAt(index), w));
                            similarCharNode.children.add(new node(similarCharNode.word.charAt(index), similarCharNode.word));
                            similarCharNode.word = null;
                            break; // done
                        }
                    } else if (index >= similarCharNode.word.length()) { // in case, new word has longer length
                        // add the next index of the last pair of chars, ton and tong ==> add g.
                        similarCharNode.children.add(new node(w.charAt(index), w));
                        break; // done
                    } else {
                        similarCharNode.children.add(new node(similarCharNode.word.charAt(index), similarCharNode.word)); // in case the current word is longer
                        similarCharNode.word = w;
                        break;
                    }
                }
            }
        }

    }

    public void insert (String w){
        int index = 0;
        node cur = root, newNode;

        while (true) {

            if (index >= w.length()){ // if the length of the word is out of index ==> insert that word to current node
                cur.word = w;
                return;
            }
            // find the node which has the same character with the current character
            node similarCharNode = findNode(w.charAt(index), cur.children);

            // if there is no node has the same char ==> insert that node and word to the child list
            if (similarCharNode == null) {
                cur.children.add(new node(w.charAt(index), w));
                break;

            } else {
                // if we find a node has the same char with the current char of the current index ==> check for
                // the characters after that index ==> index ++.
                index++;
                cur = similarCharNode; // set current node = the node has the same char

                if (cur.word == null || !cur.children.isEmpty()){
                    continue;
                }
                //  if word != null ==> that is the leaf node and it contain the complete word
                if (cur.word.equals(w)) {
                    break;
                }
                // then compare with new word
                while (index < cur.word.length() || index < w.length()) { // length condition

                    // in case the index is smaller than the length of 2 words
                    if (index < cur.word.length() && index < w.length()) {

                        // if char is equal ==> add new node
                        if (cur.word.charAt(index) == w.charAt(index)) {
                            // add new node with current char and move the word from the parent to the new child
                            newNode = new node(w.charAt(index), cur.word);
                            // set new node to be the children of the current
                            cur.children.add(newNode);
                            cur.word = null; // set current word = null
                            cur = newNode; // move the current to new node
                            index++; // increase the index to move on the next char

                        } else { // the case that 2 chars are different
                            // add 2 new node of the 2 char and the current word vs new word to be the children of the current.
                            cur.children.add(new node(w.charAt(index), w));
                            cur.children.add(new node(cur.word.charAt(index), cur.word));
                            cur.word = null; // set current word is null
                            break; // done
                        }
                    } else if (index >= cur.word.length()) { // in case, new word has longer length
                        // add the next index of the last pair of chars, ton and tong ==> add g.
                        cur.children.add(new node(w.charAt(index), w));
                        break; // done
                    } else {
                        cur.children.add(new node(cur.word.charAt(index), cur.word)); // in case the current word is longer
                        cur.word = w;
                        break;
                    }
                }
                break;
            }
        }
    }

    boolean check (String w){
       if (search(this.root, 0, w)!= null) return true;
        return false;
    }

    public String search(node root, int index,String w){
        // in case the current node contain that word
        if (root.word != null && root.word.equals(w)){
            return root.word;
        }
        // if the length of the word is out of index ==> insert that word to current node
        if (index >= w.length()){
            return null;
        }
        // find the similar node by char
        node similarCharNode = findNode(w.charAt(index), root.children);
        // if similar node is null from the child of root ==> its not in the tree
        if (similarCharNode == null) return null;

        return search(similarCharNode, ++index, w); // recursively find that word in the similar child node.

    }

    // remove item from the list of children
    private void removeFromList (LinkedList<node> nodeList, String w){
        for (int i=0; i<nodeList.size();i++){
            if (nodeList.get(i).word.equals(w)){
                nodeList.remove(i);
                break;
            }
        }
    }

    public void remove (String w){
        delete(this.root, w, 0);
    }

    private void delete ( node root, String w, int index ){
        // if index > length ==> not found the word
        if (index >= w.length()){
            return ;
        }else {

            // if index still smaller than word
            // find the similar node from the current node/ root
            node similarCharNode = findNode(w.charAt(index), root.children);
            // if similar is not found ==> its not in the tree
            if (similarCharNode == null) {
                return ;
            } else { // if similarCharNode is found
                // in case, it's is the last node and contain the word that to be remove
                if (similarCharNode.children.isEmpty() && similarCharNode.word.equals(w)) {
                    this.removeFromList(root.children, w); // remove that node from root's children

                // in case, the similar node is not a leaf node but it contain the word
                } else if (similarCharNode.word != null && similarCharNode.word.equals(w)) {
                    similarCharNode.word = null;
                }else {
                    delete(similarCharNode, w, ++index); // if it is not found ==> recursively to the similarCharNode.
                }
            }
        }
        // rearrange, if a root contain 1 child ==> set that child's word to root and remove the node in root child
        if (root.children.size() == 1){
            root.word = root.children.get(0).word;
            root.children.clear();
        }

    }


    private String showWord (node root){
        String words = "";
        if (root.word != null) words += root.word + "\n";
        if (root.children.isEmpty()){
            return words;
        }
        else {
            for (node item : root.children){
                words += showWord(item);
            }
        }

        return words;
    }


    public String StartWith (String w){
        int index = 0;
        node cur = root;

        while (index < w.length()){
            // find the node which has the same character with the current character
            node similarCharNode = findNode(w.charAt(index), cur.children);

            if (similarCharNode == null){
                return null;
            }
            cur = similarCharNode;
            index ++;
        }

        return this.showWord(cur);

    }

    public static void main (String[] args){
        TriesImpl tries = new TriesImpl();
        tries.insert("control");
        tries.insert("cabinet");
        tries.insert("combination");
        tries.insert("banana");
        tries.insert("bank");
        tries.insert("beach");
        tries.insert("simple");
        tries.insert("soap");
        tries.insert("two");
        tries.insert("variations");
        tries.insert("on");
        tries.insert("the");
        tries.insert("alphabet");
        tries.insert("trie");
        tries.insert("respect");
        tries.insert("representation");
        tries.insert("reply");
        tries.insert("presentation");
        tries.insert("represent");
        tries.insert("representation");
        tries.insert("repo");
        tries.insert("repository");
        tries.insert("repos");
        tries.insert("report");
        tries.insert("for");
        tries.insert("set");
        tries.insert("apple");
        tries.insert("ambiguous");
        tries.insert("amd");
        tries.insert("ten");
        tries.insert("ton");
        tries.insert("tong");
        tries.insert("Notion");
        tries.insert("tool");
        tries.insert("angle");
        tries.insert("to");
        tries.insert("tongion");
        tries.insert("tonde");
        tries.insert("tony");
        tries.insert("told");
        tries.insert("tolk");

//        System.out.println(tries.check("t"));
//        System.out.println(tries.check("si"));
//        System.out.println(tries.check("representation"));
//        System.out.println(tries.check("repos"));
//        System.out.println(tries.StartWith("respect"));
        tries.remove("told");













    }


}
