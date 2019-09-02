import java.util.HashMap;

/*

There are N piles of stones arranged in a row. The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, 

and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

https://leetcode-cn.com/problems/minimum-cost-to-merge-stones

*/

public class Problem1000 {
	int[] preSum;
	int[] stones;
	int K;

	int[][][] dp;
    
    public int mergeStones(int[] stones, int K) {
        this.dp = new int[stones.length][stones.length][K + 1];
        for (int i = 0; i < stones.length; i++){
            for (int j = 0; j < stones.length; j++){
                for (int k = 0; k < K + 1; k++){
                    this.dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    	if ((stones.length-1)%(K-1) != 0){
    		return -1;
    	}
    	this.stones = stones;
    	this.preSum = new int[stones.length + 1];
    	this.K = K;
    	
    	//pre_sum is for future use in calculating cost
    	for (int i = 0 ; i < this.stones.length; i++) {
    		this.preSum[i + 1] = this.preSum[i] + stones[i];
    	}
    	int cost = recursive(0, this.stones.length - 1, 1); 
		return cost;
    }
    
    //return the cost of making the subset of @stones starting from index @start to index @end into @piles piles
    int recursive(int start, int end, int piles) {
    	if (start == end && piles == 1) {
    		return 0;
    	}
    	if (this.dp[start][end][piles] != Integer.MAX_VALUE) {
    		return this.dp[start][end][piles];
    	}
    	if (piles == 1) {
    		int cost = recursive(start, end, this.K) + this.preSum[end + 1] - this.preSum[start];
    		this.dp[start][end][piles] = cost;
    		return cost;
    	}else {
    		int minCost = Integer.MAX_VALUE;
    		for (int split = start; split < end; split = split + this.K - 1) {
    			minCost = Math.min(minCost, recursive(start, split, 1) + recursive(split + 1, end, piles - 1));
    		}
    		this.dp[start][end][piles] = minCost;
    		return minCost;
    	}
    }
   
}
