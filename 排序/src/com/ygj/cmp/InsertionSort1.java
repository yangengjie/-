package com.ygj.cmp;

import com.ygj.Sort;

/**
 *��������
 * @param <T>
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		//beginĬ��Ϊ1��indexΪ0����Ϊ��ͷ������ģ������Ԫ���Ǵ������
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			//������ֱ�ǰ���Ԫ��С�ͽ���λ�ã�ֱ��������ǰ��Ԫ�ش���ǰ���Ԫ��
			//�������ǻ��curǰ������Ԫ�ؽ��бȽϣ�������λ��
			while (cur > 0 && cmp(cur, cur - 1) < 0) {
				swap(cur, cur - 1);
				cur--;
			}
		}
	}
}
