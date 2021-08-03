package DatastructuresAlgorithm.GraphUsingNode;

public class Edge<E> {
    GNode<E> destination;
    int weight;

    Edge (GNode<E> pointAt, int w){
        this.destination = pointAt;
        this.weight = w;
    }

}
