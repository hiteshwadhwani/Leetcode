package graph.MST_and_Disjoint_set;

import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimum-spanning-tree
public class Kruskal {
    class Disjoint{
        int[] parent;
        int[] rank;
        Disjoint(int n){
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(parent, -1);
        }
        public int find(int node){
            if(parent[node] == -1) return node;
            return parent[node] = find(parent[node]);
        }
        public void UnionByRank(int root1, int root2){
            int absoulute_parent_root1 = find(root1);
            int absolute_parent_root2 = find(root2);
    
            if(absoulute_parent_root1 == absolute_parent_root2) return;
    
            int rank1 = rank[absoulute_parent_root1];
            int rank2 = rank[absolute_parent_root2];
    
            if(rank1 > rank2){
                parent[absolute_parent_root2] = absoulute_parent_root1;
            }
            else if(rank1 < rank2){
                parent[absoulute_parent_root1] = absolute_parent_root2;
            }
            else{
                //rank1 == rank2
                parent[absolute_parent_root2] = absoulute_parent_root1;
                rank[absoulute_parent_root1]++;
            }
        }
    }
	int spanningTree(int V, int E, int edges[][]){
	    
	    //kruskal
	    Disjoint ds = new Disjoint(V);
	    Arrays.sort(edges, (a,b) -> a[2] - b[2]);
	    int sum = 0;
	    for(int[] edge:edges){
	        int u = edge[0];
	        int v = edge[1];
	        int wt = edge[2];
	        
	        int parent_u = ds.find(u);
	        int parent_v = ds.find(v);
	        
	        //they does not belong to the same component
	        if(parent_u != parent_v){
	            ds.UnionByRank(parent_u, parent_v);
	            sum += wt;
	        }
	    }
	    return sum;
	}
}
