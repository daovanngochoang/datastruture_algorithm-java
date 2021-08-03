package DatastructuresAlgorithm.GraphUsingNode;

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
        GNode<E> initialNode = GNodes.get(initialName), toVisitItem;

        // create a stack that contain the destination of the current node.
        Stack<GNode<E>> toVisit = new Stack<>();

        // set contain nodes that visited, using LinkedHashSet because it will organize
        // and put new nodes at the end of the set instead of putting disorderly
        Set<E> visited = new LinkedHashSet<>();
        String visitedItems = "";


        if (initialNode != null){ // if that initial node is in the graph
            visited.add(initialName); // add that initial node item to the list
            toVisit.push(initialNode); // push it to the toVisit stack.

            while (!toVisit.isEmpty()){ // visit to the nodes in the toVisit stack
                initialNode = toVisit.pop(); // pop the last element of the stack
                visited.add( initialNode.item); // at that element to visited

                for (int i  = 0; i < initialNode.Edges.size(); i ++){
                    // find all the destinations of the current initial node and push to stack.
                    toVisitItem = initialNode.Edges.get(i).destination;
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
        GNode<E> initialNode = GNodes.get(initialName), toVisitItem;
        Queue<GNode<E>> toVisit = new LinkedList<>();
        Set<E> visited = new LinkedHashSet<>();
        String visitedItems = "";


        if (initialNode != null){
            toVisit.add(initialNode);

            while (!toVisit.isEmpty()){
                initialNode = toVisit.remove();
                visited.add(initialNode.item);

                for (int i  = 0; i < initialNode.Edges.size(); i ++){
                    toVisitItem = initialNode.Edges.get(i).destination;
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

        GNode<E> destination, initialNode;
        destination = this.GNodes.get(destinationName);
        initialNode = this.GNodes.get(initialName);

        if (destination == null) {
            destination = new GNode<>(destinationName);
            this.GNodes.put(destinationName, destination);
        }

        if (initialNode == null){

            GNode<E> newGNode = new GNode<>(initialName);
            this.GNodes.put(initialName, newGNode);
            newGNode.Edges.add(new Edge<>(destination, 0));

        }else {
            initialNode.Edges.add(new Edge<>(destination, 0));
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
//        graph.BFS("HCM city");



    }

}
