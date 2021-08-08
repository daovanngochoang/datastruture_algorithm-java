package DynamicProgramming.Graph;

public class Edge<E> implements Comparable<E>{
    public GNode<E> destination;
    public int weight;

    public Edge(GNode<E> pointAt, int w){
        this.destination = pointAt;
        this.weight = w;
    }

    @Override
    public int compareTo(E e) {
        return 0;
    }

}
