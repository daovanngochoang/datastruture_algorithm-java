package DynamicProgramming.MaximumNetworkFlow.MainSource;


import java.util.*;

public class NetworkFlowAlgorithm<E> {


    HashMap<E, Node<E>> GraphNodes = new HashMap<>();

    public static void main(String[] args) {

        NetworkFlowAlgorithm<String> MAxF = new NetworkFlowAlgorithm<>();
        MAxF.insert("S", "V1", 25);
        MAxF.insert("S", "V2", 30);

        MAxF.insert("V1", "V4", 15);
        MAxF.insert("V1", "V3", 10);

        MAxF.insert("V2", "V3", 20);
        MAxF.insert("V3", "V5", 120);
        MAxF.insert("V4", "V7", 12);

        MAxF.insert("V5", "V6", 15);

        MAxF.insert("V6", "V8", 12);
        MAxF.insert("V6", "V7", 18);


        MAxF.insert("V7", "T", 8);
        MAxF.insert("V8", "T", 7);

        MAxF.MaxFlow("S", "T");


        NetworkFlowAlgorithm<String> NetworkMaxFlowAlgorithm2 = new NetworkFlowAlgorithm<>();
        NetworkMaxFlowAlgorithm2.insert("S", "V1", 20);
        NetworkMaxFlowAlgorithm2.insert("S", "V2", 15);

        NetworkMaxFlowAlgorithm2.insert("V1", "V2", 18);
        NetworkMaxFlowAlgorithm2.insert("V2", "V3", 10);
        NetworkMaxFlowAlgorithm2.insert("V3", "T", 12);
        NetworkMaxFlowAlgorithm2.insert("V1", "T", 8);
        NetworkMaxFlowAlgorithm2.insert("V2", "T", 3);

        NetworkMaxFlowAlgorithm2.MaxFlow("S", "T");


        NetworkFlowAlgorithm<String> NetworkMaxFlowAlgorithm = new NetworkFlowAlgorithm<>();


        NetworkMaxFlowAlgorithm.insert("V0", "V1", 7);
        NetworkMaxFlowAlgorithm.insert("V0", "V2", 2);
        NetworkMaxFlowAlgorithm.insert("V0", "V3", 1);


        NetworkMaxFlowAlgorithm.insert("V1", "V4", 2);
        NetworkMaxFlowAlgorithm.insert("V1", "V5", 4);


        NetworkMaxFlowAlgorithm.insert("V2", "V5", 5);
        NetworkMaxFlowAlgorithm.insert("V2", "V6", 6);

        NetworkMaxFlowAlgorithm.insert("V3", "V4", 4);
        NetworkMaxFlowAlgorithm.insert("V3", "V8", 8);
        NetworkMaxFlowAlgorithm.insert("V3", "V11", 8);


        NetworkMaxFlowAlgorithm.insert("V4", "V7", 7);
        NetworkMaxFlowAlgorithm.insert("V4", "V8", 1);

        NetworkMaxFlowAlgorithm.insert("V5", "V7", 3);
        NetworkMaxFlowAlgorithm.insert("V5", "V9", 3);
        NetworkMaxFlowAlgorithm.insert("V5", "V6", 8);

        NetworkMaxFlowAlgorithm.insert("V6", "V9", 3);

        NetworkMaxFlowAlgorithm.insert("V7", "V10", 1);


        NetworkMaxFlowAlgorithm.insert("V8", "V10", 6);
        NetworkMaxFlowAlgorithm.insert("V9", "V10", 4);
        NetworkMaxFlowAlgorithm.MaxFlow("V0", "V10");


        NetworkFlowAlgorithm<String> MaxFlowAlgorithmUpgrade = new NetworkFlowAlgorithm<>();
        MaxFlowAlgorithmUpgrade.insert("V0", "V1", 5);
        MaxFlowAlgorithmUpgrade.insert("V0", "V2", 10);
        MaxFlowAlgorithmUpgrade.insert("V0", "V3", 5);


        MaxFlowAlgorithmUpgrade.insert("V1", "V4", 10);


        MaxFlowAlgorithmUpgrade.insert("V2", "V1", 15);
        MaxFlowAlgorithmUpgrade.insert("V2", "V5", 20);


        MaxFlowAlgorithmUpgrade.insert("V3", "V6", 10);


        MaxFlowAlgorithmUpgrade.insert("V4", "V7", 10);
        MaxFlowAlgorithmUpgrade.insert("V4", "V5", 25);

        MaxFlowAlgorithmUpgrade.insert("V5", "V8", 30);
        MaxFlowAlgorithmUpgrade.insert("V5", "V3", 5);


        MaxFlowAlgorithmUpgrade.insert("V6", "V8", 5);
        MaxFlowAlgorithmUpgrade.insert("V6", "V9", 10);


        MaxFlowAlgorithmUpgrade.insert("V7", "V10", 5);


        MaxFlowAlgorithmUpgrade.insert("V8", "V10", 15);
        MaxFlowAlgorithmUpgrade.insert("V8", "V9", 5);


        MaxFlowAlgorithmUpgrade.insert("V9", "V10", 10);
        MaxFlowAlgorithmUpgrade.MaxFlow("V0", "V10");

    }

    public void SortListEdge(Node<E> gNode) {
        sort<E> eSort = new sort<>();
        eSort.QuickSortTraditionalMethodNonRecursion(gNode.Edges);
    }

    public void insert(E StartName, E destinationName, int distance) {

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

        SortListEdge(StartNode);


    }

    private void constructLevel(E StartName, E DestinationName) {

        if (!StartName.equals(DestinationName)) {

            Node<E> StartNode = GraphNodes.get(StartName), currentDestinationNode, desNode = GraphNodes.get(DestinationName), currentNode;
            Queue<Node<E>> toVisit = new LinkedList<>();

            if (StartNode != null) {
                toVisit.add(StartNode); // add start node to the toVisit queue
                StartNode.level = 0;

                while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                    currentNode = toVisit.remove(); // get the start node

                    for (Edge<E> edge : currentNode.Edges) { // ==> check all its edges and update the level

                        currentDestinationNode = edge.destination;

                        if (!currentNode.item.equals(DestinationName)) {
                            if (currentDestinationNode.level == -1 || currentNode.level <= currentDestinationNode.level || currentDestinationNode.equals(desNode)) {
                                currentDestinationNode.level = Math.max(currentDestinationNode.level, currentNode.level + 1);
                                toVisit.add(currentDestinationNode); // if the destination not in the Queue ==> add to the queue.
                            }
                        }
                    }
                }

            }
        }
    }

    private int checkCapability(Stack<Edge<E>> stack) {
        int remainCapability = Integer.MAX_VALUE;
        for (Edge<E> edge : stack) {
            if (edge.getCapability() < remainCapability) {
                remainCapability = edge.getCapability();
            }
        }
        return remainCapability;
    }

    public void MaxFlow(E StartName, E DestinationName) {
        Stack<Edge<E>> edgesInPath = new Stack<>();  // stack to contain the Edges that in the current working branch
        Stack<Node<E>> visitedNodeInPath = new Stack<>();


        Node<E> StartNode = GraphNodes.get(StartName), desNode = GraphNodes.get(DestinationName), currentNode;
        Edge<E> fullEdge;
        final int[] newFlowCap = {Integer.MAX_VALUE};
        int total = 0;

        List<List<String>> PathCollection = new LinkedList<>();


        class LocalMaintain {

            void FlowMaintain() {
                if (edgesInPath.isEmpty()) {
                    newFlowCap[0] = Integer.MAX_VALUE;
                } else {
                    newFlowCap[0] = checkCapability(edgesInPath);
                    if (newFlowCap[0] == 0) {
                        newFlowCap[0] = Integer.MAX_VALUE;
                    }
                }
            }

            void updateInPathEdge(Edge<E> fullEdge) {
                while (true) {
                    if (!edgesInPath.peek().equals(fullEdge)) {
                        edgesInPath.pop();

                    } else if (edgesInPath.peek().equals(fullEdge)) {
                        edgesInPath.pop();
                        break;
                    }
                }
            }


            void UpdateNode(Node<E> condition) {

                while (!visitedNodeInPath.peek().equals(condition)) {
                    visitedNodeInPath.pop();
                }
            }


            void UpdateDeadEnd() {
                for (Node<E> node : visitedNodeInPath) {
                    if (!node.equals(desNode)) node.updateDeadEnd();
                }
            }
        }


        LocalMaintain maintain = new LocalMaintain();

        if (StartNode != null && desNode != null) {
            constructLevel(StartName, DestinationName);
            visitedNodeInPath.push(StartNode);


            while (!visitedNodeInPath.isEmpty()) {
                int count = 0;
                currentNode = visitedNodeInPath.peek();// get the current node.

                if (currentNode.equals(desNode)) {
                    fullEdge = this.UpdateFlowAndCheckCap(edgesInPath, newFlowCap[0]);
                    PathCollection.add(getPathCollection(StartName, edgesInPath, newFlowCap[0]));
                    total += newFlowCap[0];
                    maintain.UpdateDeadEnd();

                    if (fullEdge != null) {

                        maintain.updateInPathEdge(fullEdge);

                        if (!edgesInPath.isEmpty()) {
                            maintain.UpdateNode(edgesInPath.peek().destination);
                        } else {
                            maintain.UpdateNode(fullEdge.previous);
                        }
                    }
                    maintain.FlowMaintain();
                    continue;
                }
                for (Edge<E> eEdge : currentNode.Edges) {
                    if (!currentNode.equals(desNode) && !currentNode.deadEnd && eEdge.getCapability() > 0 && !eEdge.destination.getDeadEndStatus()
                            && !visitedNodeInPath.contains(eEdge.destination) && currentNode.level <= eEdge.destination.level) {

                        newFlowCap[0] = Math.min(newFlowCap[0], eEdge.getCapability());
                        visitedNodeInPath.push(eEdge.destination);
                        edgesInPath.push(eEdge);
                        count++;
                        break;

                    }
                }
                if (count == 0) {
                    currentNode.deadEnd = true;
                    visitedNodeInPath.pop();
                    if (visitedNodeInPath.size() >= 1) edgesInPath.pop();
                    maintain.FlowMaintain();

                }

            }
            System.out.printf("\n Total Flow : %d \n\n", total);
            System.out.println("" + PathCollection);
        }
    }

    private List<String> getPathCollection(E StartName, Stack<Edge<E>> edges, int flow) {
        StringBuilder Path = new StringBuilder("" + StartName + "--->\t");
        List<String> result = new LinkedList<>();

        result.add("flow : " + flow);
        result.add((String) StartName);

        for (Edge<E> edge : edges) {
            result.add((String) edge.destination.item);
            Path.append(edge.destination.item).append("--->\t");
        }
        System.out.println(Path.append(flow));
        return result;
    }

    private Edge<E> UpdateFlowAndCheckCap(Stack<Edge<E>> PathCollection, int newWeight) {
        Edge<E> fullEdge = null;
        int i = 0;
        for (Edge<E> edge : PathCollection) {
            edge.flow = Math.min(edge.flow + newWeight, edge.Capability);
            if (edge.Capability - edge.flow == 0 && i < 1) {
                i++;
                fullEdge = edge;
            }
        }
        return fullEdge;
    }
}