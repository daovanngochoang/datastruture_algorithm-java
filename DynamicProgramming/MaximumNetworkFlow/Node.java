package DynamicProgramming.MaximumNetworkFlow;

import java.util.LinkedList;


public class Node<E> implements Comparable<Node<E>> {
    E item;
    LinkedList<Edge<E>> Edges;
    int level, coordinate;

    public Node(E item) {
        this.item = item;
        this.Edges = new LinkedList<>();
        this.level = -1;
    }

    @Override
    public int compareTo(Node<E> eNode) {
        return 0;
    }

    @Override
    public String toString() {
        return
                "item=" + item
                ;
    }
}
