package leecode03;

import java.util.PriorityQueue;
import java.util.Random;

public class StackAndQueue {
    public static void main(String[] args) {
        findKthLargest3(new int[]{3, 2, 1, 5, 6, 4}, 2);
    }

    /**
     * 题目：215 数组中的第K个最大元素
     */
    //这种方法的时间复杂度不对
    public static int findKthLargest(int[] nums, int k){
        //创建小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);

        //取最大值放入小顶堆 先将小顶堆填满
        for(int i = 0; i < k; i++){
            pq.add(nums[i]);
        }

        //这时堆顶中的元素是堆中最小的 如果有比堆顶小的数就忽略 反之就加入
        for(int i = k; i < nums.length; i++){
            Integer peek = pq.peek();
            if(nums[i] > peek){
                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }

    public static int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    //快速排序选择（基于快速排序）
    public static int findKthLargest3(int[] nums, int k){
        // 第 1 大的数，下标是 len - 1;
        // 第 2 大的数，下标是 len - 2;
        // ...
        // 第 k 大的数，下标是 len - k;
        int len = nums.length;
        int target = len - k;

        int left = 0;
        int right = len - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                // pivotIndex > target
                right = pivotIndex - 1;
            }
        }
    }

    private final static Random random = new Random();
    private static int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);//随机选一个数
        // 将数放在最左边
        swap(nums, left, randomIndex);


        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int le = left + 1;//小于目标值的右边界
        int ge = right;//大于目标值的左边界

        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }

            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }

            if (le >= ge) {//直到两个边界相遇 break
                break;
            }
            swap (nums, le, ge);
            le++;
            ge--;
        }

        swap(nums, left, ge);//左边界是开区间
        return ge;
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
