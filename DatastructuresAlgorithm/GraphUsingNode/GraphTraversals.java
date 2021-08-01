package DatastructuresAlgorithm.GraphUsingNode;

import java.util.*;

public class GraphTraversals<E> {
    HashMap<E, GraphNode<E>> GraphNodes = new HashMap<>();

    static class GraphNode<E>{
        E item;
        LinkedList<Edge<E>> Edges ;

        GraphNode (E item){
            this.item = item;
            this.Edges = new LinkedList<>();
        }
    }



    public void DFS (E initialName){
        GraphNode<E> initialNode = GraphNodes.get(initialName), toVisitItem;
        Stack<GraphNode<E>> toVisit = new Stack<>();
        Set<E> visited = new LinkedHashSet<>();
        String visitedItems = "";


        if (initialNode != null){
            visited.add(initialName);
            toVisit.push(initialNode);

            while (!toVisit.isEmpty()){
                for (int i  = 0; i < initialNode.Edges.size(); i ++){
                    toVisitItem = initialNode.Edges.get(i).destination;
                    if (!visited.contains(toVisitItem.item)) {
                        toVisit.push(toVisitItem);
                    }
                }
                initialNode = toVisit.pop();
                visited.add( initialNode.item);
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


    public void BFS (E initialName){
        GraphNode<E> initialNode = GraphNodes.get(initialName), toVisitItem;
        Queue<GraphNode<E>> toVisit = new LinkedList<>();
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

        GraphNode<E> destination, initialNode;
        destination = this.GraphNodes.get(destinationName);
        initialNode = this.GraphNodes.get(initialName);

        if (destination == null) {
            destination = new GraphNode<>(destinationName);
            this.GraphNodes.put(destinationName, destination);
        }

        if (initialNode == null){

            GraphNode<E> newGraphNode = new GraphNode<>(initialName);
            this.GraphNodes.put(initialName, newGraphNode);
            newGraphNode.Edges.add(new Edge<>(destination, 0));

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
        graph.BFS("HCM city");



    }

}
