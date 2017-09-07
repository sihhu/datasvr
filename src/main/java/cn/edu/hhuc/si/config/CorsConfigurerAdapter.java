package cn.edu.hhuc.si.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @version V1.0
 * @Package: cn.edu.hhuc.si.config
 * @ClassName: dd
 * @Description:
 * @author: Tao
 * @date: Create in 2017-09-07 12:38
 **/
public class CorsConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/dv/*").allowedOrigins("*");
    }
}