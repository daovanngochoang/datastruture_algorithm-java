package DynamicProgramming.MaximumNetworkFlow.NetworkFlowUpdateTwoWay;

import java.util.*;

public class NetworkMaxFlowTwoWayAlgorithm<E> {
    private final HashMap<E, Node<E>> GraphNodes = new HashMap<>();


    public static void main(String[] args) {

        NetworkMaxFlowTwoWayAlgorithm<String> maxFlowTwoWayAlgorithm = new NetworkMaxFlowTwoWayAlgorithm<>();
        maxFlowTwoWayAlgorithm.insert("S", "V1", 25);
        maxFlowTwoWayAlgorithm.insert("S", "V2", 30);

        maxFlowTwoWayAlgorithm.insert("V1", "V4", 15);
        maxFlowTwoWayAlgorithm.insert("V1", "V3", 10);

        maxFlowTwoWayAlgorithm.insert("V2", "V3", 20);
        maxFlowTwoWayAlgorithm.insert("V3", "V5", 120);
        maxFlowTwoWayAlgorithm.insert("V4", "V7", 12);

        maxFlowTwoWayAlgorithm.insert("V5", "V6", 10);

        maxFlowTwoWayAlgorithm.insert("V6", "V8", 12);
        maxFlowTwoWayAlgorithm.insert("V6", "V7", 18);


        maxFlowTwoWayAlgorithm.insert("V7", "T", 9);
        maxFlowTwoWayAlgorithm.insert("V8", "T", 7);

        maxFlowTwoWayAlgorithm.getMaxFlowAndAugmentingPath("S", "T");


        NetworkMaxFlowTwoWayAlgorithm<String> maxFlowTwoWayAlgorithm1 = new NetworkMaxFlowTwoWayAlgorithm<>();
        maxFlowTwoWayAlgorithm1.insert("S", "V1", 20);
        maxFlowTwoWayAlgorithm1.insert("S", "V2", 15);

        maxFlowTwoWayAlgorithm1.insert("V1", "V2", 18);
        maxFlowTwoWayAlgorithm1.insert("V2", "V3", 10);
        maxFlowTwoWayAlgorithm1.insert("V3", "T", 12);
        maxFlowTwoWayAlgorithm1.insert("V1", "T", 8);
        maxFlowTwoWayAlgorithm1.insert("V2", "T", 3);

        maxFlowTwoWayAlgorithm1.getMaxFlowAndAugmentingPath("S", "T");


        NetworkMaxFlowTwoWayAlgorithm<String> maxFlowTwoWayAlgorithm2 = new NetworkMaxFlowTwoWayAlgorithm<>();


        maxFlowTwoWayAlgorithm2.insert("V0", "V1", 7);
        maxFlowTwoWayAlgorithm2.insert("V0", "V2", 2);
        maxFlowTwoWayAlgorithm2.insert("V0", "V3", 1);
        maxFlowTwoWayAlgorithm2.insert("V0", "V20", 10);
        maxFlowTwoWayAlgorithm2.insert("V0", "V21", 9);

        maxFlowTwoWayAlgorithm2.insert("V1", "V0", 7);
        maxFlowTwoWayAlgorithm2.insert("V2", "V0", 2);
        maxFlowTwoWayAlgorithm2.insert("V3", "V0", 1);

        maxFlowTwoWayAlgorithm2.insert("V1", "V4", 2);
        maxFlowTwoWayAlgorithm2.insert("V1", "V5", 4);
//
        maxFlowTwoWayAlgorithm2.insert("V4", "V1", 2);
        maxFlowTwoWayAlgorithm2.insert("V5", "V1", 4);

        maxFlowTwoWayAlgorithm2.insert("V2", "V5", 5);
        maxFlowTwoWayAlgorithm2.insert("V2", "V6", 6);
//
        maxFlowTwoWayAlgorithm2.insert("V5", "V2", 5);
        maxFlowTwoWayAlgorithm2.insert("V6", "V2", 6);

        maxFlowTwoWayAlgorithm2.insert("V3", "V4", 4);
        maxFlowTwoWayAlgorithm2.insert("V3", "V8", 8);
        maxFlowTwoWayAlgorithm2.insert("V3", "V11", 8);
////
        maxFlowTwoWayAlgorithm2.insert("V4", "V3", 4);
        maxFlowTwoWayAlgorithm2.insert("V8", "V3", 8);
        maxFlowTwoWayAlgorithm2.insert("V11", "V3", 8);
//
//
        maxFlowTwoWayAlgorithm2.insert("V11", "V12", 8);
        maxFlowTwoWayAlgorithm2.insert("V12", "V13", 8);
        maxFlowTwoWayAlgorithm2.insert("V13", "V14", 8);

        maxFlowTwoWayAlgorithm2.insert("V4", "V7", 7);
        maxFlowTwoWayAlgorithm2.insert("V4", "V8", 6);
//
        maxFlowTwoWayAlgorithm2.insert("V7", "V4", 7);
        maxFlowTwoWayAlgorithm2.insert("V8", "V4", 1);

        maxFlowTwoWayAlgorithm2.insert("V5", "V7", 3);
        maxFlowTwoWayAlgorithm2.insert("V5", "V9", 3);
        maxFlowTwoWayAlgorithm2.insert("V5", "V6", 8);

        maxFlowTwoWayAlgorithm2.insert("V7", "V5", 3);
        maxFlowTwoWayAlgorithm2.insert("V9", "V5", 3);
        maxFlowTwoWayAlgorithm2.insert("V6", "V5", 8);

        maxFlowTwoWayAlgorithm2.insert("V6", "V9", 3);
        maxFlowTwoWayAlgorithm2.insert("V9", "V6", 3);
//
        maxFlowTwoWayAlgorithm2.insert("V7", "V10", 1);
        maxFlowTwoWayAlgorithm2.insert("V10", "V7", 1);


        maxFlowTwoWayAlgorithm2.insert("V8", "V10", 6);
        maxFlowTwoWayAlgorithm2.insert("V9", "V10", 4);
//
        maxFlowTwoWayAlgorithm2.insert("V10", "V8", 3);
        maxFlowTwoWayAlgorithm2.insert("V10", "V9", 4);
        maxFlowTwoWayAlgorithm2.getMaxFlowAndAugmentingPath("V0", "V10");


        NetworkMaxFlowTwoWayAlgorithm<String> maxFlowTwoWayAlgorithm3 = new NetworkMaxFlowTwoWayAlgorithm<>();
        maxFlowTwoWayAlgorithm3.insert("V0", "V1", 5);
        maxFlowTwoWayAlgorithm3.insert("V0", "V2", 10);
        maxFlowTwoWayAlgorithm3.insert("V0", "V3", 5);

        maxFlowTwoWayAlgorithm3.insert("V1", "V0", 5);
        maxFlowTwoWayAlgorithm3.insert("V2", "V0", 10);
        maxFlowTwoWayAlgorithm3.insert("V3", "V0", 5);

        maxFlowTwoWayAlgorithm3.insert("V1", "V4", 10);
        maxFlowTwoWayAlgorithm3.insert("V4", "V1", 10);


        maxFlowTwoWayAlgorithm3.insert("V2", "V1", 15);
        maxFlowTwoWayAlgorithm3.insert("V2", "V5", 20);

        maxFlowTwoWayAlgorithm3.insert("V1", "V2", 15);
        maxFlowTwoWayAlgorithm3.insert("V5", "V2", 20);

        maxFlowTwoWayAlgorithm3.insert("V3", "V6", 10);

        maxFlowTwoWayAlgorithm3.insert("V6", "V3", 10);

        maxFlowTwoWayAlgorithm3.insert("V4", "V7", 10);
        maxFlowTwoWayAlgorithm3.insert("V4", "V5", 25);

        maxFlowTwoWayAlgorithm3.insert("V7", "V4", 10);
        maxFlowTwoWayAlgorithm3.insert("V5", "V4", 25);

        maxFlowTwoWayAlgorithm3.insert("V5", "V8", 30);
        maxFlowTwoWayAlgorithm3.insert("V5", "V3", 5);

        maxFlowTwoWayAlgorithm3.insert("V8", "V5", 30);
        maxFlowTwoWayAlgorithm3.insert("V3", "V5", 5);

        maxFlowTwoWayAlgorithm3.insert("V6", "V8", 5);
        maxFlowTwoWayAlgorithm3.insert("V6", "V9", 10);

        maxFlowTwoWayAlgorithm3.insert("V8", "V6", 5);
        maxFlowTwoWayAlgorithm3.insert("V9", "V6", 10);

        maxFlowTwoWayAlgorithm3.insert("V7", "V10", 5);
        maxFlowTwoWayAlgorithm3.insert("V10", "V7", 5);


        maxFlowTwoWayAlgorithm3.insert("V8", "V10", 15);
        maxFlowTwoWayAlgorithm3.insert("V8", "V4", 15);
        maxFlowTwoWayAlgorithm3.insert("V8", "V9", 5);

        maxFlowTwoWayAlgorithm3.insert("V10", "V8", 15);
        maxFlowTwoWayAlgorithm3.insert("V4", "V8", 15);
        maxFlowTwoWayAlgorithm3.insert("V9", "V8", 5);

        maxFlowTwoWayAlgorithm3.insert("V9", "V10", 10);
        maxFlowTwoWayAlgorithm3.insert("V10", "V9", 10);
        maxFlowTwoWayAlgorithm3.getMaxFlowAndAugmentingPath("V0", "V10");

    }

    private void SortListEdge(Node<E> gNode) { // sort the edges in node based on parent rate.
        sort<E> eSort = new sort<>();
        eSort.QuickSortTraditionalMethodNonRecursion(gNode.Edges);
    }

    private void insert(E StartName, E destinationName, int distance) {

        Node<E> destination, StartNode;
        destination = this.GraphNodes.get(destinationName);
        StartNode = this.GraphNodes.get(StartName);

        if (destination == null) { // if the destination is not exist
            destination = new Node<>(destinationName);
            this.GraphNodes.put(destinationName, destination); // add destination to graph node
        }

        if (StartNode == null) { // if start is not exist

            Node<E> newNode = new Node<>(StartName);
            StartNode = newNode;
            this.GraphNodes.put(StartName, newNode); // add create start

        }
        StartNode.Edges.add(new Edge<>(StartNode, destination, distance)); // add the edge to the start node.
        destination.parentRate++;

        SortListEdge(StartNode);


    }



    private List<Object[]> MaxFlow(E StartName, E DestinationName) {
        Stack<Edge<E>> edgesInPath = new Stack<>();  // stack to contain the Edges that in the current working branch
        Stack<Node<E>> NodeInPath = new Stack<>(); // stack to contain EdgesInPath


        Node<E> StartNode = GraphNodes.get(StartName), desNode = GraphNodes.get(DestinationName), currentNode;
        Edge<E> fullEdge;
        final int[] newFlowCap = {Integer.MAX_VALUE};
        int total = 0, count ;

        List<Object[]> PathCollection = new LinkedList<>();


        class LocalMaintain { // local class to maintain code

            void FlowMaintain() {
                newFlowCap[0] = Integer.MAX_VALUE; // initial value

                // check the smallest in the edgesInPath, if edgesInPath is empty ==> new flow is INF
                for (Edge<E> edge : edgesInPath) {
                    if (edge.getCapability() < newFlowCap[0]) {
                        newFlowCap[0] = edge.getCapability();
                    }
                }
            }

            void updateInPathEdge(Edge<E> fullEdge) {
                while (true) { // pop until it meet the full edge
                    if (!edgesInPath.peek().equals(fullEdge)) {
                        edgesInPath.pop();
                    } else { // if it meet the full edge => pop and break.
                        edgesInPath.pop();
                        break;
                    }
                }
            }


            void UpdateNode(Node<E> condition) {

                while (!NodeInPath.peek().equals(condition)) { // pop until meet the condition
                    NodeInPath.pop();
                }
            }


            void UpdateDeadEnd() {
                for (Node<E> node : NodeInPath) { // update deadNode
                    if (!node.equals(desNode)) node.updateDeadEnd();
                }
            }
        }


        LocalMaintain maintain = new LocalMaintain();

        if (StartNode != null && desNode != null) {
            NodeInPath.push(StartNode); // push the start to the stack


            while (!NodeInPath.isEmpty()) {

                currentNode = NodeInPath.peek();// get the current node but not pop it out.

                if (currentNode.equals(desNode)) { // if the current node is the destination node

                    // update flow and check full node in edgesInPath
                    fullEdge = this.UpdateFlowAndCheckCap(edgesInPath, newFlowCap[0]);
                    // display
                    displayPath(NodeInPath, newFlowCap[0], desNode);
                    // add the collection of nodes to the Path collection
                    PathCollection.add(NodeInPath.toArray());
                    total += newFlowCap[0]; // accumulate the total
                    maintain.UpdateDeadEnd(); // update deadEnd if it exist

                    if (fullEdge != null) {

                        // pop the edge in edgesInPath until the full edge and pop the full edge as well
                        maintain.updateInPathEdge(fullEdge);

                        // - pop until meet the condition of the fullEdge's previous or the top Edge's destination in the edgesInPath  ==> stop.
                        if (!edgesInPath.isEmpty()) {
                            maintain.UpdateNode(edgesInPath.peek().destination);
                        } else {
                            maintain.UpdateNode(fullEdge.previous);
                        }
                    }
                    // re-update flow
                    maintain.FlowMaintain();

                } else { // if current node is not the destination node
                    count = 0;
                    // find an available way.
                    for (Edge<E> eEdge : currentNode.Edges) {
                        if (!currentNode.equals(desNode) && !currentNode.deadEnd && eEdge.getCapability() > 0
                                && !eEdge.destination.getDeadEndStatus()&& !NodeInPath.contains(eEdge.destination)) {
                            // if a way is satisfied all these condition ==> push to NodeInPath and push its following edge to edgesInPath then break
                            newFlowCap[0] = Math.min(newFlowCap[0], eEdge.getCapability());
                            NodeInPath.push(eEdge.destination);
                            edgesInPath.push(eEdge);
                            count++;
                            break;

                        }
                    }
                    if (count == 0) { // if there is no way found ==> that node is the deadEnd
                        currentNode.deadEnd = true; // update that node is the deadEnd
                        NodeInPath.pop(); // pop that node out the path
                        if (NodeInPath.size() >= 1) edgesInPath.pop(); // pop its following edge out
                        maintain.FlowMaintain(); // re-update flow

                    }
                }

            }
            System.out.printf("\n Total Flow : %d \n\n", total);
        }
        return PathCollection;
    }


    public void getMaxFlowAndAugmentingPath(E StartName, E DestinationName) {
        List<Object[]> path = MaxFlow(StartName, DestinationName);

        // print all collections of path
        System.out.print("[");
        for (Object[] collection : path) {
            System.out.print(Arrays.toString(collection));
            System.out.print(", ");
        }
        System.out.print("]\n\n\n");
    }

    // Get and print the path
    private void displayPath(Stack<Node<E>> NodeInPath, int flow, Node<E> desNode) {
        StringBuilder displayPath = new StringBuilder();

        for (Node<E> node : NodeInPath) {
            displayPath.append(node.item); // add that destination
            if (!node.equals(desNode))
                displayPath.append("--->\t"); // add the arrow if that node is node the destination.
        }
        System.out.println(displayPath.append(" <--> Flow : ").append(flow));
    }

    private Edge<E> UpdateFlowAndCheckCap(Stack<Edge<E>> PathCollection, int newWeight) {
        Edge<E> fullEdge = null;
        int i = 0;
        for (Edge<E> edge : PathCollection) {
            edge.flow = Math.min(edge.flow + newWeight, edge.Capability); // update the flow

            if (edge.Capability - edge.flow == 0 && i < 1) { // get the first full Node.
                i++;
                fullEdge = edge;
            }
        }
        return fullEdge;
    }
}
