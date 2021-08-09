package DynamicProgramming.Graph.shortestPath;

import DynamicProgramming.Graph.Edge;
import DynamicProgramming.Graph.GNode;

import java.util.*;

public class GraphTraversals<E> {

    // this is the hashMap to contain the nodes
    HashMap<E, GNode<E>> GNodes = new HashMap<>();

    // this is represent to the GNode including it's item and it's edges to other destinations.



    /*
    - BFS using stack, because we want to visit all destination of the initial.
    ==> visit 1 node and dig it deep to the end and then visit other destination node at the same level
    ==> at to the end, pop out of the end ==> it can dig in deft to the last destination
     */
    public void DFS (E initialName){
        // by organizing graph nodes in to a map ==> that will access to the node faster
        GNode<E> StartNode = GNodes.get(initialName), toVisitItem;

        // create a stack that contain the destination of the current node.
        Stack<GNode<E>> toVisit = new Stack<>();

        // set contain nodes that visited, using LinkedHashSet because it will organize
        // and put new nodes at the end of the set instead of putting disorderly
        Set<E> visited = new LinkedHashSet<>();
        String visitedItems = "";


        if (StartNode != null){ // if that initial node is in the graph
            visited.add(initialName); // add that initial node item to the list
            toVisit.push(StartNode); // push it to the toVisit stack.

            while (!toVisit.isEmpty()){ // visit to the nodes in the toVisit stack
                StartNode = toVisit.pop(); // pop the last element of the stack
                visited.add( StartNode.item); // at that element to visited

                for (int i  = 0; i < StartNode.Edges.size(); i ++){
                    // find all the destinations of the current initial node and push to stack.
                    toVisitItem = StartNode.Edges.get(i).destination;
                    if (!visited.contains(toVisitItem.item)) {
                        toVisit.push(toVisitItem);
                    }
                }

            }
        }
        for (E i : visited){
            if (visitedItems.equals("")){
                visitedItems += i;
            }else {
                visitedItems += "--> " + i;
            }
        }
        System.out.println(visitedItems);
    }


    /*
    - visit level by level ==> put in queue.
    - at to the end but one by one chek to the start.
     */
    public void BFS (E initialName){
        GNode<E> StartNode = GNodes.get(initialName), toVisitItem;
        Queue<GNode<E>> toVisit = new LinkedList<>();
        Set<E> visited = new LinkedHashSet<>();
        String visitedItems = "";


        if (StartNode != null){
            toVisit.add(StartNode);

            while (!toVisit.isEmpty()){
                StartNode = toVisit.remove();
                visited.add(StartNode.item);

                for (int i  = 0; i < StartNode.Edges.size(); i ++){
                    toVisitItem = StartNode.Edges.get(i).destination;
                    if (!visited.contains(toVisitItem.item)) {
                        toVisit.add(toVisitItem);
                    }
                }
            }

            for (E i : visited){
                if (visitedItems.equals("")){
                    visitedItems += i;
                }else {
                    visitedItems += "--> " + i;
                }
            }
            System.out.println(visitedItems);
        }

    }



    public void insert(E initialName, E destinationName){

        GNode<E> destination, StartNode;
        destination = this.GNodes.get(destinationName);
        StartNode = this.GNodes.get(initialName);

        if (destination == null) {
            destination = new GNode<>(destinationName);
            this.GNodes.put(destinationName, destination);
        }

        if (StartNode == null){

            GNode<E> newGNode = new GNode<>(initialName);
            this.GNodes.put(initialName, newGNode);
            newGNode.Edges.add(new Edge<>(destination, 0));

        }else {
            StartNode.Edges.add(new Edge<>(destination, 0));
        }
    }


    public static void main (String[] args){
        GraphTraversals<String> graph = new GraphTraversals<>();
        graph.insert("HCM city","Ha Noi" );
        graph.insert("Ha Noi","Hai phong" );
        graph.insert("HCM city","Long An" );
        graph.insert("HCM city","Da lat" );
        graph.insert("HCM city","Da Nang" );
        graph.insert("Ha Noi","Da Nang" );
        graph.insert("Ha Noi","Phu Quoc" );
        graph.insert("Da lat","HCM city" );
        graph.insert("Da Nang","HCM city" );
        graph.insert("Da Nang","Ha Noi" );
        graph.insert("Phu Quoc","Ha Noi" );

        graph.DFS("HCM city");
        graph.BFS("HCM city");

        GraphTraversals<String> Graph2 = new GraphTraversals<>();
        Graph2.insert("V1", "V2");
        Graph2.insert("V1", "V3");
        Graph2.insert("V1", "V4");

        Graph2.insert("V2", "V1");
        Graph2.insert("V2", "V4");
        Graph2.insert("V2", "V5");

        Graph2.insert("V3", "V1");
        Graph2.insert("V3", "V4");
        Graph2.insert("V3", "V6");

        Graph2.insert("V4", "V1");
        Graph2.insert("V4", "V2");
        Graph2.insert("V4", "V3");
        Graph2.insert("V4", "V5");
        Graph2.insert("V4", "V6");
        Graph2.insert("V4", "V7");

        Graph2.insert("V5", "V2");
        Graph2.insert("V5", "V4");
        Graph2.insert("V5", "V7");

        Graph2.insert("V6", "V3");
        Graph2.insert("V6", "V4");
        Graph2.insert("V6", "V7");

        Graph2.insert("V7", "V4");
        Graph2.insert("V7", "V5");
        Graph2.insert("V7", "V6");
        Graph2.DFS("V3");



    }

}
