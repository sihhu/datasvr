package cn.edu.hhuc.si.controller;

import cn.edu.hhuc.si.common.ActionType;
import cn.edu.hhuc.si.dao.InfoDaoI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


//@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@Controller
public class DvBasicAPI {

    private static Logger log = LoggerFactory.getLogger(DvBasicAPI.class);

    @Autowired
    private InfoDaoI dData;

    public DvBasicAPI() {
    }

    @CrossOrigin
    @RequestMapping(value = "/getData", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getData(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException, SQLException {
        log.debug("Function Info: {}", "DvBasicAPI.getData");
        String aAction = request.getParameter("Action");
        ActionType aActionType = ActionType.valueOf(aAction);
        String aJson = "";
        try {
            switch (aActionType) {
                case acExecuteSql:
                    dData.acExecuteSql(request);
                    break;
                case acGetTable:
                    dData.acGetTable(request);
                    break;
                case acGetDs:
                    dData.acGetDs(request);
                    break;
                case acGetPageTable:
                    dData.acGetPageTable(request);
                    break;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        aJson = dData.getResultJson();
        return aJson;
    }

    @RequestMapping({"/"})
    public String home() {
        log.debug("Function Info: {}", "HomeController.home");
        return "/home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String select(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException, SQLException {
        log.debug("{\"age\": \"16\"}");
        return "{\"age\": \"16\"}";
    }
}
