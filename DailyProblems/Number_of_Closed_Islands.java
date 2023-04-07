package DailyProblems;

//https://leetcode.com/problems/number-of-closed-islands/
public class Number_of_Closed_Islands {
    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 0 && DFS(grid, i, j)) count++;
            }
        }
        return count;
    }
    public boolean DFS(int[][] grid, int i, int j){
        int row = grid.length;
        int col = grid[0].length;
        if(i < 0 || j < 0 || i >= row || j >= col) return false;
        if(grid[i][j] == 1) return true;
        grid[i][j] = 1;
        boolean top = DFS(grid, i-1, j);
        boolean bottom = DFS(grid, i+1, j);
        boolean right = DFS(grid, i, j+1);
        boolean left = DFS(grid, i, j-1);
        return top && bottom && right && left;
    }
}
