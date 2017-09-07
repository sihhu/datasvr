package cn.edu.hhuc.si.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class InitDataListener implements ServletContextListener {

	private static Logger log = LoggerFactory.getLogger(InitServiceI.class);

	private static ApplicationContext ctx = null;

	public InitDataListener() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		log.debug("======close======");

	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		log.debug("======load======");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		InitServiceI initService = (InitServiceI) ctx
				.getBean("demoInitService");
		try {
			initService.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
