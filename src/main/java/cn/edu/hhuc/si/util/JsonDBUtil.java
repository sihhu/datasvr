package cn.edu.hhuc.si.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Tao
 * @Package cn.edu.hhuc.si.util
 * @ClassName: JsonDBUtil
 * @Description: 操作数据库查询的结果集，返回String、JSONArray
 * @date 2017年1月10日 下午12:53:06
 */
public class JsonDBUtil {
    public static String rSetToJson(ResultSet rs) throws SQLException,
            JSONException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array.toString();
    }

    public static JSONArray rSToJson(ResultSet rs) throws SQLException,
            JSONException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array;
    }

}
