package weekContest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Contest56 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//          System.out.println(compress("abbbbb".toCharArray()));
		System.out.println(smallestDistancePair(new int[] {1,4,5,6,7,8,9,9,10,10},18));
          
	}
	 public boolean isOneBitCharacter(int[] bits) {
	        if(bits.length==0 || bits[bits.length-1]==1) return false;
	        else return helper(bits,bits.length-2);
	 }
	 public boolean helper(int[] bits,int cur) {
		if(cur<0) return true;
		if(bits[cur]==1 && cur>0 && bits[cur-1]==1) return  helper(bits,cur-2);
		if(bits[cur]==0) {
			if(cur>0 && bits[cur-1]==1) {
				return helper(bits,cur-1)||
					   helper(bits,cur-2);
			}
			else return helper(bits,cur-1);
		}
		return false;
	 }
	 public static  int compress(char[] chars) {
		 if(chars==null || chars.length==0) return 0;		
		 char before=chars[0];	
		 int s=0;
		 int temp=1;
		 for(int i=1;i<chars.length;i++) {
			 if( before==chars[i]) {
				 temp++;
			 }
			 else {
				 if(temp==1)
					 chars[s++]=before;
				 else {
					 char[] tem=(before+""+temp).toCharArray();
					 for(int j=0;j<tem.length;j++) {
						 chars[s++]=tem[j];
					 }
				 }
				 before=chars[i];
				 temp=1;
			 }
		 }
		 if(temp==1) {
			 chars[s++]=before;			
		 }
		 else {
			 char[] tem=(before+""+temp).toCharArray();
			 for(int j=0;j<tem.length;j++) {
				 chars[s++]=tem[j];
			 }
		 }
		
		 
		 return s;
	        
	 }
	 public int findLength(int[] A, int[] B) {
		 int max=0;
		 int[][] dp=new int[A.length+1][B.length+1];
		 for(int i=1;i<=A.length;i++) {
			 for(int j=1;j<=B.length;j++) {
				 if(A[i-1]==B[j-1]) dp[i][j]=dp[i-1][j-1]+1;
				 else dp[i][j]=0;
				 max=Math.max(max, dp[i][j]);
			 }
		 }
		 return max;
	        
	    }
	 /*
	  * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

		Example 1:
		Input:
		nums = [1,3,1]
		k = 1
		Output: 0 
		Explanation:
		Here are all the pairs:
		(1,3) -> 2
		(1,1) -> 0
		(3,1) -> 2
		Then the 1st smallest distance pair is (1,1), and its distance is 0.
		Note:
		2 <= len(nums) <= 10000.
		0 <= nums[i] < 1000000.
		1 <= k <= len(nums) * (len(nums) - 1) / 2.
			  */
	 
	 //
	 public static int smallestDistancePair(int[] nums, int k) {
	      int n=nums.length;
	      Arrays.sort(nums);
	      int[] num=new int[nums.length-1];
	      int[] temp=new int[nums.length-1];
	      int[] index=new int[num.length];
	      for(int i=0;i<num.length;i++) {
	    	  num[i]=nums[i+1]-nums[i];
	    	  temp[i]=num[i];
	    	  index[i]=i;
	      }   	      
	      int s=0;	
	      int min=Integer.MAX_VALUE;
	      int minindex=-1;
	      while(s<k) {	
	    	 min=Integer.MAX_VALUE;
	    	 for(int j=0;j<num.length;j++) {
	    		 if(num[j]<min) {
	    			 min=num[j];
	    			 minindex=j;
	    		 }
	    	 } 
	    	 num[minindex]=index[minindex]+1<num.length?temp[index[minindex]+1]+num[minindex]:Integer.MAX_VALUE;
	    	 index[minindex]++;	    	 
	    	s++;
	      }
	      return min;
	    }
	 
	 //another solution binary search
	 int countPairs(int[] a, int mid) {
	        int n = a.length, res = 0;
	        for (int i = 0; i < n; ++i) {
	            int j = i;
	            while (j < n && a[j] - a[i] <= mid) j++;
	            res += j - i - 1;
	        }
	        return res;
	    }

	    public int smallestDistancePair2(int a[], int k) {
	        int n = a.length;
	        Arrays.sort(a);

	        // Minimum absolute difference
	        int low = a[1] - a[0];
	        for (int i = 1; i < n - 1; i++)
	            low = Math.min(low, a[i + 1] - a[i]);

	        // Maximum absolute difference
	        int high = a[n - 1] - a[0];

	        // Do binary search for k-th absolute difference
	        while (low < high) {
	            int mid = low + (high - low) / 2;
	            if (countPairs(a, mid) < k)
	                low = mid + 1;
	            else
	                high = mid;
	        }

	        return low;
	    }

}
