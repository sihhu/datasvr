package cn.edu.hhuc.si.service.impl;

import cn.edu.hhuc.si.service.InitServiceI;
import cn.edu.hhuc.si.util.ConfigFactory;
import cn.edu.hhuc.si.util.DBUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service("demoInitService")
public class InitServiceImpl implements InitServiceI {

    @SuppressWarnings("unchecked")
    synchronized public void init() throws IOException {
        ConfigFactory config = ConfigFactory.Instance();
        DBUtils instance = DBUtils.Instance();
    }

    /**
     * run after web closed
     */
    @PreDestroy
    public void applicationEnd() {

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

    }
}
