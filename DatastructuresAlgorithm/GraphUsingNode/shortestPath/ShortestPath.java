package DatastructuresAlgorithm.GraphUsingNode.shortestPath;

import DatastructuresAlgorithm.GraphUsingNode.Edge;
import DatastructuresAlgorithm.GraphUsingNode.GNode;

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

    public void getSingleSourceShortestPath(E StartName, E DestinationName) {
        String name = StartName + " => " + DestinationName + "'s ";

        if (!StartName.equals(DestinationName)) {
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
                            if (!toVisit.contains(destinationNode))
                                toVisit.add(destinationNode); // if the destination not in the Queue ==> add to the queue.
                        }
                    }
                    visited.add(StartNode.item); // add the Start node as visited
                }
                if (DestinationNode.weight != Integer.MAX_VALUE) {
                    String a = name + getPath(DestinationNode)+"\n"; // print the path
                    System.out.println(a);
                }
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
        return "shortest Path : " + path + "\t| shortest distance : " + distance;

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
        path.insert("HCM City", "Ha Noi", 2);
        path.insert("HCM City", "Da Nang", 11);
        path.insert("Ha Noi", "Da Nang", 3);
        path.insert("Ha Noi", "Hai Phong", 10);
        path.insert("Gia Lai", "HCM City", 4);
        path.insert("Gia Lai", "Ca Mau", 5);
        path.insert("Da Nang", "Gia Lai", 2);
        path.insert("Da Nang", "Ca Mau", 8);
        path.insert("Da Nang", "Long An", 4);
        path.insert("Da Nang", "Hai Phong", 2);
        path.insert("Hai Phong", "Long An", 6);
        path.insert("Long An", "Ca Mau", 1);


        path.getSingleSourceShortestPath("HCM City", "Hai Phong");
        path.getSingleSourceShortestPath("Ha Noi", "Ca Mau");
        path.getSingleSourceShortestPath("HCM City", "Ca Mau");
        path.all_pairShortestPath();





    }
}
