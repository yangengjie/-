package ����;

import java.util.ArrayList;
import java.util.List;

public class _989_������ʽ�������ӷ� {
	public  List<Integer> addToArrayForm(int[] A, int K) {
		List<Integer> resultList = new ArrayList<>();
		int temp = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			int sum = A[i] + temp + K % 10;
			K /= 10;
			if (sum >= 10) {
				temp = 1;
				resultList.add(0, sum - 10);
			} else {
				temp = 0;
				resultList.add(0, sum);
			}
		}
		K += temp;
		while (K != 0) {
			resultList.add(0, K % 10);
			K /= 10;
		}

		return resultList;
	}
}
