package com.runmit.clotho.core.dto;

import java.util.List;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月25日
 */

public class ExtEntity<T> {
	private int result = 0;
	private List<T> rows;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
