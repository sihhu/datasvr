package cn.edu.hhuc.si.servlet;

import cn.edu.hhuc.si.util.ConfigFactory;
import cn.edu.hhuc.si.util.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Tao
 * @version V1.0
 * @Package cn.edu.hhuc.si.servlet
 * @ClassName: InitServlet
 * @Description: 初始化Servlet
 * @date 2015年12月4日 上午9:09:07
 */
public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        // load properties
        ConfigFactory config = ConfigFactory.Instance();
        DBUtils instance = DBUtils.Instance();
    }
}
