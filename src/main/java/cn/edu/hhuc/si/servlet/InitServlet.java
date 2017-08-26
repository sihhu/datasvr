package cn.edu.hhuc.si.servlet;

import cn.edu.hhuc.si.servlet.base.BaseServlet;
import cn.edu.hhuc.si.util.ConfigFactory;
import cn.edu.hhuc.si.util.DBUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * @author Tao
 * @version V1.0
 * @Package cn.edu.hhuc.si.servlet
 * @ClassName: InitServlet
 * @Description: 初始化Servlet
 * @date 2015年12月4日 上午9:09:07
 */
@WebServlet(name = "/Init.do")
public class InitServlet extends BaseServlet {

    private static Logger log = Logger.getLogger(InitServlet.class);

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        // load properties
        ConfigFactory config = ConfigFactory.Instance();
        DBUtils instance = DBUtils.Instance();
    }
}
