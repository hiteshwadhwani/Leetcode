package DailyProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class  LargestColorValueinaDirectedGraph {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        // make a graph
        ArrayList<Integer>[] graph = new ArrayList[n];
        int[] indegree = new int[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge:edges){
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            indegree[to]++;
        }

        int[][] count = new int[n][26];

        for(int i=0;i<n;i++){
            count[i][colors.charAt(i) - 'a']++;
        }

        //Khans Algorithm

        // to check if graph has cycle or not
        int visited = 0;
        
        Queue<Integer> que = new LinkedList<>();

        //push nodes int que whose indegree is 0
        for(int i=0;i<n;i++){
            if(indegree[i] == 0) que.add(i);
        }

        int max = 0;

        while(que.size() > 0){
            //remove
            int node = que.poll();

            //mark visited
            visited++;

            //work
            for(int i=0;i<26;i++){
                max = Math.max(max, count[node][i]);
            }

            //add children
            for(int nbr:graph[node]){
                for(int i=0;i<26;i++){
                    count[nbr][i] = Math.max(count[nbr][i], count[node][i] + (colors.charAt(nbr) - 'a' == i ? 1 : 0));
                }
                indegree[nbr]--;
                if(indegree[nbr] == 0) que.add(nbr);
            }
        }  
        
        //check if graph contains cycle by checking if all nodes are visited
        return  visited == n ? max : -1;
    }
}
