package DynamicProgramming.MaximumNetworkFlow;


public class Edge<E> implements Comparable<E>{
    Node<E> destination, previous;
    int weight, Capability;


    public Edge(Node<E> previous, Node<E> destination, int Capability){
        this.destination = destination;
        this.previous = previous;
        this.Capability = Capability;
        this.weight = 0;
    }

    @Override
    public int compareTo(E e) {
        return 0;
    }

}