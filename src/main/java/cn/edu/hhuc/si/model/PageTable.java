package cn.edu.hhuc.si.model;

/**
 * 
 * @Package cn.edu.hhuc.si.model
 * @ClassName: PageTable
 * @Description: 分页对象
 * @author Tao
 * @date 2017年1月8日 下午8:20:28
 */
public class PageTable {
	public int PageSize = 10;
	public int PageIndex = 1;
	public int RowCount = 0;
	public int PageCount = 0;
	public String OrderFields = "";
	public String Datajson = "";

	public int getPageSize() {

		return PageSize;
	}

	public void setPageSize(int pageSize) {

		PageSize = pageSize;
	}

	public int getPageIndex() {

		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {

		PageIndex = pageIndex;
	}

	public int getRowCount() {

		return RowCount;
	}

	public void setRowCount(int rowCount) {

		RowCount = rowCount;
	}

	public int getPageCount() {

		return PageCount;
	}

	public void setPageCount(int pageCount) {

		PageCount = pageCount;
	}

	public String getOrderFields() {

		return OrderFields;
	}

	public void setOrderFields(String orderFields) {

		OrderFields = orderFields;
	}

	public String getDatajson() {

		return Datajson;
	}

	public void setDatajson(String datajson) {

		Datajson = datajson;
	}

	@Override
	public String toString() {
		return "PageTable [PageSize=" + PageSize + ", PageIndex=" + PageIndex
				+ ", RowCount=" + RowCount + ", PageCount=" + PageCount
				+ ", OrderFields=" + OrderFields + ", Datajson=" + Datajson
				+ "]";
	}

	public PageTable(int pageSize, int pageIndex, int rowCount, int pageCount,
			String orderFields, String datajson) {
		super();
		PageSize = pageSize;
		PageIndex = pageIndex;
		RowCount = rowCount;
		PageCount = pageCount;
		OrderFields = orderFields;
		Datajson = datajson;
	}

	public PageTable() {
		super();
	}

}
