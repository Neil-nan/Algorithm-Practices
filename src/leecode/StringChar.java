package leecode;

public class StringChar {

    /**
     * 题目：344 反转字符串
     */
    public static void reverseString(char[] s){
        //使用双指针
        int len = s.length;
        int left = 0;
        int right = len - 1;
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
        int len = s.length();
        char[] reverse = s.toCharArray();
        int leave = len % (2 * k);//剩余字符串
        int time = len / (2 * k);//反转次数
        for(int i = 0; i < time; i++){
            reverse(reverse, i * 2 * k, i * 2 * k + k - 1);
        }
        //判断剩余字符
        if(leave < k){
            //将剩余部分进行反转
            reverse(reverse, time * 2 * k, s.length() - 1);
        }else {
            reverse(reverse, time * 2 * k, time * 2 * k + k - 1);
        }

        return new String(reverse);

    }

    //反转字符(前闭后闭区间)
    public static void reverse(char[] chars, int left, int right){
        while(left <= right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

    }

    /**
     * 题目：剑指offer 05 替换空格
     */
    public static String replaceSpace1(String s){
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ' '){
                sb.append("%20");
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    //使用双指着方法(感觉没什么用)
    public static String replaceSpace2(String s){
        if(s == null || s.length() == 0){
            return s;
        }
        //使用stringbuffer扩充空间
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                str.append("  ");//扩充两个空间
            }
        }
        //若是没有空格直接返回
        if(str == null){
            return s;
        }
        //创建左右指针 从后向前进行遍历
        int left = s.length() - 1;//左指针 指向原始字符串的最后一个位置
        s += str.toString();
        int right = s.length() - 1;//右指针 指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while(left >= 0){
            if(chars[left] == ' '){
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            }else {
                chars[right] = chars[left];
            }
            right--;
            left--;
        }
        return new String(chars);
    }

    /**
     * 题目：151 翻转字符串里的单词
     */
    public static String reverseWords(String s){
        StringBuffer sb = removeSpace(s);
        reverseString(sb, 0, sb.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    //去除首尾以及中间多余空格
    public static StringBuffer removeSpace(String s){
        int start = 0;
        int end = s.length() - 1;
        while(s.charAt(start) == ' '){
            start++;
        }
        while(s.charAt(end) == ' '){
            end--;
        }
        StringBuffer sb = new StringBuffer();
        while(start <= end){
            char c = s.charAt(start);
            if(c != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    //反转字符串
    public static void reverseString(StringBuffer sb, int start, int end){
        while(start <= end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    //反转单词
    public static void reverseEachWord(StringBuffer sb){
        int start = 0;
        int end = 1;
        int n = sb.length();
        while(start < n){
            while(end < n && sb.charAt(end) != ' '){
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }

    /**
     * 题目：剑指offer 58-II 左旋转字符串
     */
    //反转整个字符串并进行分别旋转
    public static String reverseLeftWords(String s, int n){
        char[] chars = s.toCharArray();
        //反转整个字符串
        reverse1(chars, 0, s.length() - 1);
        //反转各自部分
        reverse1(chars, 0, s.length() - n - 1);
        reverse1(chars, s.length() - n, s.length() - 1);

        return new String(chars);
    }

    public static void reverse1(char[] chars, int start, int end){
        while(start <= end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 题目：28 实现strStr()
     */
    //使用kmp算法
    public static int strStr(String haystack, String needle){
        if(needle.length() == 0){
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0;//小字符串指针
        for(int i = 0; i < haystack.length(); i++){
            while(j > 0 && needle.charAt(j) != haystack.charAt(i)){
                j = next[j - 1];
            }
            if(needle.charAt(j) == haystack.charAt(i)){
                j++;
            }
            if(j == needle.length()){
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    //求next数组
    //i 后缀的末尾 j前缀的末尾
    public static void getNext(int[] next, String s){
        int j = 0;
        next[0] = 0;
        for(int i = 1; i < s.length(); i++){
            //首先前缀和后缀现在是相等的，但是下一个是不相等的，前缀的指着要向后退
            //缩小到使前缀的前缀等于后缀的后缀，那么也就相当于前缀的前缀等于前缀的后缀
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i] = j;
        }
    }

    /**
     * 题目：459 重复的字符串
     */
    //自己做的 有问题
    public static boolean repeatedSubstringPattern1(String s){
        //找到前缀为1前的字符个数 求next数组
        //求kmp数组
        int j = 0;
        int[] next = new int[s.length()];
        next[0] = 0;
        for(int i = 1; i < s.length(); i++){
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        int index;
        for(index = 0; index < s.length(); index++){
            if(next[index] != 0){
                break;
            }
        }
        if(next[next.length - 1] != 0 && next[next.length - 1] % index == 0){
            return true;
        }
        return false;
    }

    //kmp 先使用数学进行推导
    //如果字符串是由重复子串构成的 那么必然 s=n*x x为子串 那么最长的相同前后缀长度一定不包含s本身 为m*x 而且n-m = 1
    //所以如果nx%(n-m)x=0 就可以判断有重复出现是子字符串
    public static boolean repeatedSubstringPattern2(String s){
        //先求s的kmp
        int j = 0;//前缀的最后一个
        int len = s.length();
        int[] next = new int[len];
        next[0] = 0;
        for(int i = 1; i < len; i++){//后缀的最后一个
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i] = j;
        }

        //通过kmp判断子串
        if(next[len - 1] > 0 && len % (len - next[len - 1]) == 0){
            return true;
        }
        return false;
    }


    
}
