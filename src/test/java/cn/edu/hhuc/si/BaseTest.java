package cn.edu.hhuc.si;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class BaseTest {

    private static Logger log = Logger.getLogger(BaseTest.class);

    @Test
    public void helloTest() {
        System.out.println("hello world");
    }
}
