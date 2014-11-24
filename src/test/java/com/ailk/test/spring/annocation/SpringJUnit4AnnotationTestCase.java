package com.ailk.test.spring.annocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ailk.test.spring.CustomProfileValueSource;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 *  Spring JUnit4测试常用的注解。
 *  
 *  注意事项：
 *  	这些注解只能在使用JUnit4可以使用，一般声明@RunWith为SpringJUnit4ClassRunner，TestNG测试框架不可以使用
 *  
 *  1、@IfProfileValue
 *  		使用描述：
 *  			类和方法级别注解，类级别会覆盖方法级别设置，指明当前环境满足指定的profile值时，该测试方法才会有效，否则不会执行。
 *  			获取当前环境profile值的类通过@ProfileValueSourceConfiguration来指定，未指定时默认为SystemProfileValueSource
 *  		属性描述：
 *  			（1）name，profile名称
 *  			（2）value，profile值
 *  			（3）values，profile值列表
 *  2、@ProfileValueSourceConfiguration
 *  		使用描述：
 *  			类级别注解，用于指定返回profile值的类（实现ProfileValueSource接口）
 *  		属性描述：
 *  			（1）value，未指定时为SystemProfileValueSource
 *  3、@Timed
 *  		使用描述：
 *  			方法级别注解，指定该测试方法必须在指定的实际执行完，否则测试不通过。
 *  		属性描述：
 *  			（1）millis，指定测试方法执行的最小时间(单位：毫秒)，Timed > setup + test(repeat =n ) + tearDown 才会通过，不同于JUnit4的Test(timeout=...)，
 *  4、@Repeat
 *  		使用描述：
 *  			方法级别注解，指定该测试方法会执行指定次数
 *  		属性描述：
 *  			（1）value，指定测试方法执行次数
 * 
 * </pre>
 * @date 2013-1-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/webapp/WEB-INF/sample-servlet.xml" }, classes = {})
@TransactionConfiguration
@ProfileValueSourceConfiguration(CustomProfileValueSource.class)
public class SpringJUnit4AnnotationTestCase {

	private static final Log log = LogFactory.getLog(SpringJUnit4AnnotationTestCase.class);

	@IfProfileValue(name = "java.vendor", value = "Sun Microsystems Inc.")
	@Test
	public void testProcessWhichRunsOnlyOnSunJvm() {
		// 该测试方法只会在Sun Microsystems Inc的 Java VMs 执行

	}

	@IfProfileValue(name = "test-groups", values = { "unit-tests" })
	@Test
	public void testProcessWhichRunsForUnitOrIntegrationTestGroups() {
		// 该测试方法只会在单元测试（unit test）和结合测试（integration test)组中执行
	}

	@Test
	@Timed(millis = 1000L)
	public void testTimed() throws Exception {
		Thread.sleep(500L);
	}

	@Test
	@Timed(millis = 1000L)
	@Repeat(4)
	public void testTimedAndRepeat() throws Exception {
		Thread.sleep(500L);
	}

	@Repeat(3)
	public void testRepeat() throws Exception {
		log.info("testRepeat");
	}

}
