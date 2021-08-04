package DatastructuresAlgorithm.GraphUsingNode;

import java.util.*;

/*
    - Dijkstra’s Algorithm: The Shortest Path Algorithm
    link 1: https://www.youtube.com/watch?v=GazC3A4OQTE
    link 2: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
    link 3: https://www.analyticssteps.com/blogs/dijkstras-algorithm-shortest-path-algorithm
 */

public class ShortestPath<E> {

    // this is the hashMap to contain the nodes
    HashMap<E, GNode<E>> GraphNodes = new HashMap<>();


    private void resetNodes (){
        for (GNode<E> node : GraphNodes.values()){
            node.resetWeight();
        }
    }

    public void getSingleSourceShortestPath(E StartName, E DestinationName){
        String name = StartName + " to "+ DestinationName + "'s ";
        resetNodes(); // reset the graph nodes

        GNode<E> StartNode = GraphNodes.get(StartName), destinationNode, DestinationNode = GraphNodes.get(DestinationName);
        Queue<GNode<E>> toVisit = new LinkedList<>();
        Set<E> visited = new LinkedHashSet<>();
        int len, newDistance, countC = 0, countP = 0;
        Edge<E> edge;

        if (StartNode != null) { // if start node is exist
            toVisit.add(StartNode); // add start node to the toVisit queue
            StartNode.weight = 0; // change the start node's weight tobe 0

            while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                StartNode = toVisit.remove(); // get the start node
                len = StartNode.Edges.size();

                if (!visited.contains(StartNode.item)) { // if that node is not in the viSited
                    for (int i = 0; i < len; i++) { // ==> check all its edges and update the distance

                        edge = StartNode.Edges.get(i);
                        destinationNode = edge.destination;
                        newDistance = edge.weight + StartNode.weight; // distance = Start distance + edge distance

                        if (newDistance < destinationNode.weight) { // if new distance < destination distance
                            destinationNode.weight = newDistance; // update destination distance.
                            destinationNode.previous = StartNode; // set the previous node is start node, that mean that way is the best way

                        }
                        if (!toVisit.contains(destinationNode)) toVisit.add(destinationNode); // if the destination not in the Queue ==> add to the queue.
                    }
                }
                visited.add(StartNode.item); // add the Start node as visited
            }
            if (DestinationNode.weight != Integer.MAX_VALUE) {
                String a = name + getPath(DestinationNode); // print the path
                System.out.println(a);
            }
        }
    }


    /*
    - find all shortest path from a node to others
     */
    public void all_pairShortestPath (){
        for (E node : GraphNodes.keySet()){
            for (E key : GraphNodes.keySet()) {
                if (!key.equals(node)) {
                    this.getSingleSourceShortestPath(node, key);
                }
            }
        }
    }

    private String getPath (GNode<E> Destination){ // get the path from the last
        String path = ""+ Destination.item;
        int distance = Destination.weight;
        while (Destination.previous != null) {
            path = Destination.previous.item + "--> " + path;
            Destination = Destination.previous;
        }
        return "shortest Path : " + path + "\tshortest distance : " + distance;

    }

    public GNode<E> getNode (E nodeName){
        return GraphNodes.get(nodeName);
    }


    public void insert(E StartName, E destinationName, int distance){

        GNode<E> destination, StartNode;
        destination = this.GraphNodes.get(destinationName);
        StartNode = this.GraphNodes.get(StartName);

        if (destination == null) { // if the destination is not exist
            destination = new GNode<>(destinationName);
            this.GraphNodes.put(destinationName, destination); // add destination to graph node
        }

        if (StartNode == null){ // if start is not exist

            GNode<E> newGNode = new GNode<>(StartName);
            this.GraphNodes.put(StartName, newGNode); // add create start
            newGNode.Edges.add(new Edge<>(destination, distance)); // add the edge from start to destination

        }else {
            StartNode.Edges.add(new Edge<>(destination, distance)); // add the edge to the start node.
        }

        // sort the list of that node to get the order from smaller to larger
        for (GNode<E> gNode: GraphNodes.values()){
            gNode.SortList();
        }
    }



    public static void main (String[] args){
        ShortestPath<String> path = new ShortestPath<>();
        path.insert("V1", "V2", 2);
        path.insert("V1", "V4", 11);
        path.insert("V2", "V4", 3);
        path.insert("V2", "V5", 10);
        path.insert("V3", "V1", 4);
        path.insert("V3", "V6", 5);
        path.insert("V4", "V3", 2);
        path.insert("V4", "V6", 8);
        path.insert("V4", "V7", 4);
        path.insert("V4", "V5", 2);
        path.insert("V5", "V7", 6);
        path.insert("V7", "V6", 1);


        path.getSingleSourceShortestPath("V1", "V5");
        path.getSingleSourceShortestPath("V2", "V6");
        path.getSingleSourceShortestPath("V1", "V6");
        path.all_pairShortestPath();





    }
}
