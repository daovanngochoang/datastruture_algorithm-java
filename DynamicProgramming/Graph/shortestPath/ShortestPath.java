package DynamicProgramming.Graph.shortestPath;

import DynamicProgramming.Graph.Edge;
import DynamicProgramming.Graph.GNode;
import DynamicProgramming.Graph.sort;

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
            node.weight = Integer.MAX_VALUE;
            node.previous = null;
        }
    }




    private void reArrange (LinkedList<GNode<E>> list){
        sort<E> GnodeSort = new sort<E>();
        GnodeSort.QuickSortTraditionalMethodNonRecursion2(list);

    }





    public void getSingleSourceShortestPath(E StartName, E DestinationName) {
        String name = StartName + " => " + DestinationName + "'s ";

        if (!StartName.equals(DestinationName)) {
            resetNodes(); // reset the graph nodes

            GNode<E> StartNode = GraphNodes.get(StartName), currentDestinationNode, DestinationNode = GraphNodes.get(DestinationName);
            Queue<GNode<E>> toVisit = new LinkedList<>();
            Set<E> visited = new LinkedHashSet<>();
            int len, newDistance;
            Edge<E> edge;

            if (StartNode != null && DestinationNode != null) { // if start node is exist
                toVisit.add(StartNode); // add start node to the toVisit queue
                StartNode.weight = 0; // change the start node's weight tobe 0

                while (!toVisit.isEmpty()) { // if the toVisit queue is not empty
                    StartNode = toVisit.remove(); // get the start node
                    len = StartNode.Edges.size();

                    if (!visited.contains(StartNode.item)) { // if that node is not in the viSited
                        for (int i = 0; i < len; i++) { // ==> check all its edges and update the distance

                            edge = StartNode.Edges.get(i);
                            currentDestinationNode = edge.destination;
                            newDistance = edge.weight + StartNode.weight; // distance = Start distance + edge distance

                            if (newDistance < currentDestinationNode.weight) { // if new distance < destination distance
                                currentDestinationNode.weight = newDistance; // update destination distance.
                                currentDestinationNode.previous = StartNode; // set the previous node is start node, that mean that way is the best way

                            }
                            if (!toVisit.contains(currentDestinationNode))
                                toVisit.add(currentDestinationNode); // if the destination not in the Queue ==> add to the queue.
                        }
                    }
                    visited.add(StartNode.item); // add the Start node as visited
                    this.reArrange((LinkedList<GNode<E>>) toVisit);
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
            StartNode = newGNode;
            this.GraphNodes.put(StartName, newGNode); // add create start
            newGNode.Edges.add(new Edge<>(destination, distance)); // add the edge from start to destination

        }else {
            StartNode.Edges.add(new Edge<>(destination, distance)); // add the edge to the start node.
        }

        // sort the list of that node to get the order from smaller to larger
        SortList(StartNode);

    }

    public void SortList (GNode<E> gNode){
        sort<E> eSort = new sort<>();
        eSort.QuickSortTraditionalMethodNonRecursion(gNode.Edges);
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


//        path.getSingleSourceShortestPath("HCM City", "Hai Phong");
//        path.getSingleSourceShortestPath("Ha Noi", "Ca Mau");
//        path.getSingleSourceShortestPath("HCM City", "Ca Mau");
//        path.all_pairShortestPath();

        ShortestPath<String> Path2 = new ShortestPath<>();

        Path2.insert("V1", "V2", 2);
        Path2.insert("V1", "V3", 4);
        Path2.insert("V1", "V4", 1);

        Path2.insert("V2", "V1", 2);
        Path2.insert("V2", "V4", 3);
        Path2.insert("V2", "V5", 10);

        Path2.insert("V3", "V1", 4);
        Path2.insert("V3", "V4", 2);
        Path2.insert("V3", "V6", 5);

        Path2.insert("V4", "V1", 1);
        Path2.insert("V4", "V2", 3);
        Path2.insert("V4", "V3", 2);
        Path2.insert("V4", "V5", 7);
        Path2.insert("V4", "V6", 8);
        Path2.insert("V4", "V7", 4);

        Path2.insert("V5", "V2", 10);
        Path2.insert("V5", "V4", 7);
        Path2.insert("V5", "V7", 6);

        Path2.insert("V6", "V3", 5);
        Path2.insert("V6", "V4", 8);
        Path2.insert("V6", "V7", 1);

        Path2.insert("V7", "V4", 4);
        Path2.insert("V7", "V5", 6);
        Path2.insert("V7", "V6", 1);
        Path2.getSingleSourceShortestPath("V6", "V2");
        Path2.all_pairShortestPath();



    }
}
