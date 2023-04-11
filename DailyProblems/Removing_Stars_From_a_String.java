package DailyProblems;

import java.util.Stack;

//https://leetcode.com/problems/removing-stars-from-a-string/
public class Removing_Stars_From_a_String {
    public String removeStars(String s) {
        String ans = "";
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(ch != '*') st.push(ch);
            else st.pop();
        }
        while(st.size() > 0){
            ans += st.pop();
        }
        return new StringBuilder(ans).reverse().toString();
    }
}
