package graph.MST_and_Disjoint_set;
import java.util.Arrays;

//https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
public class Number_of_Operations_to_Make_Network_Connected {
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
    public int makeConnected(int n, int[][] connections) {
        //using kruskal

        if(connections.length < n-1){
            return -1;
        }
        Disjoint ds = new Disjoint(n);
        int extra_cables = 0;
        for(int[] edge:connections){
            int u = edge[0];
            int v = edge[1];

            int parent_u = ds.find(u);
            int parent_v = ds.find(v);

            if(parent_u != parent_v){
                ds.UnionByRank(parent_u, parent_v);
            }
            else{
                extra_cables++;
            }
        }
        int cnt_computers = 0;
        for(int i=0;i<n;i++){
            if(ds.parent[i] == -1) cnt_computers++;
        }
        return extra_cables >= cnt_computers-1 ? cnt_computers-1 : -1;
    }
}
