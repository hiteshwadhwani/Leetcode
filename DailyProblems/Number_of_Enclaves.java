package DailyProblems;

//https://leetcode.com/problems/number-of-enclaves/
public class Number_of_Enclaves {
    public int numEnclaves(int[][] grid) {
        // perform boundry DFS
        // all 1 connnected through boundry one will be converted to 0

        int n = grid.length;
        int m = grid[0].length;
        
        //perform DFS on first and last column
        for(int i=0;i<n;i++){
            if(grid[i][0] == 1) DFS(grid, i, 0);
        }
        for(int i=0;i<n;i++){
            if(grid[i][m-1] == 1) DFS(grid, i, m-1);
        }

        //perform DFS on first row and last row
        for(int j=0;j<m;j++){
            if(grid[0][j] == 1) DFS(grid, 0, j);
        }
        for(int j=0;j<m;j++){
            if(grid[n-1][j] == 1) DFS(grid, n-1, j);
        }

        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1) count++;
            }
        }
        return count;
    }
    public void DFS(int[][] grid, int i, int j){
        grid[i][j] = 0;
        if (isvalid(grid, i+1, j)) DFS(grid, i+1, j);
        if (isvalid(grid, i, j+1)) DFS(grid, i, j+1);
        if (isvalid(grid, i-1, j)) DFS(grid, i-1, j);
        if (isvalid(grid, i, j-1)) DFS(grid, i, j-1);
    }
    public boolean isvalid(int[][] grid, int i, int j){
        if(i >= 0 && j >=0 && i < grid.length && j < grid[0].length && grid[i][j] ==1) return true;
        return false;
    }
}
