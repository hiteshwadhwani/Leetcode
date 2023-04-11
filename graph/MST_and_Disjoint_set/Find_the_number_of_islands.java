package graph.MST_and_Disjoint_set;

import java.util.Arrays;


//https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-the-number-of-islands
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
public class Find_the_number_of_islands {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        Disjoint ds = new Disjoint(n * m);
        int count = 0;
        
        for(int row=0;row<grid.length;row++){
            for(int col=0;col<grid[0].length;col++){
                if(grid[row][col] == '0') continue;
                
                visited[row][col] = 1;
                count++;
                
                //check children
                int[] dx = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
                int[] dy = new int[]{0, 0, 1, -1, 1, -1, -1, 1};
                for(int i=0;i<8;i++){
                    int adj_row = row + dx[i];
                    int adj_col = col + dy[i];
                     
                    if(adj_row < 0 || adj_col < 0 || adj_row >= n || adj_col >= m) continue;
                    if(visited[adj_row][adj_col] == 1){
                        int nodeNo = row * m + col;
                        int adjNodeNo = adj_row * m + adj_col;
                        if(ds.find(nodeNo) != ds.find(adjNodeNo)){
                            count--;
                            ds.UnionByRank(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }
        
        return count;
    }
}
