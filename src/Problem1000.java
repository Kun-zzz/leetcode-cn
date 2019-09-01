import java.util.HashMap;

/*

There are N piles of stones arranged in a row. The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, 

and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

https://leetcode-cn.com/problems/minimum-cost-to-merge-stones

*/

public class Problem1000 {
	int[] pre_sum;
	int[] stones;
	int K;
	HashMap<int[], Integer> dp;
	
    public int mergeStones(int[] stones, int K) {
    	if ((stones.length-1)%(K-1) != 0){
    		return -1;
    	}
    	this.stones = stones;
    	this.pre_sum = new int[stones.length + 1];
    	this.K = K;
    	//pre_sum is for future use in calculating cost
    	this.pre_sum[0] = 0;
    	for (int i = 0 ; i < this.pre_sum.length; i++) {
    		this.pre_sum[i + 1] = this.pre_sum[i] + stones[i];
    	}
    	int cost = recursive(0, this.stones.length - 1, 1); 
		return cost;
    }
    
    //return the cost of making the subset of @stones starting from index @start to index @end into @piles piles
    int recursive(int start, int end, int piles) {
    	if (start == end && piles == 1) {
    		return 0;
    	}
    	if (this.dp.containsKey(new int[] {start, end, piles})) {
    		return this.dp.get(new int[] {start, end, piles});
    	}
    	if (piles == 1) {
    		int cost = recursive(start, end, this.K) + this.pre_sum[end + 1] - this.pre_sum[start];
    		this.dp.put(new int[] {start, end, piles}, cost);
    		return cost;
    	}else {
    		
    	}
    	return 0;
    }
    

}
