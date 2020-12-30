package 数组;

//https://leetcode-cn.com/problems/first-unique-character-in-a-string/
public class _387_字符串中的第一个唯一字符 {
	public static int firstUniqChar(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char e = chars[i];
			if (s.indexOf(e) == s.lastIndexOf(e))
				return i;
		}
		return -1;
	}

	public static int firstUniqChar2(String s) {
		// 只循环了26次
		int result = s.length();
		for (char c = 'a'; c <= 'z'; c++) {
			int firstIndex = s.indexOf(c);
			int lastIndex = s.lastIndexOf(c);

			// 如果相等表示不重复
			if (firstIndex != -1 && lastIndex == firstIndex) {
				result = Math.min(firstIndex, result);
			}
		}
		if (result != s.length()) {
			return result;
		}
		return -1;
	}
}
