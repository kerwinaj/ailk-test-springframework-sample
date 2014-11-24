package com.ailk.test.spring.testcontext.testsupportclass;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 	继承AbstractJUnit4SpringContextTests时可以不用指定@RunWith，但要指定@ContextConfiguration
 * AbstractJUnit4SpringContextTests包含类一个protected 的applicationContext，当我们要使用
 * applicationContext时，可以继承它，这样不用每个测试类都要去定义一个applicationContext对象。
 * </pre>
 * @date 2013-1-23
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/webapp/WEB-INF/sample-servlet.xml" }, classes = {})
public class ExtendedTrasactionJUnit4SpringContextTests extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
		Assert.assertNotNull(this.applicationContext);
	}
}
