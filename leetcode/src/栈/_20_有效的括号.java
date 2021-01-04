package Õ»;

import java.util.HashMap;
import java.util.Stack;
//https://leetcode-cn.com/problems/valid-parentheses/submissions/
public class _20_ÓÐÐ§µÄÀ¨ºÅ {
	private static HashMap<Character, Character> map = new HashMap();
	static {
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
	}

	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty())
					return false;
				if (c != map.get(stack.pop()))
					return false;
			}

		}
		return stack.isEmpty();
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.isEmpty())
					return false;
				char left = stack.pop();
				if (left == '(' && c != ')')
					return false;
				if (left == '[' && c != ']')
					return false;
				if (left == '{' && c != '}')
					return false;
			}

		}
		return stack.isEmpty();
	}
}
