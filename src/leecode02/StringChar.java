package leecode02;

public class StringChar {
    public static void main(String[] args) {
        reverseWords("  hello world  ");
        strStr("sadbutsad", "sad");
    }

    /**
     * 题目：344 反转字符串
     */
    public static void reverseString(char[] s){
        //判断
        if(s == null || s.length == 0){
            return;
        }
        //使用左右双指针
        int left = 0;
        int right = s.length - 1;
        while(left <= right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 题目：541 反转字符串II
     */
    public static String reverseStr(String s, int k){
        char[] sChar = s.toCharArray();
        int count = s.length() / (2 * k);
        for(int i = 0; i < count; i++){
            reverse(sChar, i * 2 * k, i * 2 * k + k - 1);
        }
        //处理剩余部分
        int remain = sChar.length % (2 * k);
        if(remain > k){
            reverse(sChar, count * 2 * k, count * 2 * k + k - 1);
        }else {
            reverse(sChar, count * 2 * k, sChar.length - 1);
        }
        return new String(sChar);
    }

    //创建一个利用双指针反转字符串的方法
    public static void reverse(char[] reverse, int left, int right){
        while(left <= right){
            char temp = reverse[left];
            reverse[left] = reverse[right];
            reverse[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 题目：剑指offer 05 替换空格
     */
    //使用stringbuffer进行拼接
    public String replaceSpace(String s){
        char[] sChar = s.toCharArray();
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            if(sChar[i] == ' '){
                res.append("%20");
            }else {
                res.append(sChar[i]);
            }
        }
        return res.toString();
    }

    /**
     * 题目：151 翻转字符串里的单词
     */
    //去除首尾和中间的多余空格
    //翻转整个句子
    //翻转每个单词
    public static String reverseWords(String s){
        StringBuffer sb = removeSpace(s);
        //反转整个句子
        reverse2(sb, 0, sb.length() - 1);
        //反转每个单词
        int index = 0;
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == ' '){
                reverse2(sb, index, i - 1);
                index = i + 1;
            }
        }
        reverse2(sb, index, sb.length() - 1);
        return sb.toString();
    }

    //翻转方法  前闭后闭区间
    public static void reverse2(StringBuffer sb, int start, int end){
        while(start <= end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    //去除空格方法
    public static StringBuffer removeSpace(String s){
        //去除前后空格
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' '){
            left++;
        }
        while(s.charAt(right) == ' '){
            right--;
        }
        StringBuffer sb = new StringBuffer();
        for(int i = left; i <= right; i++){
            char c = s.charAt(i);
            if(c != ' ' || s.charAt(i - 1) != ' '){
                sb.append(c);
            }
        }
        return sb;
    }

    /**
     * 题目：剑指offer 58-II 左旋转字符串
     */
    //尝试使用字符串拼接
    public static String reverseLeftWords(String s, int n){
        StringBuffer sb = new StringBuffer();
        for(int i = n; i < s.length(); i++){
            sb.append(s.charAt(i));
        }
        for(int i = 0; i < n; i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //使用char数组
    public static String reverseLeftWords2(String s, int n){
        char[] sChar = s.toCharArray();
        //反转整个字符串
        reverse2(sChar, 0, sChar.length - 1);
        //反转各个部分
        reverse2(sChar, 0, s.length() - 1 - n);
        reverse2(sChar, s.length() - n, s.length() - 1);
        return new String(sChar);
    }

    //反转方法
    public static void reverse2(char[] sChar, int start, int end){
        while(start <= end){
            char temp = sChar[start];
            sChar[start] = sChar[end];
            sChar[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 题目：28 实现 strStr()
     */
    //kmp算法
    public static int strStr(String haystack, String needle){
        if(needle.length() == 0){
            return 0;
        }
        int[] next = getNext(needle);
        int indexN = 0;
        //遍历haystack字符串，想法和求next类似
        for(int i = 0; i < haystack.length(); i++){
            while(indexN > 0 && haystack.charAt(i) != needle.charAt(indexN)){//不相同的时候needle进行回退，回退多少看kmp数组
                indexN = next[indexN - 1];
            }
            if(haystack.charAt(i) == needle.charAt(indexN)){
                indexN++;
            }
            //终止
            if(indexN == needle.length()){
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    public static int[] getNext(String s){
        int n = s.length();
        int[] next = new int[n];
        int slow = 0;
        for(int fast = 1; fast < n; fast++){
            while(slow > 0 && s.charAt(fast) != s.charAt(slow)){
                //回退
                slow = next[slow - 1];
            }
            if(s.charAt(fast) == s.charAt(slow)){
                slow++;
            }
            next[fast] = slow;
        }
        return next;
    }

    /**
     * 题目：459 重复的子字符串
     */
    //kmp 先使用数学进行推导
    //如果字符串是由重复子串构成的 那么必然 s=n*x x为子串 那么最长的相同前后缀长度一定不包含s本身 为m*x 而且n-m = 1
    //所以如果nx%(n-m)x=0 就可以判断有重复出现是子字符串
    public static boolean repeatedSubstringPattern(String s){
        //先求s的kmp
        int len = s.length();
        int[] next = new int[len];
        int index = 0;
        for(int i = 1; i < len; i++){
            while(index > 0 && s.charAt(i) != s.charAt(index)){
                //index回退
                index = next[index - 1];
            }
            if(s.charAt(i) == s.charAt(index)){
                index++;
            }
            next[i] = index;
        }

        //通过kmp判读子串
        if(next[len - 1] > 0 && len % (len - next[len - 1]) == 0){
            return true;
        }
        return false;
    }

}
