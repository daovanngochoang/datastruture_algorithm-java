INTRO:
1. this is an implementation of an algorithm of finding the network flow.
2. I use stack, DFS to solve this problem.

    - edgesInPath : is the stack contains the edges in the path that we have visited, and we can update all edges in path with 
      newFlow.
      
    - visitedNodeInPath : is the stack contains the nodes that we have already visited, to control the 
      flow of the path and, because this is the two-way node, we use this stack to avoid visiting the visited
      nodes, and avoid it to run backward the path. also, this stack contains the node to be visited at the top.
      
THE RELATIONSHIP BETWEEN THESE STACK.
- VisitedNodeInPath - edgesInPath.
    + the last visited node is equal to the last visited edge's destination.
    + in case, there is any full edge, we will pop all edge above that full edge and that full edge, then pop the nodes
      in node stack until the top node in stack is the destination of the top edge in the edge stack. in case the edge 
      stack is empty, we pop until reach the previous node of the full edge.
      
- ACTIONS.
1. FlowMaintain.
    - if edgeInPath is empty ==> newFlowCap = INF.
    - else, check the remainCap in the edges in the edgesInPath stack.
2. updateInPathEdge.
    - pop the tops item in the edge stack until it meet the fullEdge, pop the full edge and stop. 
3. UpdateNode.
    - pop until meet the condition of the fullEdge's previous / the top Edge's destination in the edgesInPath  ==> stop.
4. UpdateDeadEndStatus.
    - when that node has no way to any node ==> deadEnd.
    ==> deadEnd status true if all Its edges is full capacity, or that node can not visit to anywhere.
      

ALGORITHM STEPS.

1. Construct the level for every layer of node (it does the best in one-way nodes graph but not in the two-way).
    - using BFS we layer by layer construct the level of the node from the start to destination. 
    - the initial level is -1 or that currentNode's level is smaller than the currentDestinationNode (to avoid it runs backward) 
      or, the currentDestinationNode is the destination (if that node is destinationNode but, It's level is smaller than the others we alo accept
      and update it to be the highest level). 
    - construct algorithm will update the node by It's parent's level + 1.

2. get the startNode, desNode to check if they are existed, if true => push the startNode to the visitedNodeInPath stack.

3. using while loop we do:
    - assign currentNode to be the top item in visitedNodeInPath stack using visitedNodeInPath.peak() ==> we don't pop that node out.
    - we check is that current node the destination.
        => if that node is the destination:
            - update newFlowCap to all edges in path and check fullEdge.
            - add the edge collection in PathCollection.
            - total += newFlowCap.
            - update the deadEnd.(the sign told that node is the deadEnd node, avoid visiting in the next time )
            - if fullEdge is not null.
                + update the edgeInPath (Action of updateInPathEdge).
                + update the NodeInPath (Action of UpdateNode).
                + FlowMaintain.
    - else:
        - run the for loop to the currentNode Edges.
            - if the current Node satisfied all these condition.
                + if that currentNode not the destination node.
                + that currentNode is not deadEnd.
                + the edge's destination is not the deadEnd.
                + that edge's capability > 0.
                + the edge's destination not in the visited nodes.
                + count ++; // to check if any edge is added or not.
                + break.
        - check, if no node is added (count == 0).
            + set that node is the deadEnd.
            + pop that currentNode.
            + pop the following edge of that node.
            + FlowMaintain.

    


















