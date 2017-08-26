package cn.edu.hhuc.si.dao;

import javax.servlet.http.HttpServletRequest;

public interface InfoDaoI {

    /**
     * sql执行操作，目标是单个表
     */
    void acExecuteSql(HttpServletRequest request);

    /**
     * sql执行查询操作，目标是单个表
     */
    void acGetTable(HttpServletRequest request);

    /**
     * 获得Json返回信息
     */
    String getResultJson();

    /**
     * sql执行查询操作，目标是单个表分页
     */
    void acGetPageTable(HttpServletRequest request);

    /**
     * sql执行查询操作，目标是多个表
     */
    void acGetDs(HttpServletRequest request);

}
