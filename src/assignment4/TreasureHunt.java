package assignment4;
import java.lang.Math;

public class TreasureHunt {

	private int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	public int findMinRiskRecursive(int[][] grid, int row, int col) {
		if (row >= grid.length || col >= grid[0].length) return Integer.MAX_VALUE;
		if (row == grid.length-1 && col == grid[0].length-1) return grid[row][col];  // WE are at the last box, so just return;


		return grid[row][col] + Math.min(
				findMinRiskRecursive(grid, row + 1 , col),
				findMinRiskRecursive(grid, row, col + 1)
		);

	}


	public int findMinRiskMemoization(int[][] grid, int row, int col, int[][] memo) {
		return -1;
	}

	public int findMinRiskTabulation(int[][] grid) {
		return -1;
	}

}
