package DatastructuresAlgorithm.GraphUsingNode;

public class Edge<E> implements Comparable<E>{
    GNode<E> destination;
    int weight;

    Edge (GNode<E> pointAt, int w){
        this.destination = pointAt;
        this.weight = w;
    }

    @Override
    public int compareTo(E e) {
        return 0;
    }

}
