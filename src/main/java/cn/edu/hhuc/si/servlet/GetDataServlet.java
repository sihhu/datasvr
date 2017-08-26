package cn.edu.hhuc.si.servlet;


import cn.edu.hhuc.si.dao.InfoDaoI;
import cn.edu.hhuc.si.daoImpl.InfoDaoImpl;
import cn.edu.hhuc.si.servlet.base.BaseServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetData.do")
public class GetDataServlet extends BaseServlet {
    private static Logger log = Logger.getLogger(GetDataServlet.class);
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String aAction = request.getParameter("Action");
        InfoDaoI dData = new InfoDaoImpl();
        String aJson = "";
        try {
            try {
                // 根据前台请求的Action进行操作
                switch (aAction) {
                    case "acExecuteSql":
                        dData.acExecuteSql(request);
                        break;
                    case "acGetTable":
                        dData.acGetTable(request);
                        break;
                    case "acGetDs":
                        dData.acGetDs(request);
                        break;
                    case "acGetPageTable":
                        dData.acGetPageTable(request);
                        break;
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        } catch (Exception e) {

        }
        aJson = dData.getResultJson();
        super.writeJson(aJson, response);
    }
}
