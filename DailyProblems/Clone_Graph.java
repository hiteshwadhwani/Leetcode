package DailyProblems;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://leetcode.com/problems/clone-graph/
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
public class Clone_Graph {
    HashMap<Integer, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Node newNode = new Node(node.val);
        DFS(node, newNode, visited);
        return newNode;
    }
    public void DFS(Node old, Node node, HashMap<Integer, Node> visited){
        visited.put(node.val, node);

        // add children
        for(Node nbr:old.neighbors){
            //it means we do not have the reference to that nbr than we have to make that node
            if(!visited.containsKey(nbr.val)){
                Node newNode = new Node(nbr.val);
                node.neighbors.add(newNode);
                DFS(nbr, newNode, visited);
            }
            //it means we have the reference to that nbr and we can directly add that to the neighbors
            else{
                node.neighbors.add(visited.get(nbr.val));
            }
        }
    }
}
