package DynamicProgramming.MaximumNetworkFlow.Draft;



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

        }
        StartNode.Edges.add(new Edge<>(StartNode, destination, distance)); // add the edge to the start node.

//        SortListEdge(StartNode);


    }



    private void resetEdges (){

        for (Node<E> node: GraphNodes.values()){
            for (Edge<E> edge : node.Edges){
                edge.weight = 0;
            }
        }
    }




    private void resetNode (){
        for (Node<E> node: GraphNodes.values()){
            node.level = -1;
        }
    }





    private void constructLevel(E StartName, E DestinationName) {

        if (!StartName.equals(DestinationName)) {

            Node<E> StartNode = GraphNodes.get(StartName), currentDestinationNode, desNode = GraphNodes.get(DestinationName), currentNode;
            Queue<Node<E>> toVisit = new LinkedList<>();

            if (StartNode != null) {
                resetNode();
                toVisit.add(StartNode); // add start node to the toVisit queue
                StartNode.level = 0;

                while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                    currentNode = toVisit.remove(); // get the start node

                    for (Edge<E> edge : currentNode.Edges) { // ==> check all its edges and update the level

                        currentDestinationNode = edge.destination;

                        if (!currentNode.item.equals(DestinationName)) {
                            if (currentDestinationNode.level == -1 || currentNode.level <= currentDestinationNode.level|| currentDestinationNode.equals(desNode)) {
                                currentDestinationNode.level = Math.max(currentDestinationNode.level, currentNode.level + 1);
                                toVisit.add(currentDestinationNode); // if the destination not in the Queue ==> add to the queue.
                            }
                        }
                    }
                }

            }
        }
    }





    public List<List<String>> getOptimumPathFlow(E StartName, E DestinationName) {

        if (!StartName.equals(DestinationName)) {
            resetEdges(); // reset the edge
            this.constructLevel(StartName, DestinationName); // construct the level to make sure the level increase from start to destination.

            Stack<Node<E>> toVisitNode = new Stack<>(); // stack to contain the Node
            Stack<Edge<E>> toVisitEdges = new Stack<>(); // stack to contain the Edges
            Stack<Edge<E>> edgesInPath = new Stack<>();  // stack to contain the Edges that in the current working branch
            Stack<Node<E>> visitedNodeInPath = new Stack<>();

            Node<E> StartNode = GraphNodes.get(StartName), desNode = GraphNodes.get(DestinationName), currentNode;
            Edge<E> followEdge, fullEdge;
            final int[] newFlowCap = {Integer.MAX_VALUE};
            int remainCapacity;
            int total = 0;


            List<List<String>> PathCollection = new LinkedList<>();

            // local class to contain function to maintain code so we dont need to write them 3 - 4 times.
            class resetMaintain {
                void resetEdgeStacks() { // reset edge stacks
                    if (!toVisitNode.isEmpty()) {
                        popEdges(toVisitEdges, toVisitNode.peek().item);
                    }else {
                        toVisitEdges.clear();
                    }

                    if (!toVisitEdges.isEmpty() && !(toVisitEdges.peek().previous == null)) {
                        popEdges(edgesInPath, toVisitEdges.peek().previous.item);
                    } else {
                        edgesInPath.clear();
                    }
                }

                void resetVisited (){
                    if (edgesInPath.isEmpty()){
                        visitedNodeInPath.clear();
                        visitedNodeInPath.push(StartNode);
                    }else {
                        popNodeVisited(visitedNodeInPath, edgesInPath.peek().destination);
                    }
                }
                void FlowMaintain(){
                    if (edgesInPath.isEmpty()) {
                        newFlowCap[0] = Integer.MAX_VALUE;
                    }else {
                        newFlowCap[0] = checkCapability(edgesInPath);
                        if (newFlowCap[0] == 0){
                            this.resetEdgeStacks();
                            newFlowCap[0] = Integer.MAX_VALUE;
                        }
                    }
                }
            }

            resetMaintain Maintain = new resetMaintain();



            if (StartNode != null && desNode != null) {
                toVisitNode.push(StartNode);


                while (!toVisitNode.isEmpty()) {
                    int count = 0;
                    currentNode = toVisitNode.pop();// get the current node.


                    // in case the the node run in the wrong way
                    // ==> the node is the leaf but it is not the destination or the path is run further than the destination
                    if ((currentNode.level >= desNode.level || ((currentNode.Edges.size() == 1 && !visitedNodeInPath.isEmpty()
                            && visitedNodeInPath.contains(currentNode.Edges.get(0).destination)) || currentNode.Edges.isEmpty())) && !currentNode.equals(desNode)) {

                        Maintain.resetEdgeStacks();
                        Maintain.FlowMaintain();

                    } else {

                        if (!toVisitEdges.isEmpty()) {
                            followEdge = toVisitEdges.pop(); // get the edge that point to the current Node.

                            // the rest capacity that can contain the flow == capacity - the current flow in the link.
                            remainCapacity = followEdge.Capability - followEdge.weight;

                            // find the bottleneck in the graph
                            newFlowCap[0] = Math.min(newFlowCap[0], remainCapacity);
                            edgesInPath.push(followEdge);

                            if (currentNode.item.equals(DestinationName)) {
                                // update and check full edge
                                fullEdge = this.UpdateFlowAndCheckCap(edgesInPath, newFlowCap[0]);
                                PathCollection.add(getPathCollection(StartName, edgesInPath, newFlowCap[0]));
                                total += newFlowCap[0];

                                if (fullEdge != null) {
                                    // pop Node until meet the same level of the fullEdge's destination or it meet the destination
                                    this.popNode(toVisitNode, fullEdge.destination, desNode);
                                    // update Edge stacks after pop Node.
                                    Maintain.resetEdgeStacks();


                                } else {
                                    // if there is no full edge ==> just update the edgesInPath with toVisitEdges
                                    this.popEdges(edgesInPath, toVisitEdges.peek().previous.item);

                                }
                                // also update the visited Node because when ever meet destination, we will run the other
                                // branches
                                Maintain.resetVisited();
                                Maintain.FlowMaintain();
                            }
                        }

                        boolean check = false;

                        if (!currentNode.equals(desNode)) {

                            // add item of the current node to stack
                            for (Edge<E> edge : currentNode.Edges) {

                                // get the edge that has the destination satisfied with these condition.
                                if (currentNode.level < edge.destination.level && edge.Capability - edge.weight != 0 && !visitedNodeInPath.contains(edge.destination)) {
                                    if (currentNode.equals(StartNode) && edge.destination.equals(desNode)){
                                        edgesInPath.push(edge);
                                        PathCollection.add(getPathCollection(StartName, edgesInPath, edge.Capability - edge.weight));
                                        edgesInPath.pop();
                                    }else {
                                        toVisitNode.push(edge.destination);
                                        toVisitEdges.push(edge);
                                        count++;
                                    }

                                } else if (edge.Capability - edge.weight == 0) {
                                    // if we found an edge that has full capability
                                    Maintain.resetEdgeStacks();
                                    Maintain.resetVisited();
                                    Maintain.FlowMaintain();
                                    check = true;
                                }
                            }
                            if (count == 0 && !check){
                                Maintain.resetEdgeStacks();
                                Maintain.FlowMaintain();
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

    private int checkCapability (Stack<Edge<E>> stack){
        int remainCapability = Integer.MAX_VALUE;
        for (Edge<E> edge : stack){
            if (edge.Capability - edge.weight < remainCapability){
                remainCapability = edge.Capability - edge.weight;
            }
        }
        return remainCapability;
    }


    private void popEdges(Stack<Edge<E>> EdgeCollection, E currentPrevious) {

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





    private void popNode(Stack<Node<E>> stackNode, Node<E> condition, Node<E> desNode) {
        // pop out the item until the condition wrong
        // pop out the item until the condition wrong
        while (!stackNode.isEmpty() && (stackNode.peek().level > condition.level )) {
            stackNode.pop();
        }
    }





    private void popNodeVisited(Stack<Node<E>> stackNode, Node<E> condition) {
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

        MyOwnMaxFlowAlgorithm<String> NetworkMaxFlowAlgorithm2 = new MyOwnMaxFlowAlgorithm<>();
        NetworkMaxFlowAlgorithm2.insert("S", "V1", 20);
        NetworkMaxFlowAlgorithm2.insert("S", "V2", 15);

        NetworkMaxFlowAlgorithm2.insert("V1", "V2", 18);
        NetworkMaxFlowAlgorithm2.insert("V2", "V3", 10);
        NetworkMaxFlowAlgorithm2.insert("V3", "T", 12);
        NetworkMaxFlowAlgorithm2.insert("V1", "T", 8);
        NetworkMaxFlowAlgorithm2.insert("V2", "T", 3);

        NetworkMaxFlowAlgorithm2.getOptimumPathFlow("S", "T");








        MyOwnMaxFlowAlgorithm<String> NetworkMaxFlowAlgorithm = new MyOwnMaxFlowAlgorithm<>();


        NetworkMaxFlowAlgorithm.insert("V0", "V1", 7);
        NetworkMaxFlowAlgorithm.insert("V0", "V2", 2);
        NetworkMaxFlowAlgorithm.insert("V0", "V3", 1);
//        NetworkMaxFlowAlgorithm.insert("V0", "V20", 10);
//        NetworkMaxFlowAlgorithm.insert("V0", "V21", 9);

//        NetworkMaxFlowAlgorithm.insert("V1", "V0", 7);
//        NetworkMaxFlowAlgorithm.insert("V2", "V0", 2);
//        NetworkMaxFlowAlgorithm.insert("V3", "V0", 1);

        NetworkMaxFlowAlgorithm.insert("V1", "V4", 2);
        NetworkMaxFlowAlgorithm.insert("V1", "V5", 4);
//
//        NetworkMaxFlowAlgorithm.insert("V4", "V1", 2);
//        NetworkMaxFlowAlgorithm.insert("V5", "V1", 4);

        NetworkMaxFlowAlgorithm.insert("V2", "V5", 5);
        NetworkMaxFlowAlgorithm.insert("V2", "V6", 6);
//
//        NetworkMaxFlowAlgorithm.insert("V5", "V2", 5);
//        NetworkMaxFlowAlgorithm.insert("V6", "V2", 6);

        NetworkMaxFlowAlgorithm.insert("V3", "V4", 4);
        NetworkMaxFlowAlgorithm.insert("V3", "V8", 8);
        NetworkMaxFlowAlgorithm.insert("V3", "V11", 8);
////
//        NetworkMaxFlowAlgorithm.insert("V4", "V3", 4);
//        NetworkMaxFlowAlgorithm.insert("V8", "V3", 8);
//        NetworkMaxFlowAlgorithm.insert("V11", "V3", 8);
//
//
//        NetworkMaxFlowAlgorithm.insert("V11", "V12", 8);
//        NetworkMaxFlowAlgorithm.insert("V12", "V13", 8);
//        NetworkMaxFlowAlgorithm.insert("V13", "V14", 8);

        NetworkMaxFlowAlgorithm.insert("V4", "V7", 7);
        NetworkMaxFlowAlgorithm.insert("V4", "V8", 6);
//
//        NetworkMaxFlowAlgorithm.insert("V7", "V4", 7);
//        NetworkMaxFlowAlgorithm.insert("V8", "V4", 1);

        NetworkMaxFlowAlgorithm.insert("V5", "V7", 3);
        NetworkMaxFlowAlgorithm.insert("V5", "V9", 3);
        NetworkMaxFlowAlgorithm.insert("V5", "V6", 8);

//        NetworkMaxFlowAlgorithm.insert("V7", "V5", 3);
//        NetworkMaxFlowAlgorithm.insert("V9", "V5", 3);
//        NetworkMaxFlowAlgorithm.insert("V6", "V5", 8);

        NetworkMaxFlowAlgorithm.insert("V6", "V9", 3);
//        NetworkMaxFlowAlgorithm.insert("V9", "V6", 3);
//
        NetworkMaxFlowAlgorithm.insert("V7", "V10", 1);
//        NetworkMaxFlowAlgorithm.insert("V10", "V7", 1);


        NetworkMaxFlowAlgorithm.insert("V8", "V10", 6);
        NetworkMaxFlowAlgorithm.insert("V9", "V10", 4);
//
//        NetworkMaxFlowAlgorithm.insert("V10", "V8", 3);
//        NetworkMaxFlowAlgorithm.insert("V10", "V9", 4);


//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V4", "V5");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V10", "V0");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V4", "V10");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V3", "V8");
        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V0", "V10");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V8", "V10");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V9", "V10");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V5", "V10");
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V4", "V2");
//
//        NetworkMaxFlowAlgorithm.getOptimumPathFlow("V4", "V1");


//
//

        MyOwnMaxFlowAlgorithm<String> MaxFlowAlgorithmUpgrade = new MyOwnMaxFlowAlgorithm<>();
        float start1 = System.currentTimeMillis();
        MaxFlowAlgorithmUpgrade.insert("V0", "V1", 5);
        MaxFlowAlgorithmUpgrade.insert("V0", "V2", 10);
        MaxFlowAlgorithmUpgrade.insert("V0", "V3", 5);

        MaxFlowAlgorithmUpgrade.insert("V1", "V0", 5);
        MaxFlowAlgorithmUpgrade.insert("V2", "V0", 10);
        MaxFlowAlgorithmUpgrade.insert("V3", "V0", 5);

        MaxFlowAlgorithmUpgrade.insert("V1", "V4", 10);
        MaxFlowAlgorithmUpgrade.insert("V4", "V1", 10);


        MaxFlowAlgorithmUpgrade.insert("V2", "V1", 15);
        MaxFlowAlgorithmUpgrade.insert("V2", "V5", 20);

        MaxFlowAlgorithmUpgrade.insert("V1", "V2", 15);
        MaxFlowAlgorithmUpgrade.insert("V5", "V2", 20);

        MaxFlowAlgorithmUpgrade.insert("V3", "V6", 10);

        MaxFlowAlgorithmUpgrade.insert("V6", "V3", 10);

        MaxFlowAlgorithmUpgrade.insert("V4", "V7", 10);
        MaxFlowAlgorithmUpgrade.insert("V4", "V5", 25);

        MaxFlowAlgorithmUpgrade.insert("V7", "V4", 10);
        MaxFlowAlgorithmUpgrade.insert("V5", "V4", 25);

        MaxFlowAlgorithmUpgrade.insert("V5", "V8", 30);
        MaxFlowAlgorithmUpgrade.insert("V5", "V3", 5);

        MaxFlowAlgorithmUpgrade.insert("V8", "V5", 30);
        MaxFlowAlgorithmUpgrade.insert("V3", "V5", 5);

        MaxFlowAlgorithmUpgrade.insert("V6", "V8", 5);
        MaxFlowAlgorithmUpgrade.insert("V6", "V9", 10);

        MaxFlowAlgorithmUpgrade.insert("V8", "V6", 5);
        MaxFlowAlgorithmUpgrade.insert("V9", "V6", 10);

        MaxFlowAlgorithmUpgrade.insert("V7", "V10", 5);
        MaxFlowAlgorithmUpgrade.insert("V10", "V7", 5);


        MaxFlowAlgorithmUpgrade.insert("V8", "V10", 15);
        MaxFlowAlgorithmUpgrade.insert("V8", "V4", 15);
        MaxFlowAlgorithmUpgrade.insert("V8", "V9", 5);

        MaxFlowAlgorithmUpgrade.insert("V10", "V8", 15);
        MaxFlowAlgorithmUpgrade.insert("V4", "V8", 15);
        MaxFlowAlgorithmUpgrade.insert("V9", "V8", 5);

        MaxFlowAlgorithmUpgrade.insert("V9", "V10", 10);
        MaxFlowAlgorithmUpgrade.insert("V10", "V9", 10);


        MaxFlowAlgorithmUpgrade.getOptimumPathFlow("V0", "V10");
//        MaxFlowAlgorithmUpgrade.getOptimumPathFlow("V10", "V0");
//        MaxFlowAlgorithmUpgrade.getOptimumPathFlow("V5", "V6");







    }


}
