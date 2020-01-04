package cn.tedu.vo;

import java.util.List;

/**
 * 
 * @author HaiDi
 * @version 下午2:55:22
 * @email 2449365663@qq.com
 */
public class PageObject<T> {
	private Integer rowCount; // 总记录数
	private Integer pageCount; // 总页数
	private Integer pageCurrent; // 当前页
	private List<T> records; // 数据

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "PageObject [rowCount=" + rowCount + ", pageCount=" + pageCount
				+ ", pageCurrent=" + pageCurrent + ", records=" + records + "]";
	}

}
