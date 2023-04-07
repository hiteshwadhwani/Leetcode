package graph.MST_and_Disjoint_set;

import java.util.Arrays;

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

public class Disjointset {
    public static void main(String[] args) {
        Disjoint ds = new Disjoint(7);
        ds.UnionByRank(1, 2);
        ds.UnionByRank(2, 3);
        ds.UnionByRank(4, 5);
        ds.UnionByRank(6, 7);
        ds.UnionByRank(5, 6);

        if (ds.find(3) == ds.find(7)) {
            System.out.println("Same");
        } else
            System.out.println("Not Same");

        ds.UnionByRank(3, 7);
        if (ds.find(3) == ds.find(7)) {
            System.out.println("Same");
        } else
            System.out.println("Not Same");
    }
}
