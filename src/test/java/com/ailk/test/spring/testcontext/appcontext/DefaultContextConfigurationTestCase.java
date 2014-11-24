package com.ailk.test.spring.testcontext.appcontext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 如果@ContextConfiguration既未指定locations，也未指定classes属性时，加载ApplicationContext的默认方式
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 默认为 "file:src/main/webapp"
@WebAppConfiguration
// 在相同的的包路径"com.ailk.test.spring.annocation.contextconf"寻找
// "DefaultContextConfigurationTestCase-context.xml"配置
// 或者 用注解@Configuration声明的static nested class
@ContextConfiguration
public class DefaultContextConfigurationTestCase {

}
