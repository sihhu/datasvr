package cn.edu.hhuc.si.servlet.base;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


/**
 * @author Tao
 * @Package cn.edu.hhuc.si.servlet.base
 * @ClassName: BaseServlet
 * @Description: 基类
 * @date 2017年1月11日 下午8:18:47
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 将对象转换成JSON字符串，并响应回前台
     *
     * @param object
     * @throws IOException
     */
    public void writeJson(Object object, HttpServletResponse response) {
        try {
            String json = JSON.toJSONStringWithDateFormat(object,
                    "yyyy-MM-dd HH:mm:ss");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(String jsonStr, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonStr);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
