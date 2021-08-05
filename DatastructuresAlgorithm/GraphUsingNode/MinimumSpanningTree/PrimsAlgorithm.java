package DatastructuresAlgorithm.GraphUsingNode.MinimumSpanningTree;

import DatastructuresAlgorithm.GraphUsingNode.Edge;
import DatastructuresAlgorithm.GraphUsingNode.GNode;
import DatastructuresAlgorithm.GraphUsingNode.sort;

import java.util.*;

public class PrimsAlgorithm<E>{
    // this is the hashMap to contain the nodes
    HashMap<E, GNode<E>> GraphNodes = new HashMap<>();

    private void reArrange (LinkedList<GNode<E>> list){
        sort<E> GnodeSort = new sort<E>();
        GnodeSort.QuickSortTraditionalMethodNonRecursion2(list);

    }
    private void resetNodes (){
        for (GNode<E> node : GraphNodes.values()){
            node.resetWeight();
        }
    }

    private String getPath (E StartName){
        String Result ="";
        return getPathFromMST(StartName, Result);

    }
    private String getPathRecur (E StartName){
        String Result ="";
        return getPathFromMSTRecursive(StartName);

    }

    // recursive as DFS
    private String getPathFromMSTRecursive(E StartName){
        /*
        this method is look for the path from the start node to the others.
        - by loop in to its edge to find the edge that has the previous node is the start node and
        add them to the path.
        - the previous node is the previous node that has the smallest cost to move to the destination node
         */
        int i = 0, len ;
        GNode<E> startNode = GraphNodes.get(StartName), destinationNode;
        String result = StartName + "-->  ";
        E destinationName;
        // len of the startNde Edges that contain the edge point at a specific destination.
        len = startNode.Edges.size();

        for (int id = 0; id < len; id++){
            // destination node is the destination of the edge of the start node point at.
            destinationNode = startNode.Edges.get(id).destination;

            // in case destination is not null and it has the previous
            if (destinationNode != null && destinationNode.previous!= null) {

                destinationName = destinationNode.item; // get destination name
                // if the destination node has the previous node is the start ==> add path and continue
                // recursively work on the destination as the start node
                if (destinationNode.previous.equals(startNode)) {
                    i ++;
                    if (i > 1) { // in case, that node point at many nodes ==> we show the branches
                        result += StartName + "-->  " + getPathFromMSTRecursive(destinationName);
                    }else {
                        result += getPathFromMSTRecursive(destinationName);

                    }
                }
            }
        }

        return result;
    }
    // this is the same as the above algorithm but it is implemented by using while and stack.
    private String getPathFromMST (E StartName, String result){
        GNode<E> startNode, destinationNode;
        int i = 0, len;
        Stack<E> toVisit = new Stack<>();
        Set<E> visited = new LinkedHashSet<>();
        E destinationName;
        result += StartName + "-->  ";


        toVisit.push(StartName);
        while (!toVisit.isEmpty()) {
            StartName = toVisit.pop();
            startNode = GraphNodes.get(StartName);
            if (startNode!= null) {
                len = startNode.Edges.size();
                for (int id = 0; id < len; id++) {

                    destinationNode = startNode.Edges.get(id).destination;

                    if (destinationNode != null && destinationNode.previous != null) {
                        destinationName = destinationNode.item;

                        if (destinationNode.previous.equals(startNode)) {
                            i++;
                            if (i > 1) {
                                result += StartName + "-->  " + destinationName + "-->  ";
                            } else {
                                result += destinationName + "-->  ";

                            }
                            if (!(toVisit.contains(destinationName) && visited.contains(destinationName)))
                                toVisit.push(destinationName);
                        }
                    }
                }
            }
            i = 0;
            visited.add(StartName);
        }
        return result;
    }

    public void getSingleSourceShortestPath(E StartName) {

        resetNodes(); // reset the graph nodes

        GNode<E> StartNode = GraphNodes.get(StartName), destinationNode;
        Queue<GNode<E>> toVisit = new LinkedList<>();
        Set<E> visited = new LinkedHashSet<>();
        int len, newDistance;
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
                        newDistance = edge.weight; // distance = edge distance

                        if (!visited.contains(destinationNode.item)){
                            if (newDistance < destinationNode.weight ) { // if new distance < destination distance
                                destinationNode.weight = newDistance; // update destination distance.
                                destinationNode.previous = StartNode; // set the previous node is start node, that mean that way is the best way
                            }
                            if (!toVisit.contains(destinationNode)) toVisit.add(destinationNode); // if the destination not in the Queue ==> add to the queue.
                        }
                    }
                }
                visited.add(StartNode.item); // add the Start node as visited
                // rearrange the queue to make the list of node in order ==> choose the way that has a smaller cost
                this.reArrange((LinkedList<GNode<E>>) toVisit);
            }

            String Path = this.getPath(StartName);
            System.out.println(Path);
            String Path2 = this.getPathRecur(StartName);
            System.out.println(Path2);
        }
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
//        Queue<GNode<String>> test = new LinkedList<>();
//        PrimsAlgorithm<String> prim = new PrimsAlgorithm<>();
//        prim.insert("HCM City", "Ha Noi", 2);
//        prim.insert("HCM City", "Da Nang", 11);
//        prim.insert("Ha Noi", "Da Nang", 3);
//        prim.insert("Ha Noi", "Hai Phong", 10);
//        prim.insert("Gia Lai", "HCM City", 4);
//        prim.insert("Gia Lai", "Ca Mau", 5);
//        prim.insert("Da Nang", "Gia Lai", 2);
//        prim.insert("Da Nang", "Ca Mau", 8);
//        prim.insert("Da Nang", "Long An", 4);
//        prim.insert("Da Nang", "Hai Phong", 2);
//        prim.insert("Hai Phong", "Long An", 6);
//        prim.insert("Long An", "Ca Mau", 1);
//        prim.getSingleSourceShortestPath("HCM City");


        PrimsAlgorithm<String> prim2 = new PrimsAlgorithm<>();
        prim2.insert("V1", "V2", 2);
        prim2.insert("V1", "V3", 4);
        prim2.insert("V1", "V4", 1);

        prim2.insert("V2", "V1", 2);
        prim2.insert("V2", "V4", 3);
        prim2.insert("V2", "V5", 10);

        prim2.insert("V3", "V1", 4);
        prim2.insert("V3", "V4", 2);
        prim2.insert("V3", "V6", 5);

        prim2.insert("V4", "V1", 1);
        prim2.insert("V4", "V2", 3);
        prim2.insert("V4", "V3", 2);
        prim2.insert("V4", "V5", 7);
        prim2.insert("V4", "V6", 8);
        prim2.insert("V4", "V7", 4);

        prim2.insert("V5", "V2", 10);
        prim2.insert("V5", "V4", 7);
        prim2.insert("V5", "V7", 6);

        prim2.insert("V6", "V3", 5);
        prim2.insert("V6", "V4", 8);
        prim2.insert("V6", "V7", 7);

        prim2.insert("V7", "V4", 4);
        prim2.insert("V7", "V5", 6);
        prim2.insert("V7", "V6", 1);


        prim2.getSingleSourceShortestPath("V3");




    }

}
