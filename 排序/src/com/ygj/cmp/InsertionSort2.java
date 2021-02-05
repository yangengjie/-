package com.ygj.cmp;

import com.ygj.Sort;

/**
 * ����������Ż���Ԫ��λ�ý��� �ĳ� Ԫ�ص�Ų��
 * 
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			T v = array[cur];
			while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
				//ͷ���������ݱȴ������Ԫ�ش��Ԫ�����Ų��1��λ��
				array[cur] = array[cur - 1];
				cur--;
			}
			//cur��������Ҫ�����λ��
			array[cur] = v;
		}
	}

}
