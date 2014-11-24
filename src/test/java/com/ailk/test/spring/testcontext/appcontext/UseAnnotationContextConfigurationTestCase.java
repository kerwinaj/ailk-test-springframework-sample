package com.ailk.test.spring.testcontext.appcontext;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ailk.test.spring.CustomDataSourceConfig;

/**
 * 
 * @author <a href="mailto:liulj5@asiainfo-linkage.com">liulj5</a>
 * @description <pre>
 * 	从@ContextConfiguration的classes属性指定的类来加载ApplicationContext
 * </pre>
 * @date 2013-1-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CustomDataSourceConfig.class })
public class UseAnnotationContextConfigurationTestCase implements ApplicationContextAware {
	ApplicationContext applicationContext;

	@Test
	public void testDataSource() {
		assertNotNull(this.applicationContext);
		assertNotNull(this.applicationContext.getBean("dataSource"));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
