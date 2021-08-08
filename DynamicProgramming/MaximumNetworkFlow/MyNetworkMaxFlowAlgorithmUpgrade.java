package DynamicProgramming.MaximumNetworkFlow;


import java.util.*;

public class MyNetworkMaxFlowAlgorithmUpgrade<E> {


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
            int len;
            Edge<E> edge;

            if (StartNode != null) { // if start node is exist
                toVisit.add(StartNode); // add start node to the toVisit queue
                StartNode.level = 0;

                while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                    StartNode = toVisit.remove(); // get the start node
                    len = StartNode.Edges.size();


                    for (int i = 0; i < len; i++) { // ==> check all its edges and update the level

                        edge = StartNode.Edges.get(i);
                        destinationNode = edge.destination;

                        if (destinationNode.level == -1 || StartNode.level < destinationNode.level) {
                            destinationNode.level = StartNode.level + 1;


                            if (!toVisit.contains(destinationNode))
                                toVisit.add(destinationNode); // if the destination not in the Queue ==> add to the queue.
                        }
                    }
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
            Stack<Node<E>> visitedNodeInPath = new Stack<>();

            Node<E> StartNode = GraphNodes.get(StartName), desNode = GraphNodes.get(DestinationName), currentNode;
            Edge<E> followEdge, fullEdge;
            int newFlowCap = Integer.MAX_VALUE, remainCapacity, total = 0;

            List<List<String>> PathCollection = new LinkedList<>();

            class local {
                void resetEdgeStacks() {
                    if (!toVisitNode.isEmpty()) {
                        resetEdges(toVisitEdges, toVisitNode.peek().item);
                    }
                    if (!toVisitEdges.isEmpty() && !(toVisitEdges.peek().previous == null)) {
                        resetEdges(edgesInPath, toVisitEdges.peek().previous.item);
                    } else {
                        edgesInPath.clear();
                    }
                }

                void resetVisited (){
                    if (edgesInPath.isEmpty()){
                        visitedNodeInPath.clear();
                        visitedNodeInPath.push(StartNode);
                    }else {
                        resetNodeVisited(visitedNodeInPath, edgesInPath.peek().destination);
                    }
                }
            }

            local Maintain = new local();
            if (StartNode != null && desNode != null) {
                toVisitNode.push(StartNode);


                while (!toVisitNode.isEmpty()) {

                   currentNode = toVisitNode.pop();// get the current node.

                    // in case the the node run in the wrong way
                    // ==> the node is the leaf but it is not the destination or the path is run further than the destination
                    if ((((currentNode.Edges.size() == 1 && currentNode.Edges.get(0).destination == visitedNodeInPath.peek()) ||currentNode.Edges.isEmpty()) && !currentNode.equals(desNode)) || StartNode.level > desNode.level) {
                        Maintain.resetEdgeStacks();
                        Maintain.resetVisited();



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

                                    this.resetNode(toVisitNode, fullEdge.destination);
                                    Maintain.resetEdgeStacks();


                                } else {
                                    this.resetEdges(edgesInPath, toVisitEdges.peek().previous.item);

                                }
                                Maintain.resetVisited();
                                newFlowCap = Integer.MAX_VALUE;
                            }
                        }


                        if (!currentNode.equals(desNode)) {
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
                                if (currentNode.level <= edge.destination.level && !toVisitNode.contains(edge.destination)
                                        && edge.Capability - edge.weight != 0 && !visitedNodeInPath.contains(edge.destination)) {

                                    toVisitNode.push(edge.destination);
                                    toVisitEdges.push(edge);

                                } else if (edge.Capability - edge.weight == 0) {
                                    Maintain.resetEdgeStacks();
                                    Maintain.resetVisited();
                                }
                            }

                            visitedNodeInPath.push(currentNode);
                        }
                    }
                }
                System.out.printf("\n Total Flow : %d \n\n", total);
                System.out.println("" + PathCollection);
                return PathCollection;
            }
        }
        return null;
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


    private void resetNode(Stack<Node<E>> stackNode, Node<E> conditionLevel) {
        // pop out the item until the condition wrong
        while (!stackNode.isEmpty() && (stackNode.peek().level > conditionLevel.level)) {
            stackNode.pop();
        }
    }

    private void resetNodeVisited(Stack<Node<E>> stackNode, Node<E> condition) {
        // pop out the item until the condition wrong
        while (!stackNode.isEmpty()) {
            // delete until found out an edge that has the destination reach the condition.
            if (stackNode.peek().equals(condition)) {
                break;
            } else {
                stackNode.pop();
            }
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
//        MyNetworkMaxFlowAlgorithmUpgrade<String> myNetworkMaxFlowAlgorithmUpgrade = new MyNetworkMaxFlowAlgorithmUpgrade<>();
//
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V0", "V1", 7);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V0", "V2", 2);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V0", "V3", 1);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V1", "V0", 7);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V2", "V0", 2);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V3", "V0", 1);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V1", "V4", 2);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V1", "V5", 4);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V4", "V1", 2);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V5", "V1", 4);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V2", "V5", 5);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V2", "V6", 6);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V5", "V2", 5);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V6", "V2", 6);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V3", "V4", 4);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V3", "V8", 8);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V3", "V11", 8);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V4", "V3", 4);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V8", "V3", 8);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V11", "V3", 8);
//
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V11", "V12", 8);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V12", "V13", 8);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V13", "V14", 8);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V4", "V7", 7);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V4", "V8", 1);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V7", "V4", 7);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V8", "V4", 1);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V5", "V7", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V5", "V9", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V5", "V6", 8);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V7", "V5", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V9", "V5", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V6", "V5", 8);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V6", "V9", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V9", "V6", 3);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V7", "V10", 1);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V10", "V7", 1);
//
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V8", "V10", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V9", "V10", 4);
//
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V10", "V8", 3);
//        myNetworkMaxFlowAlgorithmUpgrade.insert("V10", "V9", 4);
//
//        myNetworkMaxFlowAlgorithmUpgrade.getOptimumPathFlow("V0", "V10");





        MyNetworkMaxFlowAlgorithmUpgrade<String> MaxFlowAlgorithmUpgrade = new MyNetworkMaxFlowAlgorithmUpgrade<>();
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
        MaxFlowAlgorithmUpgrade.insert("V8", "V4", 15);
        MaxFlowAlgorithmUpgrade.insert("V8", "V9", 5);

        MaxFlowAlgorithmUpgrade.insert("V9", "V10", 10);

        MaxFlowAlgorithmUpgrade.getOptimumPathFlow("V0", "V10");


    }


}
