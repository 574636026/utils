package com.org.utils.image;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 */
public class BitMap {
	/**
	 * bitmap（位图）的方式节约空间
	 */
	private final int[] bitmap;
	private final int size;

	public BitMap(final int size) {
		this.size = size;
		int sLen = ((size % 32) == 0) ? size / 32 : size / 32 + 1;
		this.bitmap = new int[sLen];
	}

	private static int _Index(final int number) {
		return number / 32;
	}

	private static int _Position(final int number) {
		return number % 32;
	}

	private void adjustBitMap(final int index, final int position) {
		int bit = bitmap[index] | (1 << position);
		bitmap[index] = bit;
	}

	public void add(int[] numArr) {
		for (int i = 0; i < numArr.length; i++)
			add(numArr[i]);
	}

	public void add(int number) {
		adjustBitMap(_Index(number), _Position(number));
	}

	public boolean getIndex(final int index) {
		if (index > size)
			return false;

		int bit = (bitmap[_Index(index)] >> _Position(index)) & 0x0001;
		return (bit == 1);
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer(size);
		for (Integer i : bitmap) {
			StringBuffer tmp = new StringBuffer(32);
			String bits = Integer.toBinaryString(i);
			for (int b = 0; b < 32 - bits.length(); b++)
				tmp.append(0);
			tmp.append(bits);
			sbf.append(tmp.reverse());
		}
		String s = sbf.substring(0, size).toString();
		// System.out.println("bitmap length: " + bitmap.length + " \r\nsize:" +
		// size);
		return StringUtils.reverse(s);
	}

}
