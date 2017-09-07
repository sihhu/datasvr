Intro
============

Datasvr is a simple collection of back-service utilities.

At the moment, we only have a springmvc-demo configuration without any other plugins, but there is more to come!

Prepare for a simple SpringMVC-demo
============
- configure `pom.xml`, including *.jar
- add `web.xml` (applicationContext.xml & dv-servlet.xml included)
- files in `WEB-INF/` is needed for the configuration in `dv-servlet.xml`
- `sql files` is configured in 'resources/data.properties'(res), see `pub/Sql.zip`

## Note
- you can input `http://127.0.0.1:8080/dv/home/index` to see the `home.jsp`, if `home.jsp` does not exist, the console will show fault for no-mapping.
- in `cn.edu.hhuc.si.controller.api.BasicAPI`, you can see different functions for the communication.

## Usage
For configuration of the demo, you can:
>
> Use Jetty:
> * mvn install
> * run `DataServer.java` in package `cn.edu.hhuc.si.server`
>

>
> Use Tomcat:
>

## Additional Readings
- For setting springmvc, you can see [V1.0](https://github.com/taoyouxian/datasvr/tree/springmvc-demo)
- For solving the cross-domain, you can see [V1.1](https://github.com/taoyouxian/datasvr/tree/cross-domain)

## Contact us
For feedback and questions with IntelliJ IDEA building the project, feel free to email us:
* Youxian Tao taoyouxian@aliyun.com
