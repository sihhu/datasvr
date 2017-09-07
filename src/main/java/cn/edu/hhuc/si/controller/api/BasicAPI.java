package cn.edu.hhuc.si.controller.api;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class BasicAPI {

    private static Logger log = LoggerFactory.getLogger(BasicAPI.class);

    public BasicAPI() {

    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, InterruptedException, IOException, ServletException, SQLException {
        log.debug("Function Info: {}", "DvBasicAPI.fileupload");
    }

    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    @ResponseBody
    public String postData(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException, SQLException {
        log.debug("Function Info: {}", "DvBasicAPI.postData");
        return "";
    }

    @RequestMapping(value = "/getDatas", method = RequestMethod.GET)
    @ResponseBody
    public String getDatas(@RequestParam(value = "query") String arg) throws JsonGenerationException, JsonMappingException, IOException, InterruptedException, SQLException {
        log.debug("Function Info: {}", "DvBasicAPI.getData");
        return "";
    }

}
