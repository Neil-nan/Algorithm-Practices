package leecode;

import javafx.scene.control.skin.SliderSkin;

import javax.print.DocFlavor;
import java.util.*;

public class HashTab {

    /**
     * 题目：242 有效的字母异位词
     */
    public static boolean isAnagram(String s, String t){
        //先判断长度
        if(s.length() != t.length()){
            return false;
        }

        int[] record = new int[26];

        //录入字符
        for(int i = 0; i < s.length(); i++){
            record[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++){
            record[t.charAt(i) - 'a']--;
        }
        //检查
        for (int count : record) {
            if(count != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：383 赎金信
     */
    //使用数组
    public static boolean canConstruct1(String ransomNote, String magazine){
        int[] dictionary = new int[26];

        //遍历杂志
        for(int i = 0; i < magazine.length(); i++){
            dictionary[magazine.charAt(i) - 'a']++;
        }
        //遍历信件
        for(int i = 0; i < ransomNote.length(); i++){
            dictionary[ransomNote.charAt(i) - 'a']--;
        }

        for (int count : dictionary) {
            if(count < 0){
                return false;
            }
        }
        return true;
    }

    //使用hash
    public static boolean canConstruct2(String ransomNote, String magazine){
        if(magazine.length() < ransomNote.length()){
            return false;
        }
        //创建hash
        Map<Character, Integer> record = new HashMap<>();
        //遍历杂志
        for(int i = 0; i < magazine.length(); i++){
            record.put(magazine.charAt(i), record.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        //遍历信件
        for(int i = 0; i < ransomNote.length(); i++){
            record.put(ransomNote.charAt(i), record.getOrDefault(ransomNote.charAt(i), 0) - 1);
            //判断
            if(record.get(ransomNote.charAt(i)) < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：49 字母异位词分组
     */
    public static List<List<String>> groupAnagrams(String[] strs){
        //创建hash key为字符串排序后的结果 value为可以成为key的字符串集合
        Map<String, List<String>> record = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> value = record.getOrDefault(key, new ArrayList<>());
            value.add(str);
            record.put(key, value);
        }

        return new ArrayList<>(record.values());

    }

    /**
     * 题目：438 找到字符串中素哟有字母异位词
     */
    public static List<Integer> findAnagrams1(String s, String p){
        List<Integer> ans = new ArrayList<>();
        //将p进行排序
        char[] chars = p.toCharArray();
        Arrays.sort(chars);
        p = new String(chars);
        int len = p.length();
        //双指针
        for(int i = 0; i <= s.length() - len; i++){
            String str = s.substring(i, i + len);
            char[] chars1 = str.toCharArray();
            Arrays.sort(chars1);
            str = new String(chars1);
            if(p.equals(str)){
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> findAnagrams2(String s, String p){
        int lenS = s.length();
        int lenP = p.length();
        if(lenP > lenS){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        //先判断第一组
        for(int i = 0; i < lenP; i++){
            sCnt[s.charAt(i) - 'a']++;
            pCnt[p.charAt(i) - 'a']++;
        }
        //这里不能用sCnt.equal(pCnt)
        if(Arrays.equals(pCnt, sCnt)){
            res.add(0);
        }
        //增删s字符串 并进行判断
        for(int i = lenP; i < lenS; i++){
            //添加
            sCnt[s.charAt(i) - 'a']++;
            //去除
            sCnt[s.charAt(i - lenP) - 'a']--;
            //判断
            if(Arrays.equals(pCnt, sCnt)){
                res.add(i - lenP + 1);
            }
        }
        return res;
    }

    /**
     * 题目：349 两个数组的交集
     */
    public static int[] intersection(int[] nums1, int[] nums2){
        //使用set保存结果 因为set集合无序不可重复
        Set<Integer> resSet = new HashSet<>();

        //set集合无序不可重复
        Set<Integer> cnt = new HashSet<>();
        //遍历数组1
        for (int i : nums1) {
            cnt.add(i);
        }
        //遍历数组2
        for (int i : nums2) {
            if(cnt.contains(i)){
                resSet.add(i);
            }
        }
        //转数组
        int[] resArr = new int[resSet.size()];
        int index = 0;
        for (Integer integer : resSet) {
            resArr[index++] = integer;
        }
        return resArr;

    }

    /**
     * 题目：350 两个数组的交集
     */
    public static int[] intersect1(int[] nums1, int[] nums2){
        //使用map保存结果
        //key 数组中的数字 value 数字重复次数
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> resList = new ArrayList<>();

        //遍历数组1
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        //遍历数组2
        for (int i : nums2) {
            if(map.getOrDefault(i, 0) > 0){
                resList.add(i);
                map.put(i, map.get(i) - 1);
            }
        }

        //结果转换成数组
        int[] res = new int[resList.size()];
        int index = 0;
        for (Integer integer : resList) {
            res[index++] = integer;
        }
        return res;
    }

    //使用排序 + 双指针
    public static int[] intersect2(int[] nums1, int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] ans = new int[Math.max(len1, len2)];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index1 < len1 && index2 < len2){
            if(nums1[index1] < nums2[index2]){
                index1++;
            }else if(nums1[index1] > nums2[index2]){
                index2++;
            }else {
                ans[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }

        return Arrays.copyOfRange(ans, 0, index);
    }

    /**
     * 题目：202 快乐数
     */
    public static boolean isHappy(int n){
        //记录数据 防止重发
        Set<Integer> result = new HashSet<>();
        while(n != 1 && !result.contains(n)){
            result.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    public static int getNextNum(int n){
        int res = 0;
        while (n > 0){
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

    /**
     * 题目：1 两数之和
     */
    public static int[] twoSum(int[] nums, int target){
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        //使用hash记录 key 数字 value 数字位置
        Map<Integer, Integer> cnt = new HashMap<>();
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int temp = target - nums[i];
            if(cnt.containsKey(temp)){
                res[0] = cnt.get(temp);
                res[1] = i;
            }
            cnt.put(nums[i], i);
        }
        return res;
    }

    /**
     * 题目：454 四数相加II
     */
    //看过思路
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        //使用hash记录 key记录a + b的数值 value 记录数值的个数
        Map<Integer, Integer> record = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                int temp = i + j;
                record.put(temp, record.getOrDefault(temp, 0) + 1);
            }
        }
        //记录结果
        int count = 0;
        //遍历c + d
        for (int i : nums3) {
            for (int j : nums4) {
                int temp = i + j;
                if(record.containsKey(0 - temp)){
                    count += record.get(0 - temp);
                }
            }
        }
        return count;
    }

    /**
     * 题目：383 赎金信
     */
    public static boolean canConstruct(String ransomNote, String magazine){
        if(ransomNote.length() > magazine.length()){
            return false;
        }
        //使用数组存储magazine
        int[] cnt = new int[26];
        //记录magazine
        for(int i = 0; i < magazine.length(); i++){
            cnt[magazine.charAt(i) - 'a']++;
        }
        //记录ransomNote
        for(int i = 0; i < ransomNote.length(); i++){
            cnt[ransomNote.charAt(i) - 'a']--;
        }

        //检查
        for (int i : cnt) {
            if(i < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：15 三数之和
     */
    //使用双指针
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //找出a+b+c = 0
        //a = nums[i] b = nums[j] c = -(a + b)
        for(int i = 0; i < nums.length; i++){
            //去重：如果第一个就大于零 那么就没有继续的必要了
            if(nums[i] > 0){
                return result;
            }
            //去重：和前一个重复，已经讨论过的情况，不重复讨论
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            //创建左右指针
            int left = i + 1;
            int right = nums.length - 1;
            //去重：如果右指针小于0 最大小于零 没有讨论意义
            if(nums[right] < 0){
                return result;
            }

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                //和大于零 右指针向里收一收
                if(sum > 0){
                    right--;
                }else if(sum < 0){//和小于零 左指针向外走一走
                    left++;
                }else {//有结果
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //去重 将已有结果重复在数组中的数值过滤掉
                    while(right > left && nums[right] == nums[right - 1]){
                        right--;
                    }
                    while(right > left && nums[left] == nums[left + 1]){
                        left++;
                    }

                    //下一步探索
                    left++;
                    right--;
                }
            }
        }
        return result;
    }


    /**
     * 题目：18 四数之和
     */
    public static List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            //去重
            if(nums[i] > target){
                return result;
            }
            //去重 和亲一个重复的时候 之前已经讨论过 不再进行讨论
            if(i > 0 && nums[i] == nums[i -1]){
                continue;
            }
            for(int j = i + 1; j < nums.length; j++){
//                //去重
//                if(nums[j] > target - nums[i]){
//                    return result;
//                }
                //去重
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }

                //创建双指针
                int left = j + 1;
                int right = nums.length - 1;
                while(left < right){
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    //判断
                    if(sum > target){
                        right--;
                    }else if(sum < target){
                        left++;
                    }else {//找到结果
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        //去重
                        while (right > left && nums[right] == nums[right - 1]){
                            right--;
                        }
                        while (right > left && nums[left] == nums[left + 1]){
                            left++;
                        }

                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
