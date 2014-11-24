package com.ailk.test.spring.testcontext.appcontext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ailk.test.spring.CustomApplicationContextInitializer;
import com.ailk.test.spring.ProfileCustomDataSourceConfig;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 
 * 使用@ContextConfiguration的initializers来指定ApplicationContextInitializer做些硬编码的ApplicationContext初始化工作
 * 
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProfileCustomDataSourceConfig.class }, initializers = { CustomApplicationContextInitializer.class })
public class UseInitializerContextConfigurationTestCase implements ApplicationContextAware {

	ApplicationContext applicationContext;

	@Test
	public void testUseInitializer() {
		// 如果把CustomApplicationContextInitializer里的代码注释掉，则测试不会通过，因为没找到合适的profile
		applicationContext.getBean("dataSource");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
