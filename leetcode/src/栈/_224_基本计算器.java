package Õ»;

import java.util.Stack;

//https://leetcode-cn.com/problems/basic-calculator/
public class _224_»ù±¾¼ÆËãÆ÷ {
	public int calculate(String s) {
		int sum = 0;
		Stack<Integer> stack = new Stack<>();
		int temp = 1;
		int n = 0;
		for (char c : s.trim().toCharArray()) {
			if (c == '(' || c == ')' || c == ' ')
				continue;
			if (c == '+') {
				temp = 1;
				n = 0;
			} else if (c == '-') {
				temp = -1;
				n = 0;
			} else {
				n = n * 10 + (c - '0');
				System.out.println(n);
			}
		}
		return sum;
	}
}
