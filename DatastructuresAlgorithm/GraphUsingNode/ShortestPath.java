package DatastructuresAlgorithm.GraphUsingNode;

import java.util.LinkedList;

public class ShortestPath<E> {

    static class GNode<E> {
        E item;
        int weight;
        LinkedList<Edge<E>> Edges ;

        GNode (E item){
            this.item = item;
            this.weight = Integer.MAX_VALUE;
            this.Edges = new LinkedList<>();
        }
    }




}
