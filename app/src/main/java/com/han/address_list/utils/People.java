package com.han.address_list.utils;

public class People extends Contact {

	/** 显示数据拼音的首字母 */
	public String sortLetters;

	public SortToken sortToken = new SortToken();

	public People(String name, String number, String sortKey) {
		super(name, number, sortKey);
	}

	@Override
	public String toString() {
		return "People [sortLetters=" + sortLetters + ", sortToken=" + sortToken.toString() + "]";
	}
}
