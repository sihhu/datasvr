package cn.edu.hhuc.si.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version V1.0
 * @Package: cn.edu.hhuc.si.util
 * @ClassName: ConfigFactory
 * @Description: read data.properties
 * @author: Tao
 * @date: Create in 2017-08-12 17:35
 **/
public class ConfigFactory {

    private static ConfigFactory instance = null;

    private ConfigFactory() {
        prop = new Properties();
        InputStream in = this.getClass().getResourceAsStream("/data.properties");
        try {
            if (in != null) {
                prop.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigFactory Instance() {
        if (instance == null) {
            instance = new ConfigFactory();
        }
        return instance;
    }

    private Properties prop = null;

    public void LoadProperties(String propFilePath) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(propFilePath);
            this.prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addProperty(String key, String value) {
        this.prop.setProperty(key, value);
    }

    public String getProperty(String key) {
        return this.prop.getProperty(key);
    }
}
