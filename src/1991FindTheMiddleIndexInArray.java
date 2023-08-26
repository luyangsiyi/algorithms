// O(n^2)
class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int left = sum(nums, 0, i - 1);
            int right = sum(nums, i + 1, n - 1);
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    public int sum(int[] nums, int start, int end) {
        int result = 0;
        if (end == -1) return 0;
        for (int i = start; i <= end; i++){
            result += nums[i];
        }
        return result;
    }
}

// 前缀和O(n)
// sum(0,i) + sum(0,i) + nums[i] = total 满足这个条件的i就是我们要的
class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) total += nums[i];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) return i;
            sum += nums[i];
        }
        return -1;
    }
}