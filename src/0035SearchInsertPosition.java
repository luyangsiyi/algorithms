// class Solution {
//     public int searchInsert(int[] nums, int target) {
//         return search(nums, target, 0, nums.length - 1);
//     }

//     public int search(int[] nums, int target, int start, int end) {
//         if (start == end) {
//             if (target <= nums[start]) return start;
//             else return start + 1;
//         }
//         int center = (start + end) / 2;
//         int value = nums[center];
//         if(target <= value) {
//             return search(nums, target, start, center);
//         } else {
//             return search(nums, target, center + 1, end);
//         }
//     }
// }

// 找到第一个大于等于target的下标
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left  = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int center = (left + right) / 2;
            if (target < nums[center]) {
                right = center - 1;
            } else if (target > nums[center]) {
                left = center + 1;
            } else {
                return center;
            }
        }
        return left;
    }
}