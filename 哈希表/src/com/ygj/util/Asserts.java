package com.ygj.util;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("≤‚ ‘Œ¥Õ®π˝");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
