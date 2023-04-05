package leecode02;

import java.util.*;

public class HashTab {

    /**
     * 题目：242 有效的字母异位词
     */
    //使用数组
    public static boolean isAnagram(String s, String t){
        int[] cnt = new int[26];
        //记录s
        int lenS = s.length();
        int lenT = t.length();
        if(lenS != lenT){
            return false;
        }

        for(int i = 0; i < lenS; i++){
            cnt[s.charAt(i) - 'a']++;
        }
        //删除t并进行检查
        for(int i = 0; i < lenT; i++){
            cnt[t.charAt(i) - 'a']--;
        }
        for (int i : cnt) {
            if(i != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：383 赎金信
     */
    public static boolean canConstruct(String ransomNote, String magazine){
        int lenM = magazine.length();
        int lenR = ransomNote.length();
        if(lenM < lenR){
            return false;
        }
        //创建篮子
        int[] cnt = new int[26];
        for(int i = 0; i < lenM; i++){
            cnt[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0; i < lenR; i++){
            cnt[ransomNote.charAt(i) - 'a']--;
        }
        for (int i : cnt) {
            if(i < 0){
                return false;
            }
        }
        return true;
    }

    //使用hash表
    public static boolean canConstruct2(String ransomNote, String magazine){
        if(magazine.length() < ransomNote.length()){
            return false;
        }
        //创建hash key 字母 value 个数
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < magazine.length(); i++){
            char mag = magazine.charAt(i);
            map.put(mag, map.getOrDefault(mag, 0) + 1);
        }
        for(int i = 0; i < ransomNote.length(); i++){
            char ran = ransomNote.charAt(i);
            map.put(ran, map.getOrDefault(ran, 0) - 1);
            if(map.get(ran) < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：49 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs){
        //创建hash key 单词排序后 value 单词
        Map<String, List<String>> map = new HashMap<>();
        //遍历字符串数组
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            /**
             * 这里注意
             */
            String key = new String(chars);
            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(str);
            map.put(key, value);
        }
        //创建结果
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            result.add(stringListEntry.getValue());
        }
        return result;
    }

    /**
     * 题目：438 找到字符串中所有字母异位词
     */
    public static List<Integer> findAnagrams(String s, String p){
        List<Integer> result = new ArrayList<>();
        //使用数组
        if(s.length() < p.length()){
            return result;
        }
        int[] cntS = new int[26];//不断刷新比较
        int[] cntT = new int[26];//进行比较
        //先装第一组
        for(int i = 0; i < p.length(); i++){
            cntS[s.charAt(i) - 'a']++;
            cntT[p.charAt(i) - 'a']++;
        }
        //判断相等
        if(Arrays.equals(cntS, cntT)){
            result.add(0);
        }
        for(int i = p.length(); i < s.length(); i++){
            cntS[s.charAt(i) - 'a']++;
            cntS[s.charAt(i - p.length()) - 'a']--;
            //判断
            if(Arrays.equals(cntS, cntT)){
                result.add(i - p.length() + 1);
            }
        }
        return result;


    }

    /**
     * 题目：349 两个数组的交集
     */
    public static int[] intersection(int[] nums1, int[] nums2){
        //set集合
        Set<Integer> cnt = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i : nums1) {
            cnt.add(i);
        }
        for (int i : nums2) {
            if(cnt.contains(i)){
                res.add(i);
            }
        }
        int[] result = new int[res.size()];
        int index = 0;
        for (Integer integer : res) {
            result[index++] = integer;
        }
        return result;
    }

    /**
     * 题目：350 两个数组的交集 II
     */
    //使用双指针 + 排序
    public static int[] intersect(int[] nums1, int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] ans = new int[Math.min(len1, len2)];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index1 < len1 && index2 < len2){
            if(nums1[index1] < nums2[index2]){
                index1++;
            }else if(nums1[index1] > nums2[index2]){
                index2++;
            }else {
                ans[index] = nums1[index1];
                index++;
                index1++;
                index2++;
            }
        }
        return Arrays.copyOfRange(ans, 0, index);

    }

    public static int[] intersect1(int[] nums1, int[] nums2){
        //判断
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return new int[0];
        }
        //需要记录数值和个数
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //遍历记录数组2
        for (int num : nums2) {
            if(map.getOrDefault(num, 0) > 0){
                res.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }

    /**
     * 题目：202 快乐数
     */
    public static boolean isHappy(int n){
        Set<Integer> cnt = new HashSet<>();
        while(n != 1 && !cnt.contains(n)){
            cnt.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    public static int getNextNum(int n){
        int res = 0;
        while(n > 0){
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
        //创建hash表 key 数值 value 位置
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                res[1] = i;
                res[0] = map.get(target - nums[i]);
                return res;
            }else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    /**
     * 题目：454 四数相加II
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int res = 0;
        //记录a + b 的可能性和个数 key a + b value 个数
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                int key = nums1[i] + nums2[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        //记录 c + d
        for(int i = 0; i < nums3.length; i++){
            for(int j = 0; j < nums4.length; j++){
                int key = 0 - nums3[i] - nums4[j];
                if(map.containsKey(key)){
                    res += map.get(key);
                }
            }
        }
        return res;
    }

    /**
     * 题目：383 赎金信
     */
    //使用字母
    public static boolean canConstruct3(String ransomNote, String magazine){
        //判断
        if(ransomNote.length() > magazine.length()){
            return false;
        }
        //字母使用数组
        int[] cnt = new int[26];
        //记录magazine
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        //除去ransomNote
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0){
                return false;
            }
        }
        return true;
    }

    //使用hash
    public static boolean canConstruct4(String ransomNote, String magazine){
        if(magazine == null || magazine.length() == 0){
            return false;
        }
        if(magazine.length() < ransomNote.length()){
            return false;
        }
        //记录magazine key 字母 value 个数
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < magazine.length(); i++){
            char m = magazine.charAt(i);
            map.put(m, map.getOrDefault(m, 0) + 1);
        }
        //扣除ransoNote 并检查
        for(int i = 0; i < ransomNote.length(); i++){
            char ran = ransomNote.charAt(i);
            if(map.getOrDefault(ran, 0) > 0){
                map.put(ran, map.get(ran) - 1);
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：15 三数之和
     */
    //和回溯的 40组合总和II 的去重相似
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if(nums[0] > 0){
            return result;
        }
        //a + b + c for循环a
        for(int i = 0; i < nums.length - 2; i++){
            if(nums[i] > 0){
                return result;
            }
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            if(nums[right] < 0){
                return result;
            }
            //左闭右闭
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0){
                    right--;
                }else if(sum < 0){
                    left++;
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    //去重
                    while(right > left && nums[right] == nums[right - 1]){
                        right--;
                    }
                    while(right > left && nums[left] == nums[left + 1]){
                        left++;
                    }
                    right--;
                    left++;
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
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1; j < nums.length - 2; j++){
                //去重
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }

                //创建指针
                int left = j + 1;
                int right = nums.length - 1;
                while(left < right){
                    //注意
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum > target){
                        right--;
                    }else if(sum < target){
                        left++;
                    }else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        //去重
                        while(left < right && nums[right] == nums[right - 1]){
                            right--;
                        }
                        while(left < right && nums[left] == nums[left + 1]){
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
