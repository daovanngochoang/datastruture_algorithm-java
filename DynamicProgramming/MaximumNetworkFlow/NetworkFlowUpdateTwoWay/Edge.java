package DynamicProgramming.MaximumNetworkFlow.NetworkFlowUpdateTwoWay;


public class Edge<E> implements Comparable<E> {
    Node<E> destination, previous;
    int flow, Capability;


    public Edge(Node<E> previous, Node<E> destination, int Capability) {
        this.destination = destination;
        this.previous = previous;
        this.Capability = Capability;
        this.flow = 0;
    }

    public int getCapability() {
        return Capability - flow;
    }

    @Override
    public int compareTo(E e) {
        return 0;
    }

    @Override
    public String toString() {
        return " remain = " + getCapability();

    }
}