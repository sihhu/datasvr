package cn.edu.hhuc.si.daoImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.edu.hhuc.si.dao.InfoDaoI;
import cn.edu.hhuc.si.model.TsCmdResult;
import cn.edu.hhuc.si.util.ReadSQLUtil;
import cn.edu.hhuc.si.util.TiDB;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class InfoDaoImpl implements InfoDaoI {

    private static Logger log = Logger.getLogger(InfoDaoImpl.class);

    public TsCmdResult j = new TsCmdResult();

    @SuppressWarnings("unchecked")
    @Override
    public void acExecuteSql(HttpServletRequest request) {
        try {
            String aSqlPath = request.getParameter("Path");
            String aSqlPs = request.getParameter("Ps");
            String aSql = ReadSQLUtil.ReadSqlFromFile(aSqlPath);
            Map<String, String> aPs = (Map<String, String>) JSON.parse(aSqlPs);
            boolean flag = TiDB.executeSql(aSql, aPs, j.DebugInfos);
            if (flag)
                j.setState(1);
        } catch (Exception er) {
            j.DebugInfos.add(er.getMessage());
            log.info(er.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void acGetTable(HttpServletRequest request) {
        try {
            // 获取sql路径
            String aSqlPath = request.getParameter("Path");
            // 获取sql需要的参数信息
            String aSqlPs = request.getParameter("Ps");
            // 获取sql内容
            String aSql = ReadSQLUtil.ReadSqlFromFile(aSqlPath);
            // 将参数转换成键值对
            Map<String, String> aPs = (Map<String, String>) JSON.parse(aSqlPs);
            // 获取结果集
            JSONArray aJson = TiDB.getTable(aSql, aPs, j.DebugInfos);
            // 获取拼接结果
            j.setDatas(aJson);
            // 返回状态为1
            j.setState(1);
        } catch (Exception er) {
            j.DebugInfos.add(er.getMessage());
            log.info(er.getMessage());
        }
    }

    @Override
    public String getResultJson() {
        return JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
    }



    @SuppressWarnings("unchecked")
    @Override
    public void acGetPageTable(HttpServletRequest request) {
        try {
            String aSqlPath = request.getParameter("Path");
            String aOrderFields = request.getParameter("OrderFields");
            String aPageSize = request.getParameter("PageSize");
            String aPageIndex = request.getParameter("PageIndex");
            String aSqlPs = request.getParameter("Ps");
            String aSql = ReadSQLUtil.ReadSqlFromFile(aSqlPath);
            Map<String, String> aPs = (Map<String, String>) JSON.parse(aSqlPs);
            Object aJson = TiDB.getPageTable(aSql, aOrderFields, aPageSize,
                    aPageIndex, aPs, j.DebugInfos);
            // 获取拼接结果
            j.setDatas(aJson);
            j.setState(1);
        } catch (Exception er) {
            j.DebugInfos.add(er.getMessage());
            log.info(er.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void acGetDs(HttpServletRequest request) {
        try {
            String aTables = request.getParameter("Tables");
            String aSqlPs = request.getParameter("Ps");
            Map<String, String> aTableList = (Map<String, String>) JSON
                    .parse(aTables);
            Map<String, String> aPsList = (Map<String, String>) JSON
                    .parse(aSqlPs);
            Map<String, String> aKeyTables = new HashMap<String, String>();
            for (String aTab : aTableList.keySet()) {
                String aSqlPath = aTableList.get(aTab);
                String aSql = ReadSQLUtil.ReadSqlFromFile(aSqlPath);
                aKeyTables.put(aTab, aSql);
            }
            Map<String, Object> map = TiDB.getDataSet(aKeyTables, aPsList,
                    j.DebugInfos);
            // 获取拼接结果
            j.setDatas(map);
            j.setState(1);
        } catch (Exception er) {
            j.DebugInfos.add(er.getMessage());
            log.info(er.getMessage());
        }
    }

}
