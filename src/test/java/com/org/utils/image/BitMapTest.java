package com.org.utils.image;

public class BitMapTest {

	public void test() {

		BitMap bm = new BitMap(Integer.MAX_VALUE);
		int searchNum = 56;
		int[] numArr = { 19, 64, 45, 56, 0, 54, 28, 2, 23, 34, 40, 18, 54, 50,
				49, 29, 20, 31, 47, 30, 24, 17, 50, 57, 33, 55, 21, 22, 27, 45,
				3, 19, 17, 49, 24, 5, 15, 24, 27, 35, 6, 53, 9, 61, 4, 6, 12,
				23, 52, 48, 39, 39, 21, 1, 11 };
		bm.add(numArr);
		for (int i : numArr)
			System.out.println(bm.getIndex(i));

		System.out.println(bm.getIndex(searchNum));
	}
}
