INTRO:
1. this is an implementation of an algorithm to find the network flow.
2. I use stack, DFS to solve this problem.
    - toVisitNode : is the stack that contain the node to be visited, because we want to run deep 
      into a path only => schedule its childrend nodes to stack.
    
    - toVisitEdges : is the stack contain the following edges of the node in toVisitNode, they have
      the same position => when we pop 1 from the toVisitNode stack and 1 from edge from toVisitEdges
      then that node is the target (destination) of the edge.
      
    - edgesInPath : is the stack contain the edge in the path that we visited, and we can update all edges in path with 
      newFlow.
      
    - visitedNodeInPath : is the stack contain the nodes that we have already visited, to control the 
      flow of the path, because this is the two-way node, we use this stack to avoid visiting the visited
      nodes, and avoid it to run backward the path.
      
THE RELATIONSHIP BETWEEN THESE STACK.
1. toVisitNode - toVisitEdges and toVisitEdges - edgesInPath.
    + the destination of the last element in toVisitEdges always equal to the last node in toVisitNode.
      as we have known, the Edges in toVisitEdges is the following edges of the nodes in the toVisitNode
      => we use it in the special condition such as 
        + when it runs to the last node but that's not the destination node.
        + when it runs to the higher level of the destination node but not found the destination.
        + when it runs to the full capability edges.
        + when it runs to the destination node ...
        + in this case, we want to pop toVisitEdges to be balanced with the toVisitNode, however toVisitNode is empty
          => toVisitEdges will be clear.
    
    => we always pop the edges in the toVisitEdges until it meet the edges has the destination equal to the node
       in the toVisitNode stack.
    
    + edgesInPath contain the visited edges of that current path then the last(top) edge's destination of that stack 
      is always equal to the last(top) edge's previous of the toVisitEdges. in case, we meet the special condition, we
      need to balance these 2 stack => it's also a situation that the toVisitEdge is empty => we clear the edgesInPath.

2. VisitedNodeInPath - edgesInPath.
    + the last visited node is equal to the last visited edge's destination.
    + in case, we want to balance the visited nodes, but the visited edges is empty => clear visited node stack and 
      push the startNode to visited stack.
      

ACTIONS.
1. resetEdgeStacks.
    - based on the relationship (1), we balance toVisitEdges with toVisit Node and balance edgesInPath with toVisitEdges.
        + Basically, if toVisitNode is not empty, then bring the destination of the last element in toVisitEdges to be
          equal to the last node in toVisitNode, else clear toVisitEdges.
        + if tiVisitEdges is not empty, then bring the last(top) edge's destination of that stack to be equal to the 
          last(top) edge's previous of the toVisitEdges, else clear edgesInPth.

2. resetVisitedNodeInPath.
    - based on the relationship (2), if edgesInPath is not empty, then bring the last visited node to be equal to the last
      visited edge's destination, else clear visitedNodeInPath and push the start node to this stack.

3. flowMaintain.
    - if the edgesInPath is empty ==> we set the flow to be infinity.
    - when the edgesInPath is not empty, we need to check again to make sure we don't miss any capability.
    

ALGORITHM STEPS.
1. Construct the level for every layer of node.
    - using BFS we layer by layer construct the level of the node from the start to destination. 
    - the initial level is -1 or that currentNode's level is smaller than the currentDestinationNode (to avoid it runs backward) 
      or, the currentDestinationNode is the destination (if that node is destinationNode but, It's level is smaller than the others we alo accept
      and update it to be the highest level). 
    - construct algorithm will update the node by It's parent's level + 1.

2. get the startNode, desNode to check if they are existed, if true => push the startNode to the toVisitNode stack.

3. using while loop to visit all node in the toVisitNode until toVisitNode isEmpty.
    - pop the item in toVisitNode stack and set the current node to be that node.
    - we check the special case of when they run to the wrong path and this path does not lead to the destination and, it has satisfied one of these conditions.
        + that current node's level is >= the destination's level and that node is not the destination node.
        + that current node is the leaf node ( that current node has no edge or that current node has 1 edge and that edge's destination is in 
          the visitedNodeInPath stack.) and that current node is not the destination node.
        => then execute Actions:
            - resetEdgeStacks.
            - MaintainFlow.
            NOTE : we don't want to resetVisitedStack because we don't want to visit these nodes no more.  
    - else, do.
        - if toVisitEdges is not empty.
            Note: followEdge is the variable, It's type is Edge<E>
            + followEdge = toVisitEdges.pop().
            + check remainCapability = followEdge.Capability - followEdge.weight;
            + newFlowCap equal to the smallest capability in the path.
            + push that edge to edgeInPath.
    
            - if currentNode is destination.
                + update newFlowCap to all edges in path and check fullEdge.
                + add the edge collection in PathCollection.
                - if fullEdge is not null.
                    + pop Node from toVisitNode until it has the same level with the fullEdge's destination. 
                    + resetEdgeStacks.
            
                - else, do :
                    + popEdges which then bring the last visited node to be equal to the last visited edge's destination.
                
                - resetVisited.
                - MaintainFlow.
    
        - if current node is not the destination node.
            + run for loop to access every edge in the currentNode's Edges.
            + if edge satisfied all these conditions: 
                - that currentNode's level < its edge.destination's level.
                - its edge.Capability - edge.weight != 0.
                - visitedNodeInPath is not contain edge.destination.
                => then, do:
                    + toVisitNode push edge.destination.
                    + toVisitEdges push edge.destination.
            + else if its edge.Capability - edge.weight == 0, meet the full edge.
                - resetEdgeStacks.
                - resetVisitedNodeInPath.

        - push currentNode to visitedNodeInPath.


















