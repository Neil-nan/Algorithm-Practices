package leecode03;

import java.util.HashMap;
import java.util.Map;

public class StringChar {

    /**
     * 题目：3 无重复字符的最长子串
     */
    public static int lengthOfLongestSubstring(String s){
        //key 字符 value 位置
        Map<Character, Integer> cnt = new HashMap<>();
        int res = 0;
        int len = s.length();
        int index = -1;//左指针
        for(int i = 0; i < len; i++){
            if(cnt.containsKey(s.charAt(i))){//更新左指针
                index = Math.max(index, cnt.get(s.charAt(i)));
            }
            res = Math.max(res, i - index);
            cnt.put(s.charAt(i), i);//更新map
        }
        return res;
    }
}
