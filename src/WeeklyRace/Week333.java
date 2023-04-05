package WeeklyRace;

import java.util.*;

public class Week333 {

    public static void main(String[] args) {
//        int[][] nums1 = new int[][]{{1, 2}, {2, 3}, {4, 5}};
//        int[][] nums2 = new int[][]{{1, 4}, {3, 2}, {4, 1}};
//        mergeArrays1(nums1, nums2);
        int i = minOperations(39);
        System.out.println(i);
    }

    /**
     * 题目：2570 合并两个二维数组-求合法
     */
    public static int[][] mergeArrays1(int[][] nums1, int[][] nums2){
        //使用双指针，先计算一共有几个值
        //使用set集合 无序不重复
        Set<Integer> count = new HashSet<>();
        for (int[] ints : nums1) {
            count.add(ints[0]);
        }
        for (int[] ints : nums2) {
            count.add(ints[0]);
        }
        //创建结果
        int[][] res = new int[count.size()][2];
        int index = 0;
        //创建双指针
        int index1 = 0;
        int index2 = 0;
        while(index1 < nums1.length || index2 < nums2.length){
            //如果都没有遍历完
            if(index1 < nums1.length && index2 < nums2.length){
                //如果相同 合并
                if(nums1[index1][0] == nums2[index2][0]){
                    res[index][0] = nums1[index1][0];
                    res[index][1] = nums1[index1][1] + nums2[index2][1];
                    index1++;
                    index2++;
                }else if(nums1[index1][0] < nums2[index2][0]){
                    res[index][0] = nums1[index1][0];
                    res[index][1] = nums1[index1][1];
                    index1++;
                }else {
                    res[index][0] = nums2[index2][0];
                    res[index][1] = nums2[index2][1];
                    index2++;
                }
            }else if(index1 < nums1.length){
                res[index][0] = nums1[index1][0];
                res[index][1] = nums1[index1][1];
                index1++;
            }else if(index2 < nums2.length){
                res[index][0] = nums2[index2][0];
                res[index][1] = nums2[index2][1];
                index2++;
            }
            index++;
        }

        return res;
    }

    //使用hashmap进行计算
    public static int[][] mergeArrays(int[][] nums1, int[][] nums2){
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] ints : nums1) {
            cnt.put(ints[0], cnt.getOrDefault(ints[0], 0) + ints[1]);
        }
        for (int[] ints : nums2) {
            cnt.put(ints[0], cnt.getOrDefault(ints[0], 0) + ints[1]);
        }
        //进行排序
        int len = cnt.size();
        int[] sort = new int[len];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            sort[index++] = entry.getKey();
        }
        Arrays.sort(sort);
        //创建结果
        int[][] res = new int[len][2];
        for(int i = 0; i < len; i++){
            res[i][0] = sort[i];
            res[i][1] = cnt.get(sort[i]);
        }
        return res;
    }

    /**
     * 题目：2571 将整数减少到零需要的最少操作数
     */
    //递归
    static int result;
    public static int minOperations(int n){
        if(n == 1){
            return 1;
        }
        //奇偶判断
        result = 0;
        if(n % 2 == 0){
            minOp(n, result);
        }else {
            result = 1;
            int index = 0;
            while(Math.pow(2, index) < n){
                index++;
            }
            int a = (int) Math.pow(2, index - 1);
            int b = (int) Math.pow(2, index);
            int c = (a + b) / 2;
            if(n - c == 1){
                minOp(n - 1, result);
            }else {
                minOp(n + 1, result);
            }
        }
        return result;
    }

    public static void minOp(int n, int res){
        //终止条件
        if(n == 0){
            return ;
        }
        //单层递归条件
        //找到n的左右两边的幂次
        int index = 0;
        while(Math.pow(2, index) < n){
            index++;
        }
        int a = n - (int) Math.pow(2, index - 1);
        int b = (int) Math.pow(2, index) - n;
        minOp(a < b ? a : b, result++);

    }
}
