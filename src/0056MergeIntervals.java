/* 将区间按照starti进行排序
 * 将第一个数组加入到最终结果，依次遍历之后的数组决定是直接加入还是扩大前一个数组的区间。
 * merged = [[a,b]], candidate = [c,d]
 * a <= b < c <= d: merged = [[a,b], [c,d]]
 * a <= c <= b < d : merged = [[a,d]]
 * a <= c <= d < b : merged = [[a,b]]
 * 因为我们排过序，所以c不会比a小。
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        merged.add(new int[]{intervals[0][0], intervals[0][1]});
        for(int i = 1; i < intervals.length; i++) {
            int left = merged.get(merged.size() - 1)[1];
            int right = intervals[i][0];
            if(left < right) {
                merged.add(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(intervals[i][1], left);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}