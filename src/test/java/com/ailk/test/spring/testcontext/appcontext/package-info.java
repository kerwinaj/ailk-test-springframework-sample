package com.ailk.test.spring.testcontext.appcontext;

/**
 * 
 * Spring集成测试支持目标之一：在整个测试期间，管理及缓存Spring IoC容器(ApplicationContext、WebApplicationContext)
 * 
 * Spring初始化启动过程是比较耗时的，如果每一个测试方法都要重新创建一个ApplicationContext，导致测试效率不高，而
 * Spring TestContext Framework，在整个测试期间把ApplicationContext缓存起来，这样在同一测试Suite中，我们都可以
 * 重用ApplicationContext中定义的对象，避免每次都要加载导致测试效率较低。
 * 
 * Spring TestContext Framework包含的核心类：
 * 
 * 1、TestContext
 * 		单实例、这个类封装了测试上下文（ApplicationContext)，提供了上下文管理和缓存支持，并通过委托ContextLoader 去加载ApplicationContext
 * 		
 * 2、TestContextManager
 * 		 Spring TestContext Framework的入口，管理TestContext和TestExecutionListener，并向注册了的TestExecutionListener发送测试执行
 * 		期间的各种信号事件。测试执行期间切入点如：
 * 		（1）、在任何before Class方法调用之前触发        ----如JUnit4用@BeforeClass定义的方法
 * 		（2）、为测试实例的做准备工作                                        ----如TestCase调用构造方法
 * 		（3）、在任何before方法调用之前                                  ----如JUnit4用@Before定义的方法
 * 		（4）、在任何after方法调用之后                                    ----如JUnit4用@After定义的方法
 * 		（5）、在任何after class方法调用之后                    ----如JUnit4用@AfterClass定义的方法
 * 
 * 3、TestExecutionListener，用于测试执行期间的侦听各种事件，默认在TestContextManager注册了4种：
 * 		（1）、ServletTestExecutionListener，用于支持WebApplicationContext的各种 Servlet API mocks
 * 		（2）、DependencyInjectionTestExecutionListener，用于各个测试类的依赖注入
 * 		（3）、DirtiesContextTestExecutionListener，用于销毁过时的ApplicationContext
 * 		（4）、TransactionalTestExecutionListener，用于测试方法事务管理
 * 
 * 4、SmartContextLoader，在3.1版本后引入，扩展了ContextLoader，用于智能地选择通过locations，classes，initializers指明的配置加载上下文。而且可以激活
 * 		类定义Profile，Spring提供了以下几种实现：
 * 		（1）、DelegatingSmartContextLoader，2个默认加载器之一，根据定义的是locations还是classes来灵活地委托GenericXmlContextLoader或
 * 				AnnotationConfigContextLoader加载ApplicationContext
 * 		（2）、WebDelegatingSmartContextLoader，2个默认加载器之一，在Web项目中（测试类声明了为@WebAppConfiguration ），根据定义的是locations
 * 				还是classes来灵活地委托GenericXmlWebContextLoader或AnnotationConfigWebContextLoader 加载WebApplicationContext
 * 		（3）、AnnotationConfigContextLoader，从注解类中加载ApplicationContext
 * 		（4）、AnnotationConfigWebContextLoader，从注解类中加载WebApplicationContext 
 * 		（5）、GenericXmlContextLoader，从xml配置中加载ApplicationContext
 * 		（6）、GenericXmlWebContextLoader，从xml配置中加载WebApplicationContext
 * 		（7）、GenericPropertiesContextLoader，从java properties文件中加载ApplicationContext
 * 
 * 		
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
*/