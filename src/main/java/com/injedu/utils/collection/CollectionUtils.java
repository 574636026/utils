package com.injedu.utils.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * 集合工具类
 *
 * @author joy.zhou
 * @date 2015年12月5日
 * @version 1.0
 *
 */
public class CollectionUtils {

	/**
	 * 
	 * 切分数组(可用于分组处理数据)
	 * 
	 * 
	 * @param <T>
	 *            集合元素类型
	 * 
	 * @param surplusAlaDatas
	 *            原数据
	 * 
	 * @param partSize
	 *            每部分数据的长度
	 * 
	 * @return 切取出的数据或null
	 */
	public static <T> List<List<T>> cellList(List<T> surplusAlaDatas,
			int partSize) {
		if (surplusAlaDatas == null || surplusAlaDatas.size() <= 0) {
			return null;
		}
		final List<List<T>> currentAlaDatas = new ArrayList<List<T>>();
		int size = surplusAlaDatas.size();
		// 切割
		if (size > partSize) {

			// 添加整除项
			for (int i = 0; i < size / partSize; i++) {
				currentAlaDatas.add(surplusAlaDatas.subList(i * partSize, i
						* partSize + partSize));
			}
			// 添加余下项
			if (size % partSize > 0) {
				currentAlaDatas.add(surplusAlaDatas.subList(size
						- (size % partSize), size));
			}

		} else {
			currentAlaDatas.add(surplusAlaDatas);
		}
		return currentAlaDatas;
	}

}
