package DynamicProgramming.MaximumNetworkFlow.NetworkFlowUpdateTwoWay;

import java.util.LinkedList;


public class Node<E> implements Comparable<Node<E>> {
    E item;
    LinkedList<Edge<E>> Edges;
    int parentRate;
    boolean deadEnd = false;

    public Node(E item) {
        this.item = item;
        this.Edges = new LinkedList<>();
        this.parentRate = 0;
    }

    protected boolean checkDeadEndStatus() {
        for (Edge<E> edge : this.Edges) {
            if (edge.getCapability() > 0) {
                return false;
            }
        }
        return true;
    }

    protected void updateDeadEnd() {
        this.deadEnd = checkDeadEndStatus();
    }

    public boolean getDeadEndStatus() {
        return this.deadEnd;
    }


    @Override
    public int compareTo(Node<E> eNode) {
        return 0;
    }

    @Override
    public String toString() {
        return "" + item
                ;
    }
}
