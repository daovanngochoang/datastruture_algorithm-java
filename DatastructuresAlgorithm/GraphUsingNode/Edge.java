package DatastructuresAlgorithm.GraphUsingNode;

public class Edge<E> {
    GraphTraversals.GraphNode<E> destination;
    int weight;

    Edge (GraphTraversals.GraphNode<E> pointAt, int w){
        this.destination = pointAt;
        this.weight = w;
    }
}
