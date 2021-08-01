package DatastructuresAlgorithm.Hashing;

import java.util.LinkedList;

public class hashmap<E, E2> {
    private LinkedList<Node<E, E2>>[] buckets;
    private int hashSize;


    static class Node<E, E2> {
        private E key;
        private E2 value;

        Node (E key, E2 value){
            this.key = key;
            this.value = value;
        }
        public String toString(){
            return this.key + " = " + this.value;
        }

    }

    public hashmap(int size){
        buckets = (LinkedList<Node<E, E2>>[]) new LinkedList[size];

        for (int i=0; i<size; i++){
            buckets[i] = new LinkedList<>();
        }
        this.hashSize = size;
    }
    public hashmap(){
        int defaultSize = 100;
        buckets = (LinkedList<Node<E, E2>>[]) new LinkedList[defaultSize];

        for (int i=0; i<defaultSize; i++){
            buckets[i] = new LinkedList<>();
        }
        this.hashSize = defaultSize;
    }

    private int hashFunctionInt (int key){
        return key%(hashSize) ;
    }

    public void print (E2 item){
        System.out.println(item);
    }

    public void printHashMap(){
        for(LinkedList<Node<E, E2>> item : buckets){
            System.out.println(item);
        }
    }

    private int getHashString(String word){
        int hash = 0;
        for (int i = 0; i < word.length(); i++){
            hash += word.charAt(i) - 'a';
        }
        if (hash < 0) hash = -hash;

        return hash%hashSize;
    }

    private int getHashCharacter (char c){
        int hash = c - 'a';
        if (hash < 0) hash = -hash;
        return hash%hashSize;
    }


    private int getHash (E key){
        String type = key.getClass().getSimpleName();
        int index = 0;
        if (type.equals("Integer")) {
            index = hashFunctionInt((Integer) key);
        }
        else if (type.equals("String")){
            index = getHashString((String) key);
        }else if (type.equals("Character")){
            index = getHashCharacter((Character) key);
        }
        return index;
    }


    public void put( E key, E2 value){
        int index = getHash(key);
        if (!Exist(buckets[index], key)) buckets[index].push( new Node<>(key, value));

    }

    public Node<E, E2> Search (E key){

        int index = getHash(key);
        LinkedList<Node<E, E2>> chain = this.buckets[index];
        if (chain.size() == 1 && chain.get(0).key == key){
            return chain.get(0);
        }else if (chain.size() >=2){
            for (Node<E, E2> item : chain){
                if (item.key == key){
                    return item;
                }
            }
        }
        return null;
    }

    private boolean Exist ( LinkedList<Node<E, E2>> chain ,E key){
        for (Node<E, E2> item : chain){
            if (item.key == key) return true;
        }

        return false;
    }


    public E2 get(E key){
        Node<E, E2> result = Search(key);
        if (result != null){
            return result.value;
        }
        return null;
    }



    public static void main (String[] args){
        hashmap<Character, Integer> hashMap = new hashmap<>(10);
//        hashMap.print(hashMap.hashFunctionInt(53));

        hashMap.put('a',4);
        hashMap.put('b',5);
        hashMap.put('c',5);
        hashMap.put('d',70);
        hashMap.put(' ',8);
        hashMap.put('2',7);
        hashMap.put('a',9);

        hashMap.put('j',4);
        hashMap.put('l',5);
        hashMap.put('d',5);
        hashMap.put('0',7);
        hashMap.put('m',8);
        hashMap.put('e',7);
        hashMap.put('p',9);

        hashMap.put('h',4);
        hashMap.put('c',5);
        hashMap.put('d',5);
        hashMap.put('g',100);
        hashMap.put('n',8);
        hashMap.put('e',7);
        hashMap.put('p',9);



        hashMap.printHashMap();
        hashMap.print(hashMap.get('g'));
        hashMap.print(hashMap.get('d'));
        hashMap.print(hashMap.get('c'));
        hashMap.print(hashMap.get('a'));
        hashMap.print(hashMap.get('0'));



        hashmap<String, Integer> hash = new hashmap<>();
        hash.put("hoang dao", 1902093);
        hash.put("ngay sinh", 23);
        hash.put("thang sinh", 06);
        hash.put("nam sinh", 2001);
        int a = hash.get("nam sinh");
        hash.print(a);



    }
}
