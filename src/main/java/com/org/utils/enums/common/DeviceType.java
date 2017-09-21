package com.org.utils.enums.common;

/**
 * 设备类型
 *
 * @author joy.zhou
 * @date 2015年11月2日
 * @version 1.0
 *
 */
public enum DeviceType {
	/** Android */
	ANDROID(1),
	/** IOS */
	IOS(2);

	private int type;

	DeviceType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public static DeviceType valueOf(int type) {
		for (DeviceType mobileType : DeviceType.values()) {
			if (mobileType.getType() == type) {
				return mobileType;
			}
		}
		return null;
	}

}
