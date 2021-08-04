package DatastructuresAlgorithm.GraphUsingNode;


import java.util.LinkedList;


public class GNode<E> implements Comparable<GNode<E>>{
    public E item;
    public int weight;
    public LinkedList<Edge<E>> Edges ;
    public GNode<E> previous;

    public GNode(E item){
        this.item = item;
        this.weight = Integer.MAX_VALUE;
        this.Edges = new LinkedList<>();
    }

    public void SortList (){
        sort<E> eSort = new sort<>();
        eSort.QuickSortTraditionalMethodNonRecursion(this.Edges);
    }

    public void resetWeight(){
        this.weight = Integer.MAX_VALUE;
        this.previous = null;
    }

    @Override
    public String toString() {
        return  item + " weight=" + weight + " Edges=" + Edges +" previous=" + previous;
    }


    @Override
    public int compareTo(GNode gNode) {
        return 0;
    }
}