package DynamicProgramming.MaximumNetworkFlow;

import java.util.*;

public class MyOwnMaxFlowAlgorithm<E> {

    HashMap<E, Node<E>> GraphNodes = new HashMap<>();


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
            newNode.Edges.add(new Edge<>(null, destination, distance)); // add the edge from start to destination

        } else {
            StartNode.Edges.add(new Edge<>(StartNode, destination, distance)); // add the edge to the start node.
        }

        SortListEdge(StartNode);


    }




    /*
       - using BFS to construct level for graph.
       -

     */

    private void constructLevel(E StartName, E DestinationName) {

        if (!StartName.equals(DestinationName)) {

            Node<E> StartNode = GraphNodes.get(StartName), destinationNode;
            Queue<Node<E>> toVisit = new LinkedList<>();
            Set<E> visited = new LinkedHashSet<>();
            int len;
            Edge<E> edge;

            if (StartNode != null) { // if start node is exist
                toVisit.add(StartNode); // add start node to the toVisit queue
                StartNode.level = 0;

                while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                    StartNode = toVisit.remove(); // get the start node
                    len = StartNode.Edges.size();

                    if (!visited.contains(StartNode.item)) { // if that node is not in the viSited
                        for (int i = 0; i < len; i++) { // ==> check all its edges and update the distance

                            edge = StartNode.Edges.get(i);
                            destinationNode = edge.destination;
                            if (destinationNode.level != StartNode.level) destinationNode.level = StartNode.level + 1;

                            if (!toVisit.contains(destinationNode))
                                toVisit.add(destinationNode); // if the destination not in the Queue ==> add to the queue.
                        }
                    }
                    visited.add(StartNode.item); // add the Start node as visited

                }
            }
        }
    }



    /*
        - put nodes to stack of node, and push it's follow node to the stack of the toVisitEdges
        ==> the node and the stack has the same location in these 2 stack ==>  we pop 1 node => pop 1 stack
        and 2 of those are has a comparable.

        - each time, we pop a node out of the stack, we check the edges collection of that node if it is satisfied
        with the condition (which is explained in the code comment below).

        - whenever we pop a node out of the stack ==> pop an edge ==> that edge is the following edge of the node
        and point at that node.

        - each time we pop a node ==> pop it's following edge, then check and

        -
     */

    public List<List<String>> getOptimumPathFlow(E StartName, E DestinationName) {
        if (!StartName.equals(DestinationName)) {

            this.constructLevel(StartName, DestinationName); // construct the level to make sure the level increase from start to destination.

            Stack<Node<E>> toVisitNode = new Stack<>(); // stack to contain the Node
            Stack<Edge<E>> toVisitEdges = new Stack<>(); // stack to contain the Edges
            Stack<Edge<E>> edgesInPath = new Stack<>();  // stack to contain the Edges that in the current working branch

            Node<E> StartNode = GraphNodes.get(StartName), desNode = GraphNodes.get(DestinationName), currentNode;
            Edge<E> followEdge, fullEdge;
            int newFlowCap = Integer.MAX_VALUE, remainCapacity, total = 0;

            List<List<String>> PathCollection = new LinkedList<>();

            class local {
                void resetEdge() {
                    if (!toVisitNode.isEmpty()) {
                        resetEdges(toVisitEdges, toVisitNode.peek().item);
                    }
                    if (!toVisitEdges.isEmpty() && !(toVisitEdges.peek().previous == null)) {
                        resetEdges(edgesInPath, toVisitEdges.peek().previous.item);
                    } else {
                        edgesInPath.clear();
                    }
                }
            }
            local EdgeMaintain = new local();
            if (StartNode != null && desNode != null) {
                toVisitNode.push(StartNode);

                while (!toVisitNode.isEmpty()) {

                    currentNode = toVisitNode.pop();// get the current node.

                    // in case the the node run in the wrong way
                    // ==> the node is the leaf but it is not the destination or the path is run further than the destination
                    if ((currentNode.Edges.isEmpty() && !currentNode.equals(desNode)) || StartNode.level > desNode.level) {
                        EdgeMaintain.resetEdge();

                    } else {

                        if (!toVisitEdges.isEmpty()) {
                            followEdge = toVisitEdges.pop(); // get the edge that point to the current Node.

                            // the rest capacity that can contain the flow == capacity - the current flow in the link.
                            remainCapacity = followEdge.Capability - followEdge.weight;

                            // find the bottleneck in the graph
                            newFlowCap = Math.min(newFlowCap, remainCapacity);
                            edgesInPath.push(followEdge);

                            if (currentNode.item.equals(DestinationName)) {
                                fullEdge = this.UpdateFlowAndCheckCap(edgesInPath, newFlowCap);
                                PathCollection.add(getPathCollection(StartName, edgesInPath, newFlowCap));
                                total += newFlowCap;
                                if (fullEdge != null) {

                                    this.resetNode(toVisitNode, fullEdge.destination.level);
                                    EdgeMaintain.resetEdge();

                                } else {
                                    this.resetEdges(edgesInPath, toVisitEdges.peek().previous.item);
                                }

                                newFlowCap = Integer.MAX_VALUE;
                            }
                        }


                        // add item of the current node to stack
                        for (Edge<E> edge : currentNode.Edges) {
                    /*
                        - to make sure that node always run from smaller level to higher level, and the Destination node always
                        >= the current node
                        - and in case, the edge capability is > 0, in other words, it has ability to
                        contain a mount of flow > 0

                        ==> push that node and edge to stack.


                        && edge.destination.level <= desNode.level
                     */
                            // get the edge that has the destination satisfied with these condition.
                            if (currentNode.level <= edge.destination.level && edge.Capability - edge.weight != 0) {

                                toVisitNode.push(edge.destination);
                                toVisitEdges.push(edge);

                            } else if (edge.Capability - edge.weight == 0) {
                                EdgeMaintain.resetEdge();
                            }
                        }
                    }
                }
                System.out.printf("\n Total Flow : %d \n\n", total);
                return PathCollection;
            }
        }
        return null;
    }


    private void maintainEdge(Stack<Edge<E>> EdgeCollection, E currentPrevious) {

    }

    private void resetEdges(Stack<Edge<E>> EdgeCollection, E currentPrevious) {

        // pop item in stack until meet the condition
        while (!EdgeCollection.isEmpty()) {
            // delete until found out an edge that has the destination reach the condition.
            if (EdgeCollection.peek().destination.item.equals(currentPrevious)) {
                break;
            } else {
                EdgeCollection.pop();
            }
        }
    }


    private void resetNode(Stack<Node<E>> stackNode, int conditionLevel) {
        // pop out the item until the condition wrong
        while (!stackNode.isEmpty() && stackNode.peek().level > conditionLevel) {
            stackNode.pop();
        }
    }

    private List<String> getPathCollection(E StartName, Stack<Edge<E>> edges, int flow) {
        StringBuilder Path = new StringBuilder("" + StartName + "--->\t");
        List<String> result = new LinkedList<>();
        result.add((String) StartName);
        for (Edge<E> edge : edges) {
            result.add((String) edge.destination.item);
            Path.append(edge.destination.item).append("--->\t");
        }
        result.add("flow : " + flow);
        System.out.println(Path.append(flow));
        return result;
    }

    private Edge<E> UpdateFlowAndCheckCap(Stack<Edge<E>> PathCollection, int newWeight) {
        Edge<E> fullEdge = null;
        int i = 0;
        for (Edge<E> a : PathCollection) {
            a.weight = Math.min(a.weight + newWeight, a.Capability);
            if (a.Capability - a.weight == 0 && i < 1) {
                i++;
                fullEdge = a;
            }
        }
        return fullEdge;
    }

    public static void main(String[] args) {
        MyOwnMaxFlowAlgorithm<String> myOwnMaxFlowAlgorithm = new MyOwnMaxFlowAlgorithm<>();


        myOwnMaxFlowAlgorithm.insert("V0", "V1", 7);
        myOwnMaxFlowAlgorithm.insert("V0", "V2", 2);
        myOwnMaxFlowAlgorithm.insert("V0", "V3", 1);

        myOwnMaxFlowAlgorithm.insert("V1", "V4", 2);
        myOwnMaxFlowAlgorithm.insert("V1", "V5", 4);

        myOwnMaxFlowAlgorithm.insert("V2", "V5", 5);
        myOwnMaxFlowAlgorithm.insert("V2", "V6", 6);

        myOwnMaxFlowAlgorithm.insert("V3", "V4", 4);
        myOwnMaxFlowAlgorithm.insert("V3", "V8", 8);
        myOwnMaxFlowAlgorithm.insert("V3", "V11", 8);
        myOwnMaxFlowAlgorithm.insert("V11", "V12", 8);
        myOwnMaxFlowAlgorithm.insert("V12", "V13", 8);
        myOwnMaxFlowAlgorithm.insert("V13", "V14", 8);


        myOwnMaxFlowAlgorithm.insert("V4", "V7", 7);
        myOwnMaxFlowAlgorithm.insert("V4", "V8", 1);

        myOwnMaxFlowAlgorithm.insert("V5", "V7", 3);
        myOwnMaxFlowAlgorithm.insert("V5", "V9", 3);
        myOwnMaxFlowAlgorithm.insert("V5", "V6", 8);

        myOwnMaxFlowAlgorithm.insert("V6", "V9", 3);

        myOwnMaxFlowAlgorithm.insert("V7", "V10", 1);

        myOwnMaxFlowAlgorithm.insert("V8", "V10", 3);
        myOwnMaxFlowAlgorithm.insert("V9", "V10", 4);

        System.out.println(myOwnMaxFlowAlgorithm.getOptimumPathFlow("V0", "V10"));
    }
}
