package cn.edu.hhuc.si.util;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hhuc.si.model.PageTable;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @Package cn.edu.hhuc.si.util
 * @ClassName: TiDB
 * @Description: 操作数据库的具体方法
 * @author Tao
 * @date 2017年1月18日 下午12:54:09
 */
public class TiDB {
	private static Logger log = Logger.getLogger(TiDB.class.getName());

	static TiDB _tDB = null;

	// 单例
	public static TiDB geTiDB() {
		if (_tDB == null) {
			_tDB = new TiDB();
		}
		return _tDB;
	}

	public static Boolean executeSql(String aSql, List<String> aErrors) {
		Boolean aFlag = false;
		try {
			DBUtils.Execute(aSql);
			aFlag = true;
		} catch (Exception er) {
			if (aErrors != null) {
				aErrors.add(aSql);
				aErrors.add(er.getMessage());
			}
			log.info(er.getMessage());
		}
		return aFlag;
	}

	public static Boolean executeSql(String aSql, Map<String, String> aPs,
			List<String> aErrors) {
		String sql = getSql(aSql, aPs, aErrors);
		return executeSql(sql, aErrors);
	}

	/**
	 * 
	 * @Title: getSql
	 * @Description: 替换sql参数
	 * @param aSql
	 * @param aPs
	 *            参数
	 * @param aErrors
	 * @return String
	 */
	public static String getSql(String aSql, Map<String, String> aPs,
			List<String> aErrors) {
		try {
			for (String aKey : aPs.keySet()) {
				String aOld = "{" + aKey + "}";
				// String aNew = aPs.get(aKey);//可能是Int类型
				String aNew = String.valueOf(aPs.get(aKey));
				aSql = aSql.replace(aOld, aNew);
			}
		} catch (Exception er) {
			aErrors.add(er.getMessage());
			log.info(er.getMessage());
		}
		log.info("sql: " + aSql);
		return aSql;
	}

	public static JSONArray getTable(String aSql, Map<String, String> aPs,
			List<String> aErrors) {
		// 获得真正sql语句
		String sql = getSql(aSql, aPs, aErrors);
		return getTableInfo(sql, aErrors);
	}

	private static JSONArray getTableInfo(String aSql, List<String> aErrors) {
		JSONArray aDBJson = null;
		try {
			ResultSet rs = DBUtils.Select(aSql);
			aDBJson = JsonDBUtil.rSToJson(rs);
		} catch (Exception er) {
			if (aErrors != null) {
				aErrors.add(aSql);
				aErrors.add(er.getMessage());
			}
			log.info(er.getMessage());
		}
		return aDBJson;
	}

	public static PageTable getPageTable(String aSql, String aOrderFields,
			String aPageSize, String aPageIndex, Map<String, String> aPs,
			List<String> aErrors) {
		PageTable aRes = new PageTable();
		ResultSet rs = null;
		// 获得真正sql语句
		aSql = getSql(aSql, aPs, aErrors);
		try {
			aRes.PageSize = Integer.valueOf(aPageSize);
			aRes.OrderFields = aOrderFields;
			String aRowCountSql = "Select COUNT(*) as F_RowCount from ( "
					+ aSql + " ) a";
			log.info("aRowCountSql: " + aRowCountSql);
			try {
				rs = DBUtils.Select(aRowCountSql);
				rs.next();
				aRes.RowCount = rs.getInt("F_RowCount");
			} catch (Exception e) {
				aErrors.add(e.getMessage());
			}
			if (aRes.RowCount == 0) {

			} else {
				aRes.PageCount = (int) Math.ceil(aRes.RowCount * 1.0
						/ aRes.PageSize);
				aRes.PageIndex = Integer.valueOf(aPageIndex);
				aRes.PageIndex = aRes.PageIndex > aRes.PageCount ? aRes.PageCount
						: aRes.PageIndex;
				aRes.PageIndex = aRes.PageIndex < 1 ? 1 : aRes.PageIndex;
				String aDataSql = "Select * from (	Select ROW_NUMBER() over (order By [OrderFields]) as F_RowNumber , a.* from ( [SrcSql]) a) s where F_RowNumber<= [PageIndex] * [PageSize] and F_RowNumber> ([PageIndex]-1)*[PageSize]";
				aDataSql = aDataSql.replace("[SrcSql]", aSql);
				aDataSql = aDataSql.replace("[OrderFields]", aOrderFields);
				aDataSql = aDataSql.replace("[PageSize]",
						String.valueOf(aRes.PageSize));
				aDataSql = aDataSql.replace("[PageIndex]",
						String.valueOf(aRes.PageIndex));
				log.info("pageTableSql: " + aDataSql);
				rs = DBUtils.Select(aDataSql);
				aRes.Datajson = JsonDBUtil.rSetToJson(rs);
			}

		} catch (Exception er) {
			if (aErrors != null) {
				aErrors.add(aSql);
				aErrors.add(er.getMessage());
			}
			log.info(er.getMessage());
		}
		return aRes;
	}

	/* 重载函数 */
	public static PageTable getPageTable(String aSql, String orderFields,
										 int pageSize, int pageIndex, Map<String, String> aPs,
										 List<String> aErrors) {
		return getPageTable(aSql, orderFields, String.valueOf(pageSize),
				String.valueOf(pageIndex), aPs, aErrors);
	}

	public static Map<String, Object> getDataSet(Map<String, String> aTables,
			Map<String, String> aPs, List<String> aErrors) {
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			for (String aTable : aTables.keySet()) {
				JSONArray aJson = getTable(aTables.get(aTable), aPs, aErrors);
				maps.put(aTable, aJson);
			}
		} catch (Exception er) {
			log.info(er.getMessage());
			aErrors.add(er.getMessage());
		}
		return maps;
	}

}
