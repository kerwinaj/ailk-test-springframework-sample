package com.ailk.test.spring.annocation;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 *  Spring测试常用的注解：
 *  
 *  注意事项：
 *  	这些注解在JUnit4和TestNG测试框架中都可以使用
 *  
 *  1、@ContextConfiguration
 *  		使用描述：
 *  			类级别注解，用于指定如何加载ApplicationContext
 *  		属性描述：
 *  			（1）value，默认属性，同locations
 *  			（2）locations，指定ApplicationContext配置文件的位置，位置可以是classpath，文件路径等
 *  			（3）classes，指定加载 经@Configuration 注解的类
 *  			（4）initializers，指定ApplicationContextInitializer的实现类（spring3.2版本才引进）,和locations或classes结合使用，用于初始自定义的ApplicationContext初始化
 *  			（5）loader，指定加载ApplicationContext的ContextLoader，可以不需要显示指定
 *  			（6）inheritLocations，是否继承父类的locations，默认为true
 *  			（7）inheritInitializers，是否继承父类的initializers，默认为true
 *  
 *  2、@WebAppConfiguration
 *  		使用描述：
 *  			类级别注解，在Spring web项目中才会用到，用于指定webapp的root目录，不指定值时默认为"file:src/main/webapp"
 *  		属性描述：
 *  			（1）value，指定webapp的root目录
 *  
 *  3、@ActiveProfiles
 *  		使用描述：
 *  			类级别注解，用于指定测试时加载ApplicationContext的bean definition profiles(参照spring的profile用途)
 *  		属性描述：
 *  			（1）value，默认属性，同profiles
 *  			（2）profiles，指定加载ApplicationContext的bean definition profiles
 *  			（3）inheritProfiles，是否继承父类的profiles，默认为true
 *  
 *  4、@DirtiesContext
 *  		使用描述：
 *  			类和方法级别注解，用于指明测试期间，不管测试是否通过，ApplicationContext已过时（dirty，例如：需要替换一些类定义需要修改配置)，应该被销毁
 *  		属性描述：
 *  			（1）classMode，过时（dirty)模式，以下3种值可选：
 *  				① ClassMode.AFTER_CLASS，在声明为类注解时使用，不指定值时的默认属性，指定当前类的所有测试方法结束后销毁ApplicationContext
 *  				② ClassMode.AFTER_EACH_TEST_METHOD，在声明为类注解时使用，指定当前类的每个测试方法结束都会销毁ApplicationContext
 *  				③  声明在测试方法中，无需指定值，当前测试方法结束后销毁ApplicationContext
 *  
 *  5、@TestExecutionListeners
 *  		使用描述：
 *  			类级别注解，用于指定测试前将TestExecutionListener注册到TestContextManager
 *  		属性描述：
 *  			（1）value，默认属性，同listeners
 *  			（2）listeners，指定要注册到TestContextManager的TestExecutionListener
 *  
 *  6、@TransactionConfiguration
 *  		使用描述：
 *  			类级别注解，用于指定该测试类需要支持事务（Transaction），需要通过在ApplicationContext定义PlatformTransactionManager类来驱动事务执行
 *  		属性描述：
 *  			（1）transactionManager，不显示指定时默认值为transactionManager，当ApplicationContext定义了多个PlatformTransactionManager，可以通过此属性来显示指定
 *  			（2）defaultRollback，指定是否在执行测试后进行回滚，默认为true
 *  		
 *  7、@Rollback
 *  		使用描述：
 *  			方法级别注解，用于指定该测试方法结束后是否进行回滚，覆盖@TransactionConfiguration的defaultRollback属性
 *  		属性描述：
 *  			（1）transactionManager，不显示指定时默认值为transactionManager，当ApplicationContext定义了多个PlatformTransactionManager，可以通过此属性来显示指定
 *  			（3）defaultRollback，指定是否在执行测试后进行回滚，默认为true
 *  8、@NotTransactional
 *  		使用描述：
 *  			已过时
 *  		属性描述：
 *  			已过时
 * 
 * srping-spec的其他注解也可以使用（参照spring-references）：
 *  	1、@Autowired
 *  	2、@Qualifier
 *  	3、@Resource (javax.annotation) if JSR-250 is present
 *  	4、@Inject (javax.inject) if JSR-330 is present
 *  	5、@Named (javax.inject) if JSR-330 is present
 *  	6、@PersistenceContext (javax.persistence) if JPA is present
 *  	7、@PersistenceUnit (javax.persistence) if JPA is present
 *  	8、@Required
 *  	9、@Transactional
 * </pre>
 * @date 2013-1-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("file:src/main/webapp")
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/webapp/WEB-INF/sample-servlet.xml" }, classes = {})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SpringAnnotationTestCase {
	/**
	 * spring-spec注解
	 */
	@Autowired
	private WebApplicationContext wac;

	@Test
	public void testWebAppConfiguration() {
		assertThat(wac, notNullValue());
	}

	@Test
	@Rollback(true)
	public void testRollback() {
		// 会覆盖@TransactionConfiguration(transactionManager =
		// "transactionManager", defaultRollback = false)
	}

	@Test
	public void testDirtiesContext() {
		// 此方法执行后was会被销毁
	}

}
