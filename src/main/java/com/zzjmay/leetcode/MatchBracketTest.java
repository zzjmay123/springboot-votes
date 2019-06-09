package com.zzjmay.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 如果存在多组匹配的问题，堆栈的结构能够帮助我们解决问题
 */
public class MatchBracketTest {

    private Map<Character,Character> mappings;


    //初始化括号的匹配map
    public MatchBracketTest() {
        this.mappings = new HashMap<>();
        this.mappings.put(')','(');
        this.mappings.put('}','{');
        this.mappings.put(']','[');

    }

    /**
     * 堆栈法进行匹配
     * @param s
     * @return
     */
    public boolean isValid(String s){

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            //判断当前的括号是否是闭括号，如果是则进行出栈匹配
            if(this.mappings.containsKey(c)){
                char topElement = stack.empty()?'#':stack.pop();
                //对比栈的首元素是否相等
                if(topElement != this.mappings.get(c)){
                    return false;
                }
            }else{
                //则入栈
                stack.push(c);
            }


        }

        return stack.isEmpty();
    }


}
