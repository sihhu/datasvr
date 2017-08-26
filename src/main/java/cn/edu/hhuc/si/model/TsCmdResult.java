package cn.edu.hhuc.si.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wan
 * @Package cn.edu.hhuc.si.model
 * @ClassName: TsCmdResult
 * @Description: JSON模型
 * @date 2016年12月31日 下午8:04:04
 */
public class TsCmdResult implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public int State = 0;// 是否成功
    public Object Datas = "";// 其他信息
    public List<String> DebugInfos = new ArrayList<String>();// 提示信息

    public int getState() {

        return State;
    }

    public void setState(int state) {

        State = state;
    }

    public Object getDatas() {

        return Datas;
    }

    public void setDatas(Object datas) {

        Datas = datas;
    }

    public List<String> getDebugInfos() {

        return DebugInfos;
    }

    public void setDebugInfos(List<String> debugInfos) {

        DebugInfos = debugInfos;
    }

}
