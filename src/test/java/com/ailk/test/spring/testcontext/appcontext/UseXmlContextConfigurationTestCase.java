package com.ailk.test.spring.testcontext.appcontext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 	从@ContextConfiguration的locations属性指定的xml文件来加载ApplicationContext
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 从"/app-config.xml" 和"/test-config.xml"加载ApplicationContext
@ContextConfiguration(locations = { "/app-config.xml", "/test-config.xml" })
public class UseXmlContextConfigurationTestCase {

}
