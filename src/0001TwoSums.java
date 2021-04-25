/*
 (1)暴力解法，双循环遍历，时间复杂度O(n^2)，空间复杂度O(1)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[]{-1, -1};
    }
}

/*
(2)双指针，我们需要对nums数组进行排序，然后收尾两个指针分别向中间移动直到两个指针分别指向两个和为target的元素，时间复杂度O(nlogn)，空间复杂度O(n)
注意：因为排序后数组的索引与原来的不同，所以我们使用一个辅助数组来进行排序。我们在寻找两个元素在原数组中的顺序时为了避免重复元素的影响，我们需要额外进行处理。我们使用-1标记该目标元素的索引还未找到。
如nums=[3,2,3，4,3],target=6,初始化result=[-1,-1]
经过排序求解得到两个元素分别为tmp[left]=3和tmp[right]=3，我们开始遍历nums数组：
第一轮：nums[0] = tmp[left]and result[0] = -1，那么此时result=[0,-1]，因为nums[0]元素已经被使用，所以我们continue；
第二轮：nums[1] != tmp[left] and result[0] = 0，继续下一个判断；nums[1] != tmp[right]继续下一个循环；
第三轮：nums[2] = tmp[left]但是result[0]!=-1，继续下一个判断；nums[2] = tmp[right] and result[1] = -1，那么此时result=[0,2]......
最终返回result。
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
       int[] result = new int[]{-1, -1};
       int[] tmp = Arrays.copyOf(nums, nums.length);
       Arrays.sort(tmp);
       int left = 0, right = nums.length - 1;
       while(left < right){
           if(tmp[left] + tmp[right] == target) break;
           else if(tmp[left] + tmp[right] > target){
               right--;continue;
           } else{
               left++;continue;
           }
       }
       for(int i = 0; i < nums.length; i++){
           if(nums[i] == tmp[left] && result[0] == -1){
               result[0] = i;
               continue;
           }
           if(nums[i] == tmp[right] && result[1] == -1){
               result[1] = i;
           }
       }
       return result;
    }
}

/*
(3)哈希表，k-v分别是元素和索引，我们寻找是否存在target-nums[i]，时间复杂度O(n)，空间复杂度O(n)
注意：我们是先寻找map中是否存在target-nums[i]，然后将元素和索引分别写入到map中。而不是将所有的元素和索引生成map再寻找。
 如nums=[3,3,2,4,3],target=6
 第一轮：nums[0] = 3, 而map中不存在6-3=3，所以我们将3:0写入map
 第二轮：nums[1] = 3, 而map中存在6-3=3，所以我们直接获得两个索引返回即可
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}