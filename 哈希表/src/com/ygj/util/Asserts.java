package com.ygj.util;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("����δͨ��");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
