package Datastructures.HuffmanTree;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HuffmanTree {

    Node root;
    HashMap<Character, String> hashMap = new HashMap<Character, String>();

    static class Node {
        Character element;
        Node left, right;
        int weight;

        Node (Character element, int weight ){
            this.element = element;
            this.weight = weight;
        }
    }



    static ArrayList<Node> QuickSort(ArrayList<Node> array){
        int len = array.size(), middleIndex = (len -1) / 2, lowerQuantity =0, higherQuantity =0;
        ArrayList<Node> lower = new ArrayList<>(), higher = new ArrayList<>();

        // stop condition of recursion
        if(len <= 2){ // if the array <=2 => rearrange and return
            Node temp;
            if (len == 2){
                if (array.get(0).weight > array.get(1).weight){ // swap order
                    temp = array.get(1);
                    array.set(1, array.get(0));
                    array.set(0, temp);
                }
            }
            return array;
        }
        else{ // if array is more than 2 items
            // choose middleIndex as pivot and  copy lower item to the lower array and higher item to higher array
            for (int i =0; i < len; i++){
                if (i != middleIndex){
                    if (array.get(i).weight < array.get(middleIndex).weight){
                        lower.add(lowerQuantity++, array.get(i));
                    }else {
                        higher.add(higherQuantity++, array.get(i));
                    }
                }
            }

            // add pivot to lower array
            lower.add(lowerQuantity++, array.get(middleIndex));

            if (lowerQuantity == 0){
                QuickSort(higher);

            }else if (higherQuantity == 0){
                QuickSort(lower);

            }else {
                QuickSort(lower);
                QuickSort(higher);
            }
            array.clear();
            array.addAll(lower);

            for (int i = 0; i < higherQuantity ; i++){
                array.add(lowerQuantity++,  higher.get(i));
            }
        }
        return array;
    }

    static ArrayList<Node> rearrange (ArrayList<Node> listNode, Node node){
        int len = listNode.size();
        boolean flag = false;
        ArrayList<Node> newArray = new ArrayList<>();

        for (int i = 2 ; i < len; i++ ){
            Node eNode = listNode.get(i);
            if (node.weight < eNode.weight && !flag){
                newArray.add(node);
                flag = true;
                newArray.add(eNode);
            }else {
                newArray.add(eNode);
            }
        }
        if (!flag){
            newArray.add(node);
        }
        return newArray;
    }

    public void buildTree (String info){
        ArrayList<Node> NodeList;
        Node newRoot = null;
        // read string
        // add a list of nodes and weight
        NodeList = readStringInfo(info);

        // sort the list based on the weight
        QuickSort(NodeList);
        // insert to the tree

        while (NodeList.size() > 1){

            Node a = NodeList.get(0);
            Node b = NodeList.get(1);

            newRoot = new Node(null, a.weight + b.weight);

            newRoot.left = a;
            newRoot.right = b;

            if (NodeList.size() > 2) {
                NodeList = rearrange(NodeList, newRoot);
            }else {
                NodeList.clear();
            }


        }

        this.root = newRoot;


    }

    private void traveral(Node root, String prep){
        if (root.right == null && root.left ==null){

            this.hashMap.put(root.element, prep);
            return;
        }

        traveral(root.left, prep+'0');
        traveral(root.right, prep+'1');

    }

    static Node checkExist(ArrayList<Node> listNode, char item){
        for (Node eNode : listNode ){
            if (eNode.element == item){
                return eNode;
            }
        }
        return null;
    }


    public String Encoding(String s){

        String Result = "";

        buildTree(s);

        String prep = "";
        traveral(root, prep);

        for (int i = 0; i<s.length(); i++){
            Result += this.hashMap.get(s.charAt(i));
        }
        return Result;
    }

    static boolean isLeaf (Node node){
        return node.left == null && node.right == null;
    }

    public String Decoding(String S){
        String Result = "";
        Node current = root;
        for(int i = 0; i < S.length(); i++){


            if (S.charAt(i) == '0'){
                current = current.left;

                if (isLeaf(current)){
                    Result += current.element;
                    current = root;
                }}


            if (S.charAt(i) == '1') {
                current = current.right;

                if (isLeaf(current)) {
                    Result += current.element;
                    current = root;
                }

            }
        }

        return Result;
    }

    static ArrayList<Node> readStringInfo(String info){
        ArrayList<Node> listNode = new ArrayList<>();
        Node checkNode;
        char elem;

        for(int i = 0; i < info.length(); i++){
            elem = info.charAt(i);
            checkNode = checkExist(listNode,elem);
            if (checkNode != null){
                checkNode.weight ++;
            }else {
                listNode.add(new Node(elem, 1));
            }
        }
        return listNode;
    }

    public static void main (String[] args){
        HuffmanTree compress = new HuffmanTree();

        String en = compress.Encoding("a man a plan a canal panama");
        System.out.println(compress.hashMap);
        System.out.println(en);
        System.out.println(en.length());
        String  de = compress.Decoding(en);
        System.out.println(de);
        System.out.println(de.length()*3);




    }

}
